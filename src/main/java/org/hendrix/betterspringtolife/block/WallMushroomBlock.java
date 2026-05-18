package org.hendrix.betterspringtolife.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class WallMushroomBlock extends BaseCoralWallFanBlock {

    /**
     * The {@link Integer maximum amount of mushrooms that can be placed}
     */
    public static final int MAX_MUSHROOMS = 4;
    /**
     * The {@link IntegerProperty Mushrooms property}
     */
    public static final IntegerProperty MUSHROOMS = IntegerProperty.create("mushrooms", 1, MAX_MUSHROOMS);
    /**
     * The {@link Block mushroom Block}
     */
    private final Block mushroomBlock;

    public WallMushroomBlock(final Block mushroomBlock, final Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(MUSHROOMS, 1).setValue(WATERLOGGED, true));
        this.mushroomBlock = mushroomBlock;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(MUSHROOMS);
    }

    @Override
    protected @NonNull VoxelShape getShape(BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
            case WEST -> Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
            case EAST -> Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
            default -> Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        };
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return this.mushroomBlock.asItem().getDefaultInstance();
    }

}
