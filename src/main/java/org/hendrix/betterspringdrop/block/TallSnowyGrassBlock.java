package org.hendrix.betterspringdrop.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.hendrix.betterspringdrop.core.BSDBlocks;

/**
 * Implementation class for a tall {@link SnowyVegetationBlock Snowy Grass Block}
 */
public class TallSnowyGrassBlock extends SnowyVegetationBlock implements Fertilizable {

    /**
     * The {@link MapCodec Block Codec}
     */
    public static final MapCodec<TallSnowyGrassBlock> CODEC = createCodec(TallSnowyGrassBlock::new);

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    public TallSnowyGrassBlock(final Settings settings) {
        super(settings);
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
        return Block.createColumnShape(14.0D, 0.0D, 16.0D);
    }

    /**
     * Check if the {@link Block Block} can be fertilized
     *
     * @param world The {@link WorldView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param state The {@link BlockState current Block State}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isFertilizable(final WorldView world, final BlockPos pos, final BlockState state) {
        return Fertilizable.canSpread(world, pos, BSDBlocks.SHORT_SNOWY_GRASS.getDefaultState());
    }

    /**
     * Check if the {@link Block Block} can grow
     *
     * @param world The {@link World World reference}
     * @param random The {@link Random Random reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param state The {@link BlockState current Block State}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean canGrow(final World world, final Random random, final BlockPos pos, final BlockState state) {
        return true;
    }

    /**
     * Make the {@link Block Block} grow
     *
     * @param world The {@link ServerWorld World reference}
     * @param random The {@link Random Random reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param state The {@link BlockState current Block State}
     */
    @Override
    public void grow(final ServerWorld world, final Random random, final BlockPos pos, final BlockState state) {
        Fertilizable.findPosToSpreadTo(world, pos, BSDBlocks.SHORT_SNOWY_GRASS.getDefaultState()).ifPresent((blockPos) -> world.setBlockState(blockPos, BSDBlocks.SHORT_SNOWY_GRASS.getDefaultState()));
    }

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return The {@link #CODEC Block Codec}
     */
    @Override
    public MapCodec<TallSnowyGrassBlock> getCodec() {
        return CODEC;
    }

}
