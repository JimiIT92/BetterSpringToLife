package org.hendrix.betterspringtolife.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin class for the {@link CactusBlock Cactus Block}
 */
@Mixin(CactusBlock.class)
public final class CactusBlockMixin {

    /**
     * Spawn a {@link BSTLBlocks#PRICKLY_PEAR Prickly Pear Block} on top of a {@link CactusBlock Cactus Block}
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link World World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param random The {@link Random Random reference}
     * @param callbackInfo The {@link CallbackInfo Callback Info}
     */
    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"), cancellable = true)
    public void randomTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random, final CallbackInfo callbackInfo) {
        if(random.nextDouble() <= 0.3D) {
            callbackInfo.cancel();
            world.setBlockState(pos.up(), BSTLBlocks.PRICKLY_PEAR.getDefaultState());
        }
    }

}