package org.hendrix.betterspringtolife.block;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.utils.BlockUtils;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.Optional;

/**
 * Implementation class for a hollow block
 */
public final class HollowBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {

    private static final Supplier<Map<Block, Block>> HOLLOWABLES = Suppliers.memoize(() -> ImmutableMap.<Block, Block>builder()
            .put(Blocks.OAK_LOG, BSTLBlocks.HOLLOW_OAK_LOG)
            .put(Blocks.SPRUCE_LOG, BSTLBlocks.HOLLOW_SPRUCE_LOG)
            .put(Blocks.BIRCH_LOG, BSTLBlocks.HOLLOW_BIRCH_LOG)
            .put(Blocks.JUNGLE_LOG, BSTLBlocks.HOLLOW_JUNGLE_LOG)
            .put(Blocks.ACACIA_LOG, BSTLBlocks.HOLLOW_ACACIA_LOG)
            .put(Blocks.DARK_OAK_LOG, BSTLBlocks.HOLLOW_DARK_OAK_LOG)
            .put(Blocks.MANGROVE_LOG, BSTLBlocks.HOLLOW_MANGROVE_LOG)
            .put(Blocks.BAMBOO_BLOCK, BSTLBlocks.HOLLOW_BAMBOO_BLOCK)
            .put(Blocks.CHERRY_LOG, BSTLBlocks.HOLLOW_CHERRY_LOG)
            .put(Blocks.PALE_OAK_LOG, BSTLBlocks.HOLLOW_PALE_OAK_LOG)
            .put(Blocks.CRIMSON_STEM, BSTLBlocks.HOLLOW_CRIMSON_STEM)
            .put(Blocks.WARPED_STEM, BSTLBlocks.HOLLOW_WARPED_STEM)
    .build());

    /**
     * Constructor. Set the block properties
     *
     * @param properties The {@link Properties}
     */
    public HollowBlock(final Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public @NonNull BlockState getStateForPlacement(@NonNull BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(BlockStateProperties.WATERLOGGED, BlockUtils.isInWater(context.getLevel(), context.getClickedPos()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    protected @NonNull FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected @NonNull VoxelShape getShape(BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return switch (state.getValue(RotatedPillarBlock.AXIS)) {
            case X -> Shapes.or(
                    Block.box(0, 12, 4, 16, 15, 12),
                    Block.box(0, 1, 4, 16, 4, 12),
                    Block.box(0, 0, 15, 16, 16, 16),
                    Block.box(0, 0, 0, 16, 16, 1),
                    Block.box(0, 0, 1, 16, 1, 15),
                    Block.box(0, 15, 1, 16, 16, 15),
                    Block.box(0, 1, 12, 16, 15, 15),
                    Block.box(0, 1, 1, 16, 15, 4)
            );
            case Y -> Shapes.or(
                    Block.box(4, 0, 12, 12, 16, 15),
                    Block.box(4, 0, 1, 12, 16, 4),
                    Block.box(0, 0, 0, 1, 16, 16),
                    Block.box(15, 0, 0, 16, 16, 16),
                    Block.box(1, 0, 0, 15, 16, 1),
                    Block.box(1, 0, 15, 15, 16, 16),
                    Block.box(1, 0, 1, 4, 16, 15),
                    Block.box(12, 0, 1, 15, 16, 15)
            );
            case Z -> Shapes.or(
                    Block.box(4, 12, 0, 12, 15, 16),
                    Block.box(4, 1, 0, 12, 4, 16),
                    Block.box(0, 0, 0, 1, 16, 16),
                    Block.box(15, 0, 0, 16, 16, 16),
                    Block.box(1, 0, 0, 15, 1, 16),
                    Block.box(1, 15, 0, 15, 16, 16),
                    Block.box(1, 1, 0, 4, 15, 16),
                    Block.box(12, 1, 0, 15, 15, 16)
            );
        };
    }

    public static Optional<BlockState> getHollow(final BlockState blockState) {
        return Optional.ofNullable(HollowBlock.HOLLOWABLES.get().get(blockState.getBlock())).map(block -> block.withPropertiesOf(blockState));
    }
}
