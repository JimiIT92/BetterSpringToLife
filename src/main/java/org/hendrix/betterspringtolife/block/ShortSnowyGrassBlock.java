package org.hendrix.betterspringtolife.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.hendrix.betterspringtolife.core.BSTLBlocks;

/**
 * Implementation class for a short {@link SnowyVegetationBlock Snowy Grass Block}
 */
public final class ShortSnowyGrassBlock extends SnowyVegetationBlock implements Fertilizable {

    /**
     * The {@link MapCodec Block Codec}
     */
    public static final MapCodec<ShortSnowyGrassBlock> CODEC = createCodec(ShortSnowyGrassBlock::new);

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    public ShortSnowyGrassBlock(final Settings settings) {
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
        return Block.createColumnShape(12.0D, 0.0D, 10.0D);
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
        return true;
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
        world.setBlockState(pos, BSTLBlocks.TALL_SNOWY_GRASS.getDefaultState());
    }

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return The {@link #CODEC Block Codec}
     */
    @Override
    public MapCodec<ShortSnowyGrassBlock> getCodec() {
        return CODEC;
    }

}
