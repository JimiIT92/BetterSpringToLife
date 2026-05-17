package org.hendrix.betterspringtolife.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.hendrix.betterspringtolife.core.BSTLTags;

public class SnowyVegetationBlock extends VegetationBlock {

    private static final MapCodec<SnowyVegetationBlock> CODEC = simpleCodec(SnowyVegetationBlock::new);

    protected SnowyVegetationBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return state.is(BSTLTags.BlockTags.SNOWY_VEGETATION_MAY_PLACE_ON);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }
}
