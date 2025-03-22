package org.hendrix.betterspringtolife.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.hendrix.betterspringtolife.core.BSTLBlocks;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation class for an {@link PillarBlock Hollow Block}
 */
public final class HollowBlock extends PillarBlock implements Waterloggable {

    /**
     * The {@link Supplier<Map> Map Supplier} for the {@link Block Hollow Blocks}
     */
    private static final Supplier<Map<Block, Block>> HOLLOWABLES = Suppliers.memoize(() -> ImmutableMap.<Block, Block>builder()
            .put(Blocks.OAK_LOG, BSTLBlocks.HOLLOW_OAK_LOG)
            .put(Blocks.STRIPPED_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_OAK_LOG)
            .put(BSTLBlocks.HOLLOW_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_OAK_LOG)
            .put(Blocks.SPRUCE_LOG, BSTLBlocks.HOLLOW_SPRUCE_LOG)
            .put(Blocks.STRIPPED_SPRUCE_LOG, BSTLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG)
            .put(BSTLBlocks.HOLLOW_SPRUCE_LOG, BSTLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG)
            .put(Blocks.BIRCH_LOG, BSTLBlocks.HOLLOW_BIRCH_LOG)
            .put(Blocks.STRIPPED_BIRCH_LOG, BSTLBlocks.HOLLOW_STRIPPED_BIRCH_LOG)
            .put(BSTLBlocks.HOLLOW_BIRCH_LOG, BSTLBlocks.HOLLOW_STRIPPED_BIRCH_LOG)
            .put(Blocks.JUNGLE_LOG, BSTLBlocks.HOLLOW_JUNGLE_LOG)
            .put(Blocks.STRIPPED_JUNGLE_LOG, BSTLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG)
            .put(BSTLBlocks.HOLLOW_JUNGLE_LOG, BSTLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG)
            .put(Blocks.ACACIA_LOG, BSTLBlocks.HOLLOW_ACACIA_LOG)
            .put(Blocks.STRIPPED_ACACIA_LOG, BSTLBlocks.HOLLOW_STRIPPED_ACACIA_LOG)
            .put(BSTLBlocks.HOLLOW_ACACIA_LOG, BSTLBlocks.HOLLOW_STRIPPED_ACACIA_LOG)
            .put(Blocks.DARK_OAK_LOG, BSTLBlocks.HOLLOW_DARK_OAK_LOG)
            .put(Blocks.STRIPPED_DARK_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG)
            .put(BSTLBlocks.HOLLOW_DARK_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG)
            .put(Blocks.MANGROVE_LOG, BSTLBlocks.HOLLOW_MANGROVE_LOG)
            .put(Blocks.STRIPPED_MANGROVE_LOG, BSTLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG)
            .put(BSTLBlocks.HOLLOW_MANGROVE_LOG, BSTLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG)
            .put(Blocks.BAMBOO_BLOCK, BSTLBlocks.HOLLOW_BAMBOO_BLOCK)
            .put(Blocks.STRIPPED_BAMBOO_BLOCK, BSTLBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK)
            .put(BSTLBlocks.HOLLOW_BAMBOO_BLOCK, BSTLBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK)
            .put(Blocks.CHERRY_LOG, BSTLBlocks.HOLLOW_CHERRY_LOG)
            .put(Blocks.STRIPPED_CHERRY_LOG, BSTLBlocks.HOLLOW_STRIPPED_CHERRY_LOG)
            .put(BSTLBlocks.HOLLOW_CHERRY_LOG, BSTLBlocks.HOLLOW_STRIPPED_CHERRY_LOG)
            .put(Blocks.PALE_OAK_LOG, BSTLBlocks.HOLLOW_PALE_OAK_LOG)
            .put(Blocks.STRIPPED_PALE_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_PALE_OAK_LOG)
            .put(BSTLBlocks.HOLLOW_PALE_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_PALE_OAK_LOG)
            .put(Blocks.CRIMSON_STEM, BSTLBlocks.HOLLOW_CRIMSON_STEM)
            .put(Blocks.STRIPPED_CRIMSON_STEM, BSTLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM)
            .put(BSTLBlocks.HOLLOW_CRIMSON_STEM, BSTLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM)
            .put(Blocks.WARPED_STEM, BSTLBlocks.HOLLOW_WARPED_STEM)
            .put(Blocks.STRIPPED_WARPED_STEM, BSTLBlocks.HOLLOW_STRIPPED_WARPED_STEM)
            .put(BSTLBlocks.HOLLOW_WARPED_STEM, BSTLBlocks.HOLLOW_STRIPPED_WARPED_STEM)
        .build());

