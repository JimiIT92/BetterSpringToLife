package org.hendrix.betterspringdrop.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.hendrix.betterspringdrop.block.HollowBlock;
import org.hendrix.betterspringdrop.utils.WorldUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * Mixin class for the {@link AxeItem Axe Item}
 */
@Mixin(AxeItem.class)
public final class AxeItemMixin {

    /**
     * Make a {@link PillarBlock Log} hollow or an {@link HollowBlock Hollow Block} stripped on right click
     *
     * @param context The {@link ItemUsageContext Item Usage Context}
     * @param callbackInfoReturnable The {@link CallbackInfoReturnable<ActionResult> Action Result Callback Info Returnable}
     */
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void useOnBlock(final ItemUsageContext context, final CallbackInfoReturnable<ActionResult> callbackInfoReturnable) {
        final PlayerEntity player = context.getPlayer();
        if(player != null) {
            final World world = context.getWorld();
            final BlockPos blockPos = context.getBlockPos();
            final BlockState blockState = world.getBlockState(blockPos);
            if(player.isSneaking() || blockState.getBlock() instanceof HollowBlock) {
                final Optional<ActionResult> result = HollowBlock.getHollow(blockState).map(hollowBlockState -> WorldUtils.setBlock(hollowBlockState, context, world, player, blockPos, context.getStack(), SoundEvents.ITEM_AXE_STRIP));
                result.ifPresent(callbackInfoReturnable::setReturnValue);
            }
        }
    }

}