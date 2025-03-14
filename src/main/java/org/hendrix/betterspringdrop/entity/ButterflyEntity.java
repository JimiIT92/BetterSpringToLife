package org.hendrix.betterspringdrop.entity;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.hendrix.betterspringdrop.core.BSDDataComponentTypes;
import org.hendrix.betterspringdrop.core.BSDTags;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

/**
 * Implementation class for a {@link AmbientEntity Butterfly}
 */
public class ButterflyEntity extends PathAwareEntity implements Flutterer {

    /**
     * The {@link TrackedData<Integer> Butterfly Variant}
     */
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(ButterflyEntity.class, TrackedDataHandlerRegistry.INTEGER);
    /**
     * The {@link Float flap progress}
     */
    public float flapProgress;
    /**
     * The {@link Float last flap progress}
     */
    public float lastFlapProgress;
    /**
     * The {@link Float max wing deviation}
     */
    public float maxWingDeviation;
    /**
     * The {@link Float last max wing deviation}
     */
    public float lastMaxWingDeviation;
    /**
     * The {@link Float flap speed}
     */
    private float flapSpeed = 1.0F;
    /**
     * The {@link Float current flap speed}
     */
    private float currentFlapSpeed = 1.0F;

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType The {@link EntityType Entity Type}
     * @param world The {@link World World reference}
     */
    public ButterflyEntity(final EntityType<? extends PathAwareEntity> entityType, final World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, false);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.COCOA, -1.0F);
    }

    /**
     * Initialize the entity goals
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(2, new ButterflyEntity.FlyOntoTreeGoal(this, 1.0F));
        this.goalSelector.add(3, new FollowMobGoal(this, 1.0F, 3.0F, 7.0F));
    }

    /**
     * Get the {@link Float pathfinding favor chance}
     *
     * @param pos The {@link BlockPos current Block Pos}
     * @param world The {@link WorldView World reference}
     * @return {@link Float 10.0F if is in air, otherwise 0}
     */
    @Override
    public float getPathfindingFavor(final BlockPos pos, final WorldView world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    /**
     * Initialize the entity data
     *
     * @param builder The {@link DataTracker.Builder Data Tracker Builder}
     */
    @Override
    protected void initDataTracker(final DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, ButterflyEntity.Variant.DEFAULT.index);
    }

    /**
     * Write the entity data to {@link NbtCompound NBT}
     *
     * @param nbt The {@link NbtCompound NBT Compound Data}
     */
    @Override
    public void writeCustomDataToNbt(final NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Variant", ButterflyEntity.Variant.INDEX_CODEC, this.getVariant());
    }

    /**
     * Read the entity data from the {@link NbtCompound NBT}
     *
     * @param nbt The {@link NbtCompound NBT Compound Data}
     */
    @Override
    public void readCustomDataFromNbt(final NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(nbt.get("Variant", Variant.INDEX_CODEC).orElse(Variant.DEFAULT));
    }

    /**
     * Get the {@link ButterflyEntity.Variant Butterfly Variant}
     *
     * @return The {@link ButterflyEntity.Variant Butterfly Variant}
     */
    public ButterflyEntity.Variant getVariant() {
        return ButterflyEntity.Variant.byIndex(this.dataTracker.get(VARIANT));
    }

    /**
     * Set the {@link ButterflyEntity.Variant Butterfly Variant}
     *
     * @param variant The {@link ButterflyEntity.Variant Butterfly Variant}
     */
    private void setVariant(final ButterflyEntity.Variant variant) {
        this.dataTracker.set(VARIANT, variant.index);
    }

    /**
     * Copy the {@link ComponentsAccess entity components}
     *
     * @param from The {@link ComponentsAccess source to copy from}
     */
    @Override
    protected void copyComponentsFrom(final ComponentsAccess from) {
        this.copyComponentFrom(from, BSDDataComponentTypes.BUTTERFLY_VARIANT);
        super.copyComponentsFrom(from);
    }

    /**
     * Apply a {@link ComponentType component} to the entity
     *
     * @param type The {@link ComponentType<T> component to apply}
     * @param value The {@link T component value}
     * @return {@link Boolean True if the component has been successfully applied}
     * @param <T> The {@link T component class}
     */
    @Override
    protected <T> boolean setApplicableComponent(final ComponentType<T> type, final T value) {
        if (BSDDataComponentTypes.BUTTERFLY_VARIANT.equals(type)) {
            this.setVariant(castComponentValue(BSDDataComponentTypes.BUTTERFLY_VARIANT, value));
            return true;
        }
        return super.setApplicableComponent(type, value);
    }

    /**
     * Create the entity attributes
     *
     * @return The {@link DefaultAttributeContainer.Builder Attribute builder}
     */
    public static DefaultAttributeContainer.Builder createButterflyAttributes() {
        return AnimalEntity.createAnimalAttributes()
                .add(EntityAttributes.MAX_HEALTH, 1.0F)
                .add(EntityAttributes.FLYING_SPEED, 0.4F)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.2F)
                .add(EntityAttributes.ATTACK_DAMAGE, 1.0F);
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
    @Override
    public EntityData initialize(final ServerWorldAccess world, final LocalDifficulty difficulty, final SpawnReason spawnReason, final @Nullable EntityData entityData) {
        this.setVariant(Util.getRandom(Variant.values(), world.getRandom()));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    /**
     * Get the {@link EntityNavigation entity navigation}
     *
     * @param world The {@link World World reference}
     * @return The {@link EntityNavigation entity navigation}
     */
    @Override
    protected EntityNavigation createNavigation(final World world) {
        final BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        return birdNavigation;
    }

    /**
     * Tick the entity movement
     */
    @Override
    public void tickMovement() {
        super.tickMovement();
        this.flapWings();
    }

    /**
     * Flap the entity wings
     */
    private void flapWings() {
        this.lastFlapProgress = this.flapProgress;
        this.lastMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation += (float)(!this.isOnGround() && !this.hasVehicle() ? 4 : -1) * 0.3F;
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0F, 1.0F);
        if (!this.isOnGround() && this.flapSpeed < 1.0F) {
            this.flapSpeed = 1.0F;
        }

        this.flapSpeed *= 0.5F;
        final Vec3d velocity = this.getVelocity();
        if (!this.isOnGround() && velocity.y < (double)0.0F) {
            this.setVelocity(velocity.multiply(1.0F, 0.6, 1.0F));
        }

        this.flapProgress += this.flapSpeed * 2.0F;
    }

    /**
     * Make the entity immune to fall damage
     *
     * @param heightDifference The {@link Double height difference}
     * @param onGround {@link Boolean Whether the entity is on ground}
     * @param state The {@link BlockState Block State} the entity landed on
     * @param landedPosition The {@link BlockPos Block Pos} the entity landed on
     */
    @Override
    protected void fall(final double heightDifference, final boolean onGround, final BlockState state, final  BlockPos landedPosition) {
    }

    /**
     * Check if the entity is flapping wings
     *
     * @return {@link Boolean True if is flapping wings}
     */
    @Override
    protected boolean isFlappingWings() {
        return this.speed > this.currentFlapSpeed;
    }

    /**
     * Update the current flap speed
     */
    @Override
    protected void addFlapEffects() {
        this.currentFlapSpeed = this.speed + this.maxWingDeviation / 2.0F;
    }

    /**
     * Check if the entity can be pushed
     *
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isPushable() {
        return true;
    }

    /**
     * Make the entity push away another entity
     *
     * @param entity The {@link Entity entity to push}
     */
    @Override
    protected void pushAway(final Entity entity) {
        if (!(entity instanceof PlayerEntity)) {
            super.pushAway(entity);
        }
    }

    /**
     * Check if the entity is in air
     *
     * @return {@link Boolean True if is not on ground}
     */
    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

    /**
     * Check if the entity can spawn at the current {@link BlockPos Block Pos}
     *
     * @param type The {@link EntityType<ButterflyEntity> Entity Type}
     * @param world The {@link WorldAccess World reference}
     * @param spawnReason The {@link SpawnReason Spawn Reason}
     * @param pos The {@link BlockPos current BLock Pos}
     * @param random The {@link Random Random reference}
     * @return {@link Boolean True if the entity can spawn}
     */
    public static boolean canSpawn(final EntityType<ButterflyEntity> type, final WorldAccess world, final SpawnReason spawnReason, final BlockPos pos, final Random random) {
        return world.getBlockState(pos.down()).isIn(BSDTags.BlockTags.BUTTERFLIES_SPAWNABLE_ON) && world.getBaseLightLevel(pos, 0) > 8;
    }

    /**
     * {@link FlyGoal Goal} for flying onto a tree
     */
    static class FlyOntoTreeGoal extends FlyGoal {

        /**
         * Constructor. Set the {@link FlyGoal Goal} properties
         *
         * @param pathAwareEntity The {@link PathAwareEntity entity assigned to the Goal}
         * @param speed The {@link Double Goal speed}
         */
        public FlyOntoTreeGoal(final PathAwareEntity pathAwareEntity, final double speed) {
            super(pathAwareEntity, speed);
        }

        /**
         * Get the {@link Vec3d target location}
         *
         * @return The {@link Vec3d target location}
         */
        @Nullable
        protected Vec3d getWanderTarget() {
            Vec3d target = null;
            if (this.mob.isTouchingWater()) {
                target = FuzzyTargeting.find(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                target = this.locateTree();
            }

            return target == null ? super.getWanderTarget() : target;
        }

        /**
         * Locate a tree
         *
         * @return The {@link Vec3d tree location}
         */
        @Nullable
        private Vec3d locateTree() {
            final BlockPos blockPos = this.mob.getBlockPos();
            final BlockPos.Mutable mutable = new BlockPos.Mutable();
            final BlockPos.Mutable mutable2 = new BlockPos.Mutable();

            for(BlockPos pos : BlockPos.iterate(MathHelper.floor(this.mob.getX() - (double)3.0F), MathHelper.floor(this.mob.getY() - (double)6.0F), MathHelper.floor(this.mob.getZ() - (double)3.0F), MathHelper.floor(this.mob.getX() + (double)3.0F), MathHelper.floor(this.mob.getY() + (double)6.0F), MathHelper.floor(this.mob.getZ() + (double)3.0F))) {
                if (!blockPos.equals(pos)) {
                    final BlockState blockState = this.mob.getWorld().getBlockState(mutable2.set(pos, Direction.DOWN));
                    if ((blockState.getBlock() instanceof LeavesBlock || blockState.isIn(BlockTags.LOGS)) && this.mob.getWorld().isAir(pos) && this.mob.getWorld().isAir(mutable.set(pos, Direction.UP))) {
                        return Vec3d.ofBottomCenter(pos);
                    }
                }
            }

            return null;
        }
    }

    /**
     * Butterfly variants
     */
    public enum Variant implements StringIdentifiable {
        RED(0, "red"),
        BLUE(1, "blue"),
        ORANGE(2, "orange"),
        PURPLE(3, "purple"),
        WHITE(4, "white");

        /**
         * The {@link Variant default variant}
         */
        public static final Variant DEFAULT = RED;
        /**
         * The {@link IntFunction<Variant> Variant index mapper}
         */
        private static final IntFunction<Variant> INDEX_MAPPER = ValueLists.createIndexToValueFunction(Variant::getIndex, values(), ValueLists.OutOfBoundsHandling.CLAMP);
        /**
         * The {@link Codec<Variant> Variant codec}
         */
        public static final Codec<Variant> CODEC = StringIdentifiable.createCodec(Variant::values);
        /**
         * The {@link Codec<Variant> Index Variant codec}
         */
        public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(INDEX_MAPPER::apply, Variant::getIndex);
        /**
         * The {@link PacketCodec Variant packet codec}
         */
        public static final PacketCodec<ByteBuf, ButterflyEntity.Variant> PACKET_CODEC = PacketCodecs.indexed(INDEX_MAPPER, Variant::getIndex);
        /**
         * The {@link Integer variant index}
         */
        final int index;
        /**
         * The {@link String variant name}
         */
        private final String name;

        /**
         * Constructor. Set the variant properties
         *
         * @param index The {@link Integer variant index}
         * @param name The {@link String variant name}
         */
        Variant(final int index, final String name) {
            this.index = index;
            this.name = name;
        }

        /**
         * Get the {@link Integer variant index}
         *
         * @return The {@link Integer variant index}
         */
        public int getIndex() {
            return this.index;
        }

        /**
         * Get a {@link Variant Variant} given its {@link Integer index}
         *
         * @return The {@link Integer variant index}
         */
        public static Variant byIndex(final int index) {
            return INDEX_MAPPER.apply(index);
        }

        /**
         * Get the {@link String variant name}
         *
         * @return The {@link String variant name}
         */
        public String asString() {
            return this.name;
        }

    }

}