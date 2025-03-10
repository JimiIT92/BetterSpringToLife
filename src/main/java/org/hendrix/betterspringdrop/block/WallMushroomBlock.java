package org.hendrix.betterspringdrop.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

/**
 * Implementation class for a {@link WallMushroomBlock Wall Mushroom Block}
 */
public class WallMushroomBlock extends DeadCoralWallFanBlock implements Waterloggable {

    /**
     * The {@link Waterloggable Waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    /**
     * The {@link Integer maximum amount of mushrooms that can be placed}
     */
    public static final int MAX_MUSHROOMS = 4;
    /**
     * The {@link IntProperty Mushrooms property}
     */
    public static final IntProperty MUSHROOMS = IntProperty.of("mushrooms", 1, MAX_MUSHROOMS);
    /**
     * The {@link Block mushroom Block}
     */
    private final Block mushroomBlock;

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param mushroomBlock The {@link Block mushroom Block}
     * @param settings The {@link Settings Block Settings}
     */
    public WallMushroomBlock(final Block mushroomBlock, final Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(MUSHROOMS, 1).with(WATERLOGGED, false).with(FACING, Direction.NORTH));
        this.mushroomBlock = mushroomBlock;
    }

    /**
     * Register the {@link Block Block} Properties
     *
     * @param builder The {@link StateManager.Builder State Builder}
     */
    @Override
    protected void appendProperties(final StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(MUSHROOMS);
    }

    /**
     * Get the {@link VoxelShape Block Shape}
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link BlockView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param context The {@link ShapeContext Block Shape Context}
     * @return The {@link VoxelShape Block Shape}
     */
    @Override
    protected VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
            case WEST -> Block.createCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
            case EAST -> Block.createCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
            default -> Block.createCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        };
    }

    /**
     * Get the {@link ItemStack Item Stack} associated with the middle-mouse click action
     *
     * @param world The {@link WorldView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param state The {@link BlockState current Block State}
     * @param includeData {@link Boolean Whether custom data should be included in the Item Stack}
     * @return The {@link #mushroomBlock mushroom Block Item Stack}
     */
    @Override
    protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {
        return this.mushroomBlock.asItem().getDefaultStack();
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

}