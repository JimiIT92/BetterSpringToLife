package org.hendrix.betterspringtolife.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.hendrix.betterspringtolife.core.BSTLEntityTypes;
import org.hendrix.betterspringtolife.core.BSTLSounds;
import org.jspecify.annotations.Nullable;

public class MuddyPig extends Pig implements Shearable {

    private static final EntityDataAccessor<Boolean> HAS_FLOWERS = SynchedEntityData.defineId(MuddyPig.class, EntityDataSerializers.BOOLEAN);

    public MuddyPig(EntityType<? extends Pig> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(HAS_FLOWERS, true);
    }

    @Override
    public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {
        level.playSound(null, this, BSTLSounds.ENTITY_MOOBLOOM_SHEAR, soundSource, 1.0F, 1.0F);
        level.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        this.setHasFlowers(false);
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
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setHasFlowers(input.getBooleanOr("HasFlowers", true));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        this.setHasFlowers(this.random.nextBoolean());
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    @Override
    public @Nullable Pig getBreedOffspring(ServerLevel level, AgeableMob partner) {
        MuddyPig child = BSTLEntityTypes.MUDDY_PIG.create(level, EntitySpawnReason.BREEDING);
        if(child != null) {
            child.setHasFlowers(false);
        }
        return child;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        final ItemStack itemStack = player.getItemInHand(hand);
        final Level world = this.level();
        if (world instanceof ServerLevel serverLevel) {
            if (itemStack.is(Items.SHEARS) && this.readyForShearing()) {
                this.shear(serverLevel, SoundSource.PLAYERS, itemStack);
                this.gameEvent(GameEvent.SHEAR, player);
                itemStack.hurtAndBreak(1, player, hand);
                this.spawnAtLocation(serverLevel, new ItemStack(Blocks.POPPY, 1));
                return InteractionResult.SUCCESS;
            }
            else if (itemStack.is(Items.WATER_BUCKET)) {
                world.playSound(null, player, SoundEvents.BUCKET_EMPTY, SoundSource.PLAYERS, 1.0F, 1.0F);
                this.clean(serverLevel);
                //player.setItemInHand(hand, InteractionResult.ItemContext.exchangeStack(itemStack, player, new ItemStack(Items.BUCKET)));
                return InteractionResult.SUCCESS.heldItemTransformedTo(ItemUtils.createFilledResult(itemStack, player, new ItemStack(Items.BUCKET), false));
            }
        }

        return super.mobInteract(player, hand);
    }

    private void clean(final ServerLevel world) {
        world.sendParticles(ParticleTypes.FALLING_WATER, this.getX(), this.getY(0.5D) + 0.5D, this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
        this.convertTo(EntityType.PIG, ConversionParams.single(this, false, false), (pig) -> { });
    }

}
