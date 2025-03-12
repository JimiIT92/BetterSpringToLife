package org.hendrix.betterspringdrop.entity;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AbstractCowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.hendrix.betterspringdrop.core.BSDBlocks;
import org.hendrix.betterspringdrop.core.BSDEntities;
import org.hendrix.betterspringdrop.core.BSDSounds;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

/**
 * Implementation class for a {@link AbstractCowEntity Moobloom}
 */
public class MoobloomEntity extends AbstractCowEntity implements Shearable {

    /**
     * {@link TrackedData<Boolean> Whether the Mooblom has flowers}
     */
    private static final TrackedData<Boolean> HAS_FLOWERS = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    /**
     * {@link TrackedData<Integer> The Mooblom time left for flowers to regrow}
     */
    private static final TrackedData<Integer> FLOWER_REGROW_TIME = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.INTEGER);
    /**
     * The {@link Integer maximum time for flowers to regrow in ticks}
     */
    private static final int MAX_REGROW_TIME = 6000;

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType The {@link EntityType Entity Type}
     * @param world The {@link World World reference}
     */
    public MoobloomEntity(final EntityType<? extends AbstractCowEntity> entityType, final World world) {
        super(entityType, world);
    }

    /**
     * Tick the entity
     *
     * @param world The {@link ServerWorld World reference}
     */
    protected void mobTick(final ServerWorld world) {
        if(!this.isBaby() && !this.hasFlowers()) {
            final int flowerGrowTime = this.dataTracker.get(FLOWER_REGROW_TIME) - 1;
            if(flowerGrowTime <= 0) {
                this.setHasFlowers(true);
            } else {
                this.dataTracker.set(FLOWER_REGROW_TIME, flowerGrowTime);
            }

        }
        super.mobTick(world);
    }

    /**
     * Initialize the entity data
     *
     * @param builder The {@link DataTracker.Builder Data Tracker Builder}
     */
    protected void initDataTracker(final DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(HAS_FLOWERS, true);
        builder.add(FLOWER_REGROW_TIME, MAX_REGROW_TIME);
    }

    /**
     * Get a {@link Items#SUSPICIOUS_STEW Suspicious Stew} or {@link Items#MILK_BUCKET Milk} when interacted
     *
     * @param player The {@link PlayerEntity Player interacting with the entity}
     * @param hand The {@link Hand Hand the Player is using}
     * @return The {@link ActionResult interaction result}
     */
    public ActionResult interactMob(final PlayerEntity player, Hand hand) {
        final ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BOWL) && !this.isBaby() && this.hasFlowers()) {
            final ItemStack suspiciousStew = new ItemStack(Items.SUSPICIOUS_STEW);
            suspiciousStew.set(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffectsComponent(Collections.singletonList(new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.ABSORPTION, 100))));
            player.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, player, suspiciousStew, false));
            this.playSound(BSDSounds.ENTITY_MOOBLOOM_SUSPICIOUS_MILK, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }
        else if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            final World world = this.getWorld();
            if (world instanceof ServerWorld serverWorld) {
                this.sheared(serverWorld, SoundCategory.PLAYERS, itemStack);
                this.emitGameEvent(GameEvent.SHEAR, player);
                itemStack.damage(1, player, getSlotForHand(hand));
                this.dropStack(serverWorld, new ItemStack(BSDBlocks.BUTTERCUP, 4));
            }
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    /**
     * Shear the Moobloom
     *
     * @param world The {@link World World reference}
     * @param shearedSoundCategory The {@link SoundCategory Sound Category for the Shear sound}
     * @param shears The {@link ItemStack Shears Item Stack used}
     */
    @Override
    public void sheared(final ServerWorld world, final SoundCategory shearedSoundCategory, final ItemStack shears) {
        world.playSoundFromEntity(null, this, BSDSounds.ENTITY_MOOBLOOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        world.spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        this.setHasFlowers(false);
        this.dataTracker.set(FLOWER_REGROW_TIME, MAX_REGROW_TIME);
    }

    /**
     * Create a child entity
     *
     * @param world The {@link ServerWorld World reference}
     * @param entity The {@link PassiveEntity parent entity}
     * @return The {@link PassiveEntity child entity}
     */
    @Override
    public @Nullable PassiveEntity createChild(final ServerWorld world, final PassiveEntity entity) {
        return BSDEntities.MOOBLOOM.create(world, SpawnReason.BREEDING);
    }

    /**
     * Check if the Moobloom can be sheared
     *
     * @return {@link Boolean True if is alive, not a baby and has flowers}
     */
    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isBaby() && this.hasFlowers();
    }

    /**
     * Set the flowers status of the Moobloom
     *
     * @param hasFlowers {@link Boolean Whether the Moobloom has flowers or not}
     */
    private void setHasFlowers(final boolean hasFlowers) {
        this.dataTracker.set(HAS_FLOWERS, hasFlowers);
    }

    /**
     * Check if the Moobloom has flowers
     *
     * @return {@link Boolean True if the Moobloom has flowers}
     */
    public boolean hasFlowers() {
        return this.dataTracker.get(HAS_FLOWERS);
    }

    /**
     * Write the entity data to {@link NbtCompound NBT}
     *
     * @param nbt The {@link NbtCompound NBT Compound Data}
     */
    public void writeCustomDataToNbt(final NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("HasFlowers", this.hasFlowers());
        nbt.putInt("FlowerRegrowTime", this.dataTracker.get(FLOWER_REGROW_TIME));
    }

    /**
     * Read the entity data from the {@link NbtCompound NBT}
     *
     * @param nbt The {@link NbtCompound NBT Compound Data}
     */
    public void readCustomDataFromNbt(final NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasFlowers(nbt.getBoolean("HasFlowers").orElse(true));
        this.dataTracker.set(FLOWER_REGROW_TIME, nbt.getInt("FlowerRegrowTime").orElse(MAX_REGROW_TIME));
    }

}