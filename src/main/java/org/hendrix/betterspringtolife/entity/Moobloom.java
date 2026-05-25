package org.hendrix.betterspringtolife.entity;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.core.BSTLEntityTypes;
import org.hendrix.betterspringtolife.core.BSTLSounds;
import org.jspecify.annotations.Nullable;

import java.util.Collections;

public class Moobloom extends AbstractCow implements Shearable {

    private static final EntityDataAccessor<Boolean> HAS_FLOWERS = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> FLOWER_REGROW_TIME = SynchedEntityData.defineId(Moobloom.class, EntityDataSerializers.INT);
    private static final int MAX_REGROW_TIME = 6000;

    public Moobloom(EntityType<? extends AbstractCow> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        if(!this.isBaby() && !this.hasFlowers()) {
            final int flowerGrowTime = this.entityData.get(FLOWER_REGROW_TIME) - 1;
            if(flowerGrowTime <= 0) {
                this.setHasFlowers(true);
            } else {
                this.entityData.set(FLOWER_REGROW_TIME, flowerGrowTime);
            }
        }
        super.tick();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.getAvailableGoals().stream().filter(goal -> goal.getGoal() instanceof TemptGoal).forEach(
                this.goalSelector::removeGoal
        );
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25F, this::isFood, false));
    }

    @Override
    public boolean isFood(final ItemStack itemStack) {
        return itemStack.is(BSTLBlocks.BUTTERCUP.asItem());
    }

    @Override
    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(HAS_FLOWERS, true);
        entityData.define(FLOWER_REGROW_TIME, MAX_REGROW_TIME);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand, Vec3 location) {
        final ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.BOWL) && !this.isBaby() && this.hasFlowers()) {
            final ItemStack suspiciousStew = new ItemStack(Items.SUSPICIOUS_STEW);
            suspiciousStew.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffects(Collections.singletonList(new SuspiciousStewEffects.Entry(MobEffects.ABSORPTION, 100))));
            this.playSound(BSTLSounds.ENTITY_MOOBLOOM_SUSPICIOUS_MILK, 1.0F, 1.0F);
            return InteractionResult.SUCCESS.heldItemTransformedTo(ItemUtils.createFilledResult(itemStack, player, suspiciousStew, false));
        }
        else if (itemStack.is(Items.SHEARS) && this.readyForShearing()) {
            final Level world = this.level();
            if (world instanceof ServerLevel serverLevel) {
                this.shear(serverLevel, SoundSource.PLAYERS, itemStack);
                this.gameEvent(GameEvent.SHEAR, player);
                itemStack.hurtAndBreak(1, player, hand);
                this.spawnAtLocation(serverLevel, new ItemStack(BSTLBlocks.BUTTERCUP, 4));
            }
            return InteractionResult.SUCCESS;
        }
        return super.interact(player, hand, location);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return BSTLEntityTypes.MOOBLOOM.create(level, EntitySpawnReason.BREEDING);
    }

    @Override
    public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {
        level.playSound(null, this, BSTLSounds.ENTITY_MOOBLOOM_SHEAR, soundSource, 1.0F, 1.0F);
        level.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        this.setHasFlowers(false);
        this.entityData.set(FLOWER_REGROW_TIME, MAX_REGROW_TIME);
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby() && this.hasFlowers();
    }

    private void setHasFlowers(final boolean hasFlowers) {
        this.entityData.set(HAS_FLOWERS, hasFlowers);
    }

    public boolean hasFlowers() {
        return this.entityData.get(HAS_FLOWERS);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("HasFlowers", this.hasFlowers());
        output.putInt("FlowerRegrowTime", this.entityData.get(FLOWER_REGROW_TIME));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setHasFlowers(input.getBooleanOr("HasFlowers", true));
        this.entityData.set(FLOWER_REGROW_TIME, input.getIntOr("FlowerRegrowTime", MAX_REGROW_TIME));
    }
}
