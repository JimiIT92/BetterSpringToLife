package org.hendrix.betterspringdrop.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.hendrix.betterspringdrop.core.BSDTags;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation class for a {@link AmbientEntity Butterfly}
 */
public class ButterflyEntity extends PathAwareEntity implements Flutterer {

    public float flapProgress;
    public float maxWingDeviation;
    public float lastMaxWingDeviation;
    public float lastFlapProgress;
    private float flapSpeed = 1.0F;
    private float field_28640 = 1.0F;

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
     * Get the {@link Float pathfinding favor chance}
     *
     * @param pos The {@link BlockPos current Block Pos}
     * @param world The {@link WorldView World reference}
     * @return {@link Float 10.0F if is in air, otherwise 0}
     */
    public float getPathfindingFavor(final BlockPos pos, final WorldView world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(2, new ButterflyEntity.FlyOntoTreeGoal(this, (double)1.0F));
        this.goalSelector.add(3, new FollowMobGoal(this, (double)1.0F, 3.0F, 7.0F));
    }

    public static DefaultAttributeContainer.Builder createButterflyAttributes() {
        return AnimalEntity.createAnimalAttributes().add(EntityAttributes.MAX_HEALTH, (double)6.0F).add(EntityAttributes.FLYING_SPEED, (double)0.4F).add(EntityAttributes.MOVEMENT_SPEED, (double)0.2F).add(EntityAttributes.ATTACK_DAMAGE, (double)3.0F);
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        return birdNavigation;
    }

    public void tickMovement() {
        super.tickMovement();
        this.flapWings();
    }

    private void flapWings() {
        this.lastFlapProgress = this.flapProgress;
        this.lastMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation += (float)(!this.isOnGround() && !this.hasVehicle() ? 4 : -1) * 0.3F;
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0F, 1.0F);
        if (!this.isOnGround() && this.flapSpeed < 1.0F) {
            this.flapSpeed = 1.0F;
        }

        this.flapSpeed *= 0.9F;
        Vec3d vec3d = this.getVelocity();
        if (!this.isOnGround() && vec3d.y < (double)0.0F) {
            this.setVelocity(vec3d.multiply((double)1.0F, 0.6, (double)1.0F));
        }

        this.flapProgress += this.flapSpeed * 2.0F;
    }

    public static boolean canSpawn(EntityType<ButterflyEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(BSDTags.BlockTags.BUTTERFLIES_SPAWNABLE_ON) && world.getBaseLightLevel(pos, 0) > 8;
    }

    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    protected boolean isFlappingWings() {
        return this.speed > this.field_28640;
    }

    protected void addFlapEffects() {
        this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15F, 1.0F);
        this.field_28640 = this.speed + this.maxWingDeviation / 2.0F;
    }

    public boolean isPushable() {
        return true;
    }

    protected void pushAway(Entity entity) {
        if (!(entity instanceof PlayerEntity)) {
            super.pushAway(entity);
        }
    }

    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

    static class FlyOntoTreeGoal extends FlyGoal {
        public FlyOntoTreeGoal(PathAwareEntity pathAwareEntity, double d) {
            super(pathAwareEntity, d);
        }

        @Nullable
        protected Vec3d getWanderTarget() {
            Vec3d vec3d = null;
            if (this.mob.isTouchingWater()) {
                vec3d = FuzzyTargeting.find(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                vec3d = this.locateTree();
            }

            return vec3d == null ? super.getWanderTarget() : vec3d;
        }

        @Nullable
        private Vec3d locateTree() {
            BlockPos blockPos = this.mob.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            BlockPos.Mutable mutable2 = new BlockPos.Mutable();

            for(BlockPos blockPos2 : BlockPos.iterate(MathHelper.floor(this.mob.getX() - (double)3.0F), MathHelper.floor(this.mob.getY() - (double)6.0F), MathHelper.floor(this.mob.getZ() - (double)3.0F), MathHelper.floor(this.mob.getX() + (double)3.0F), MathHelper.floor(this.mob.getY() + (double)6.0F), MathHelper.floor(this.mob.getZ() + (double)3.0F))) {
                if (!blockPos.equals(blockPos2)) {
                    BlockState blockState = this.mob.getWorld().getBlockState(mutable2.set(blockPos2, Direction.DOWN));
                    boolean bl = blockState.getBlock() instanceof LeavesBlock || blockState.isIn(BlockTags.LOGS);
                    if (bl && this.mob.getWorld().isAir(blockPos2) && this.mob.getWorld().isAir(mutable.set(blockPos2, Direction.UP))) {
                        return Vec3d.ofBottomCenter(blockPos2);
                    }
                }
            }

            return null;
        }
    }

}
