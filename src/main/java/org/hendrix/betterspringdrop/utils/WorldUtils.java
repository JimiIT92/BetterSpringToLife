package org.hendrix.betterspringdrop.utils;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

/**
 * Utility methods for {@link World World}
 */
public final class WorldUtils {

    /**
     * Place a {@link Block}
     *
     * @param blockState The {@link BlockState Block State to place}
     * @param context The {@link ItemUsageContext Item Usage Context}
     * @param world The {@link World World reference}
     * @param player The {@link PlayerEntity Player that is replacing the Block }
     * @param blockPos The {@link BlockPos current Block Pos}
     * @param itemStack The {@link ItemStack used Item Stack}
     * @param sound The {@link SoundEvent Sound to play}
     * @return The {@link ActionResult Action Result}
     */
    public static ActionResult setBlock(final BlockState blockState, final ItemUsageContext context, final World world, final PlayerEntity player, final BlockPos blockPos, final ItemStack itemStack, final SoundEvent sound) {
        if(player != null) {
            if (player instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, blockPos, itemStack);
            }
            world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(player, blockState));
            if (itemStack != null && !itemStack.isEmpty()) {
                itemStack.damage(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }
            boolean isClient = world.isClient;
            if(isClient) {
                player.swingHand(context.getHand());
                player.playSound(sound, 1.0F, 1.0F);
            }
            return isClient ? ActionResult.SUCCESS : ActionResult.SUCCESS_SERVER;
        }
        return ActionResult.CONSUME;
    }

}