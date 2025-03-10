package org.hendrix.betterspringdrop.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.hendrix.betterspringdrop.core.BSDParticles;

/**
 * Implementation class for an {@link TallFlowerBlock Asphodel Block}
 */
public class AsphodelBlock extends TallFlowerBlock {

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    public AsphodelBlock(final Settings settings) {
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
        return Block.createColumnShape(4.0D, 0.0D, 16.0D).offset(state.getModelOffset(pos));
    }

    /**
     * Randomly display some particles
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link World World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param random The {@link Random Random reference}
     */
    public void randomDisplayTick(final BlockState state, final World world, final BlockPos pos, final Random random) {
        if (random.nextDouble() <= 0.025D) {
            final double particleX = (double)pos.getX() + random.nextDouble();
            final double particleY = (double)pos.getY() + 0.1F + random.nextDouble();
            final double particleZ = (double)pos.getZ() + random.nextDouble();
            world.addParticleClient(BSDParticles.ASPHODEL, particleX, particleY, particleZ, 0.0F, 0.0F, 0.0F);
        }
    }

}