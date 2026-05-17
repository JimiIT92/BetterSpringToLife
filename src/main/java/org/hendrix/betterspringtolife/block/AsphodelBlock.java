package org.hendrix.betterspringtolife.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.hendrix.betterspringtolife.core.BSTLParticles;

public class AsphodelBlock extends TallFlowerBlock {

    public AsphodelBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.column(4, 0, 16).move(state.getOffset(pos));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if(random.nextDouble() <= 0.025D) {
            final double particleX = (double)pos.getX() + random.nextDouble();
            final double particleY = (double)pos.getY() + 0.1F + random.nextDouble();
            final double particleZ = (double)pos.getZ() + random.nextDouble();
            level.addParticle(BSTLParticles.ASPHODEL, particleX, particleY, particleZ, 0.0F, 0.0F, 0.0F);
        }
    }
}
