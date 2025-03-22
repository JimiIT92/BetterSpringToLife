package org.hendrix.betterspringtolife.entity;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
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
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.core.BSTLEntities;
import org.hendrix.betterspringtolife.core.BSTLSounds;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

/**
 * Implementation class for a {@link AbstractCowEntity Moobloom}
 */
public final class MoobloomEntity extends AbstractCowEntity implements Shearable {

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
     * Initialize the entity goals
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0F));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0F));
        this.goalSelector.add(3, new TemptGoal(this, 1.25F, this::isBreedingItem, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25F));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0F));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    /**
     * Check if the {@link ItemStack Item Stack} can be used to breed the entity
     *
     * @param itemStack The {@link ItemStack Item Stack}
     * @return {@link Boolean True} if is a {@link BSTLBlocks#BUTTERCUP Buttercup} or is in the {@link ItemTags#COW_FOOD Cow Food Tag}
     */
    @Override
    public boolean isBreedingItem(final ItemStack itemStack) {
        return itemStack.isOf(BSTLBlocks.BUTTERCUP.asItem());
    }

    /**
     * Tick the entity
     *
     * @param world The {@link ServerWorld World reference}
     */
    @Override
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
    @Override
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
    @Override
    public ActionResult interactMob(final PlayerEntity player, final Hand hand) {
        final ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BOWL) && !this.isBaby() && this.hasFlowers()) {
            final ItemStack suspiciousStew = new ItemStack(Items.SUSPICIOUS_STEW);
            suspiciousStew.set(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffectsComponent(Collections.singletonList(new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.ABSORPTION, 100))));
            this.playSound(BSTLSounds.ENTITY_MOOBLOOM_SUSPICIOUS_MILK, 1.0F, 1.0F);
            return ActionResult.SUCCESS.withNewHandStack(ItemUsage.exchangeStack(itemStack, player, suspiciousStew, false));
        }
        else if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            final World world = this.getWorld();
            if (world instanceof ServerWorld serverWorld) {
                this.sheared(serverWorld, SoundCategory.PLAYERS, itemStack);
                this.emitGameEvent(GameEvent.SHEAR, player);
                itemStack.damage(1, player, getSlotForHand(hand));
                this.dropStack(serverWorld, new ItemStack(BSTLBlocks.BUTTERCUP, 4));
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
        world.playSoundFromEntity(null, this, BSTLSounds.ENTITY_MOOBLOOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
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
        return BSTLEntities.MOOBLOOM.create(world, SpawnReason.BREEDING);
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
    @Override
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
    @Override
    public void readCustomDataFromNbt(final NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasFlowers(nbt.getBoolean("HasFlowers").orElse(true));
        this.dataTracker.set(FLOWER_REGROW_TIME, nbt.getInt("FlowerRegrowTime").orElse(MAX_REGROW_TIME));
    }

}