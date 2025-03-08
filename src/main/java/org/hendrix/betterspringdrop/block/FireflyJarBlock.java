package org.hendrix.betterspringdrop.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

/**
 * Implementation class for an {@link LanternBlock Firefly Jar Block}
 */
public class FireflyJarBlock extends LanternBlock {

    public static final MapCodec<LanternBlock> CODEC = createCodec(FireflyJarBlock::new);
    public static final IntProperty FIREFLIES = IntProperty.of("fireflies", 1, 5);

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    public FireflyJarBlock(final Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HANGING, false).with(WATERLOGGED, false).with(FIREFLIES, 1));
    }

    /**
     * Register the {@link Block Block} Properties
     *
     * @param builder The {@link StateManager.Builder State Builder}
     */
    @Override
    protected void appendProperties(final StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FIREFLIES);
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

}