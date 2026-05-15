package org.hendrix.betterspringtolife.core;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.block.HollowBlock;

/**
 * {@link BetterSpringToLife} events
 */
public final class BSTLEvents {

    //#region Events

    private static InteractionResult hollowLog(Player player, Level level, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        final ItemStack itemStack = player.getItemInHand(interactionHand);
        if(player.isShiftKeyDown() && itemStack.is(ItemTags.AXES)) {
            final BlockPos pos = blockHitResult.getBlockPos();
            final BlockState blockState = level.getBlockState(pos);
            return HollowBlock.getHollow(blockState).map(
                  hollowBlock -> setBlock(hollowBlock, player, interactionHand, level, pos, itemStack, SoundEvents.AXE_STRIP)
            ).orElse(InteractionResult.PASS);
        }
        return InteractionResult.PASS;
    }

    //#endregion

    private static InteractionResult setBlock(final BlockState blockState, final Player player, final InteractionHand hand, final Level level, final BlockPos pos, final ItemStack itemStack, final SoundEvent soundEvent) {
        level.setBlockAndUpdate(pos, blockState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
        if(player != null) {
            if(player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
            }
            if(itemStack != null && !itemStack.isEmpty()) {
                if(itemStack.isDamageableItem()) {
                    itemStack.hurtAndBreak(1, player, hand);
                } else {
                    itemStack.consume(1, player);
                }
            }
            final boolean isClient = level.isClientSide();
            if(isClient) {
                player.swing(hand);
                player.playSound(soundEvent);
            }
            return isClient ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.PASS;
    }

    public static void register() {
        UseBlockCallback.EVENT.register(BSTLEvents::hollowLog);
    }

}