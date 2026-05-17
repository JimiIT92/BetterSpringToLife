package org.hendrix.betterspringtolife.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.NonNull;

public final class LeafPileBlock extends SnowLayerBlock implements SimpleWaterloggedBlock {

    public LeafPileBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LAYERS, 1).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        return Block.isFaceFull(belowState.getCollisionShape(level, pos.below()), Direction.UP) || belowState.is(this) && belowState.getValue(LAYERS) == 8;
    }

    @Override
    protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {

    }

    @Override
    public @NonNull BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
        FluidState replacedFluidState = context.getLevel().getFluidState(context.getClickedPos());
        return super.getStateForPlacement(context).setValue(BlockStateProperties.WATERLOGGED, replacedFluidState.is(Fluids.WATER));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    protected @NonNull FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(true) : super.getFluidState(state);
    }
}
