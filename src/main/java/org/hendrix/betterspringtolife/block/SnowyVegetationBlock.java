package org.hendrix.betterspringtolife.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.hendrix.betterspringtolife.core.BSTLTags;

public class SnowyVegetationBlock extends VegetationBlock {

    protected SnowyVegetationBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return state.is(BSTLTags.BlockTags.SNOWY_VEGETATION_MAY_PLACE_ON);
    }

}