    /**
     * The {@link Waterloggable Waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    public HollowBlock(final Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y).with(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Get the {@link BlockState placement state} of the {@link Block Block}
     *
     * @param context The {@link ItemPlacementContext Placement Context}
     * @return The {@link BlockState Placed Block State}
     */
    @Override
    public BlockState getPlacementState(final ItemPlacementContext context) {
        return this.getDefaultState().with(AXIS, context.getSide().getAxis()).with(WATERLOGGED, Fluids.WATER.equals(context.getWorld().getFluidState(context.getBlockPos()).getFluid()));
    }

    /**
     * Register the {@link Block Block} Properties
     *
     * @param builder The {@link StateManager.Builder State Builder}
     */
    @Override
    protected void appendProperties(final StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, WATERLOGGED);
    }

    /**
     * Get the {@link FluidState Fluid State} of the {@link Block Block}
     *
     * @param state The {@link BlockState current Block State}
     * @return The {@link FluidState Fluid State}
     */
    public FluidState getFluidState(final BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    /**
     * Get the {@link Integer Block opacity}
     *
     * @param state The {@link BlockState current Block State}
     * @return The {@link Integer Block opacity}
     */
    @Override
    protected int getOpacity(final BlockState state) {
        return 1;
    }

    /**
     * Get the {@link VoxelShape Block Shape} based on its {@link Direction.Axis Axis}
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link BlockView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param context The {@link ShapeContext Block Shape Context}
     * @return The {@link VoxelShape Block Shape}
     */
    @Override
    public VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
        return switch (state.get(AXIS)) {
            case X -> VoxelShapes.union(
                    Block.createCuboidShape(0, 12, 4, 16, 15, 12),
                    Block.createCuboidShape(0, 1, 4, 16, 4, 12),
                    Block.createCuboidShape(0, 0, 15, 16, 16, 16),
                    Block.createCuboidShape(0, 0, 0, 16, 16, 1),
                    Block.createCuboidShape(0, 0, 1, 16, 1, 15),
                    Block.createCuboidShape(0, 15, 1, 16, 16, 15),
                    Block.createCuboidShape(0, 1, 12, 16, 15, 15),
                    Block.createCuboidShape(0, 1, 1, 16, 15, 4)
            );
            case Y -> VoxelShapes.union(
                    Block.createCuboidShape(4, 0, 12, 12, 16, 15),
                    Block.createCuboidShape(4, 0, 1, 12, 16, 4),
                    Block.createCuboidShape(0, 0, 0, 1, 16, 16),
                    Block.createCuboidShape(15, 0, 0, 16, 16, 16),
                    Block.createCuboidShape(1, 0, 0, 15, 16, 1),
                    Block.createCuboidShape(1, 0, 15, 15, 16, 16),
                    Block.createCuboidShape(1, 0, 1, 4, 16, 15),
                    Block.createCuboidShape(12, 0, 1, 15, 16, 15)
            );
            case Z -> VoxelShapes.union(
                    Block.createCuboidShape(4, 12, 0, 12, 15, 16),
                    Block.createCuboidShape(4, 1, 0, 12, 4, 16),
                    Block.createCuboidShape(0, 0, 0, 1, 16, 16),
                    Block.createCuboidShape(15, 0, 0, 16, 16, 16),
                    Block.createCuboidShape(1, 0, 0, 15, 1, 16),
                    Block.createCuboidShape(1, 15, 0, 15, 16, 16),
                    Block.createCuboidShape(1, 1, 0, 4, 15, 16),
                    Block.createCuboidShape(12, 1, 0, 15, 15, 16)
            );
        };
    }

    /**
     * Get the {@link Optional<BlockState> Hollow Block State} for the provided {@link BlockState Block State}
     *
     * @param state The {@link BlockState current Block State}
     * @return The {@link Optional<BlockState> Hollow Block State}, if any
     */
    public static Optional<BlockState> getHollow(final BlockState state) {
        return Optional.ofNullable(HollowBlock.HOLLOWABLES.get().get(state.getBlock())).map(block -> block.getStateWithProperties(state));
    }

}