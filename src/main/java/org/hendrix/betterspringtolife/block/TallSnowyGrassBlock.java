package org.hendrix.betterspringtolife.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.hendrix.betterspringtolife.core.BSTLBlocks;

public class TallSnowyGrassBlock extends SnowyVegetationBlock implements BonemealableBlock {

    public TallSnowyGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.column(14, 0, 16);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return BonemealableBlock.hasSpreadableNeighbourPos(level, pos, BSTLBlocks.SHORT_SNOWY_GRASS.defaultBlockState());
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BonemealableBlock.findSpreadableNeighbourPos(level, pos, BSTLBlocks.SHORT_SNOWY_GRASS.defaultBlockState()).ifPresent(neighbourPos -> level.setBlockAndUpdate(neighbourPos, BSTLBlocks.SHORT_SNOWY_GRASS.defaultBlockState()));
    }
}
