package org.hendrix.betterspringtolife.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireflyBushBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.hendrix.betterspringtolife.utils.WorldUtils;

/**
 * Implementation class for an {@link FireflyBushBlock Empty Firefly Bush}
 */
public final class EmptyFireflyBush extends FireflyBushBlock {

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    public EmptyFireflyBush(final Settings settings) {
        super(settings);
    }

    /**
     * Disable the Firefly particles
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link World World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param random The {@link Random Random reference}
     */
    public void randomDisplayTick(final BlockState state, final World world, final BlockPos pos, final Random random) {

    }

    /**
     * Refill the bush with Fireflies
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link World World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param random The {@link Random Random reference}
     */
    @Override
    protected void randomTick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random) {
        if (random.nextInt(30) == 0) {
            if(world.isNightAndNatural() && world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos) <= pos.getY()) {
                WorldUtils.setBlock(Blocks.FIREFLY_BUSH.getDefaultState(), null, null, world, pos, null, null);
            }
        }
        super.randomTick(state, world, pos, random);
    }

    /**
     * Get the {@link ItemStack Item Stack} associated with the middle-mouse click action
     *
     * @param world The {@link WorldView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param state The {@link BlockState current Block State}
     * @param includeData {@link Boolean Whether custom data should be included in the Item Stack}
     * @return The {@link Items#FIREFLY_BUSH Firefly Bush Item Stack}
     */
    @Override
    protected ItemStack getPickStack(final WorldView world, final BlockPos pos, final BlockState state, final boolean includeData) {
        return Items.FIREFLY_BUSH.getDefaultStack();
    }
}