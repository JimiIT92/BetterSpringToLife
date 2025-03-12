package org.hendrix.betterspringdrop.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.conversion.EntityConversionContext;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.hendrix.betterspringdrop.core.BSDEntities;
import org.hendrix.betterspringdrop.core.BSDSounds;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation class for a {@link PigEntity Muddy Pig}
 */
public class MuddyPigEntity extends PigEntity implements Shearable {

    /**
     * {@link TrackedData<Boolean> Whether the Muddy Pig has a flower}
     */
    private static final TrackedData<Boolean> HAS_FLOWER = DataTracker.registerData(MuddyPigEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType The {@link EntityType Entity Type}
     * @param world The {@link World World reference}
     */
    public MuddyPigEntity(final EntityType<? extends PigEntity> entityType, final World world) {
        super(entityType, world);
    }

    /**
     * Initialize the entity data
     *
     * @param builder The {@link DataTracker.Builder Data Tracker Builder}
     */
    protected void initDataTracker(final DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(HAS_FLOWER, false);
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
        if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
            final World world = this.getWorld();
            if (world instanceof ServerWorld serverWorld) {
                this.sheared(serverWorld, SoundCategory.PLAYERS, itemStack);
                this.emitGameEvent(GameEvent.SHEAR, player);
                itemStack.damage(1, player, getSlotForHand(hand));
                this.dropStack(serverWorld, new ItemStack(Blocks.POPPY, 1));
            }
            return ActionResult.SUCCESS;
        }
        else if (itemStack.isOf(Items.WATER_BUCKET)) {
            final World world = this.getWorld();
            if (world instanceof ServerWorld serverWorld) {
                world.playSoundFromEntity(null, player, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                this.clean(serverWorld);
                player.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, player, new ItemStack(Items.BUCKET)));
            }
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }

    /**
     * Shear the Muddy Pig
     *
     * @param world The {@link World World reference}
     * @param shearedSoundCategory The {@link SoundCategory Sound Category for the Shear sound}
     * @param shears The {@link ItemStack Shears Item Stack used}
     */
    @Override
    public void sheared(final ServerWorld world, final SoundCategory shearedSoundCategory, final ItemStack shears) {
        world.playSoundFromEntity(null, this, BSDSounds.ENTITY_MOOBLOOM_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        world.spawnParticles(ParticleTypes.EXPLOSION, this.getX(), this.getBodyY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        this.setHasFlower(false);
    }

    /**
     * Clean the Muddy Pig
     *
     * @param world The {@link World World reference}
     */
    public void clean(final ServerWorld world) {
        world.spawnParticles(ParticleTypes.FALLING_WATER, this.getX(), this.getBodyY(0.5D) + 0.5D, this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        this.convertTo(EntityType.PIG, EntityConversionContext.create(this, false, false), (pig) -> { });
    }

    /**
     * Create a child entity
     *
     * @param world The {@link ServerWorld World reference}
     * @param entity The {@link PassiveEntity parent entity}
     * @return The {@link PigEntity child entity}
     */
    @Override
    public @Nullable PigEntity createChild(final ServerWorld world, final PassiveEntity entity) {
        return BSDEntities.MUDDY_PIG.create(world, SpawnReason.BREEDING);
    }

    /**
     * Check if the Muddy Pig can be sheared
     *
     * @return {@link Boolean True if is alive, not a baby and has a flower}
     */
    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isBaby() && this.hasFlower();
    }

    /**
     * Set the flower status of the Muddy Pig
     *
     * @param hasFlower {@link Boolean Whether the Muddy Pig has a flower or not}
     */
    private void setHasFlower(final boolean hasFlower) {
        this.dataTracker.set(HAS_FLOWER, hasFlower);
    }

    /**
     * Check if the Muddy Pig has a flower
     *
     * @return {@link Boolean True if the Muddy Pig has a flower}
     */
    public boolean hasFlower() {
        return this.dataTracker.get(HAS_FLOWER);
    }

    /**
     * Write the entity data to {@link NbtCompound NBT}
     *
     * @param nbt The {@link NbtCompound NBT Compound Data}
     */
    public void writeCustomDataToNbt(final NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("HasFlower", this.hasFlower());
    }

    /**
     * Read the entity data from the {@link NbtCompound NBT}
     *
     * @param nbt The {@link NbtCompound NBT Compound Data}
     */
    public void readCustomDataFromNbt(final NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setHasFlower(nbt.getBoolean("HasFlower").orElse(false));
    }

    /**
     * Initialize the entity
     *
     * @param world The {@link ServerWorldAccess World reference}
     * @param difficulty The {@link LocalDifficulty difficulty level}
     * @param spawnReason The {@link SpawnReason Spawn Reason}
     * @param entityData The {@link EntityData custom Entity Data}
     * @return {@link EntityData The Entity Data}
     */
    public EntityData initialize(final ServerWorldAccess world, final LocalDifficulty difficulty, final SpawnReason spawnReason, final @Nullable EntityData entityData) {
        this.setHasFlower(this.getRandom().nextBoolean());
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

}