package org.hendrix.betterspringtolife.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluids;

/**
 * Utility methods for {@link Block}
 */
public final class BlockUtils {

    /**
     * Check whether a Block is in water
     *
     * @param level The {@link Level} reference
     * @param pos The {@link BlockPos}
     * @return True if the block is in water
     */
    public static boolean isInWater(final Level level, final BlockPos pos) {
        return level.getFluidState(pos).is(Fluids.WATER);
    }

}