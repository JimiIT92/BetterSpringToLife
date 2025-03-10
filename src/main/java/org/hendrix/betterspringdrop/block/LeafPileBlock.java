package org.hendrix.betterspringdrop.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.hendrix.betterspringdrop.core.BSDTags;

/**
 * Implementation class for a {@link Block Leaf Pile Block}
 */
public class LeafPileBlock extends SnowBlock implements Waterloggable {

    /**
     * The {@link Waterloggable Waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    public LeafPileBlock(final Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LAYERS, 1).with(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Check if the {@link Block Block} can survive at the current {@link BlockPos Block Pos}
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link WorldView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @return {@link Boolean True if the Block can survive}
     */
    @Override
    protected boolean canPlaceAt(final BlockState state, final WorldView world, final BlockPos pos) {
        final BlockState blockState = world.getBlockState(pos.down());
        if (blockState.isIn(BSDTags.BlockTags.LEAF_PILE_CANNOT_SURVIVE_ON)) {
            return false;
        } else if (blockState.isIn(BSDTags.BlockTags.LEAF_PILE_CAN_SURVIVE_ON)) {
            return true;
        } else {
            return Block.isFaceFullSquare(blockState.getCollisionShape(world, pos.down()), Direction.UP) || blockState.isOf(this) && blockState.get(LAYERS) == 8;
        }
    }

    /**
     * Get the {@link BlockState placement state} of the {@link Block Block}
     *
     * @param context The {@link ItemPlacementContext Placement Context}
     * @return The {@link BlockState Placed Block State}
     */
    @Override
    public BlockState getPlacementState(final ItemPlacementContext context) {
        final BlockState blockState = super.getPlacementState(context);
        return blockState == null ? null : blockState.with(WATERLOGGED, Fluids.WATER.equals(context.getWorld().getFluidState(context.getBlockPos()).getFluid()));
    }

    /**
     * Register the {@link Block Block} Properties
     *
     * @param builder The {@link StateManager.Builder State Builder}
     */
    @Override
    protected void appendProperties(final StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
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
     * Prevent the block from "melting"
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link World World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param random The {@link Random Random reference}
     */
    @Override
    protected void randomTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {

    }

}