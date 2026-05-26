package org.hendrix.betterspringtolife.entity;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.hendrix.betterspringtolife.core.BSTLDataComponentTypes;
import org.hendrix.betterspringtolife.core.BSTLTags;
import org.jspecify.annotations.Nullable;

import java.util.function.IntFunction;

public class Butterfly extends PathfinderMob implements FlyingAnimal {

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Butterfly.class, EntityDataSerializers.INT);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;
    private float nextFlap = 1.0F;

    public Butterfly(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(PathType.FIRE_IN_NEIGHBOR, -1.0F);
        this.setPathfindingMalus(PathType.FIRE, -1.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25F, itemStack -> itemStack.is(ItemTags.SMALL_FLOWERS), false));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new Butterfly.FlyOntoTreeGoal(this, 1.0F));
        this.goalSelector.addGoal(4, new FollowMobGoal(this, (double)1.0F, 3.0F, 7.0F));
    }

    @Override
    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(VARIANT, Butterfly.Variant.DEFAULT.index);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("Variant", Butterfly.Variant.INDEX_CODEC, this.getVariant());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setVariant(input.read("Variant", Butterfly.Variant.INDEX_CODEC).orElse(Butterfly.Variant.DEFAULT));
    }

    public Butterfly.Variant getVariant() {
        return Butterfly.Variant.byIndex(this.entityData.get(VARIANT));
    }

    public void setVariant(Butterfly.Variant variant) {
        this.entityData.set(VARIANT, variant.index);
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter components) {
        this.applyImplicitComponentIfPresent(components, BSTLDataComponentTypes.BUTTERFLY_VARIANT);
        super.applyImplicitComponents(components);
    }

    @Override
    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        if(BSTLDataComponentTypes.BUTTERFLY_VARIANT.equals(type)) {
            this.setVariant(castComponentValue(BSTLDataComponentTypes.BUTTERFLY_VARIANT, value));
            return true;
        }
        return super.applyImplicitComponent(type, value);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes()
                .add(Attributes.MAX_HEALTH, 1.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.FLYING_SPEED, 0.4F);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        this.setVariant(Util.getRandom(Butterfly.Variant.values(), level.getRandom()));
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    protected PathNavigation createNavigation(final Level level) {
        FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level);
        flyingPathNavigation.setCanOpenDoors(false);
        flyingPathNavigation.setCanFloat(true);
        return flyingPathNavigation;
    }

    public void aiStep() {
        super.aiStep();
        this.calculateFlapping();
    }

    private void calculateFlapping() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed += (float)(!this.onGround() && !this.isPassenger() ? 4 : -1) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.5F;
        Vec3 movement = this.getDeltaMovement();
        if (!this.onGround() && movement.y < (double)0.0F) {
            this.setDeltaMovement(movement.multiply((double)1.0F, 0.6, (double)1.0F));
        }

        this.flap += this.flapping * 2.0F;
    }

    protected void checkFallDamage(final double ya, final boolean onGround, final BlockState onState, final BlockPos pos) {
    }

    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    public boolean isPushable() {
        return true;
    }

    protected void doPush(final Entity entity) {
        if (!(entity instanceof Player)) {
            super.doPush(entity);
        }
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    public static boolean checkButterflySpawnRules(final EntityType<Butterfly> type, final LevelAccessor level, final EntitySpawnReason spawnReason, final BlockPos pos, final RandomSource random) {
        return level.getBlockState(pos.below()).is(BSTLTags.BlockTags.BUTTERFLIES_SPAWNABLE_ON) && level.getRawBrightness(pos, 0) > 8;
    }

    static class FlyOntoTreeGoal extends WaterAvoidingRandomFlyingGoal {

        public FlyOntoTreeGoal(PathfinderMob mob, double speedModifier) {
            super(mob, speedModifier);
        }

        protected @Nullable Vec3 getPosition() {
            Vec3 pos = null;
            if (this.mob.isInWater()) {
                pos = LandRandomPos.getPos(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                pos = this.getTreePos();
            }

            return pos == null ? super.getPosition() : pos;
        }

        private @Nullable Vec3 getTreePos() {
            BlockPos mobPos = this.mob.blockPosition();
            BlockPos.MutableBlockPos abovePos = new BlockPos.MutableBlockPos();
            BlockPos.MutableBlockPos belowPos = new BlockPos.MutableBlockPos();

            for(BlockPos pos : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - (double)3.0F), Mth.floor(this.mob.getY() - (double)6.0F), Mth.floor(this.mob.getZ() - (double)3.0F), Mth.floor(this.mob.getX() + (double)3.0F), Mth.floor(this.mob.getY() + (double)6.0F), Mth.floor(this.mob.getZ() + (double)3.0F))) {
                if (!mobPos.equals(pos)) {
                    BlockState state = this.mob.level().getBlockState(belowPos.setWithOffset(pos, Direction.DOWN));
                    boolean canSitOn = state.getBlock() instanceof LeavesBlock || state.is(BlockTags.LOGS);
                    if (canSitOn && this.mob.level().isEmptyBlock(pos) && this.mob.level().isEmptyBlock(abovePos.setWithOffset(pos, Direction.UP))) {
                        return Vec3.atBottomCenterOf(pos);
                    }
                }
            }

            return null;
        }
    }

    public enum Variant implements StringRepresentable {
        RED(0, "red"),
        BLUE(1, "blue"),
        ORANGE(2, "orange"),
        PURPLE(3, "purple"),
        WHITE(4, "white");

        public static final Butterfly.Variant DEFAULT = RED;
        private static final IntFunction<Butterfly.Variant> INDEX_MAPPER = ByIdMap.continuous(Butterfly.Variant::getIndex, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        public static final Codec<Butterfly.Variant> CODEC = StringRepresentable.fromEnum(Butterfly.Variant::values);
        public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(INDEX_MAPPER::apply, Variant::getIndex);
        public static final StreamCodec<ByteBuf, Butterfly.Variant> PACKET_CODEC = ByteBufCodecs.idMapper(INDEX_MAPPER, Butterfly.Variant::getIndex);
        private final int index;
        private final String name;

        private Variant(final int index, final String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return this.index;
        }

        public static Butterfly.Variant byIndex(final int index) {
            return INDEX_MAPPER.apply(index);
        }

        public String getSerializedName() {
            return this.name;
        }

    }
}
