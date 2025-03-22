package org.hendrix.betterspringtolife.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.hendrix.betterspringtolife.block.FireflyJarBlock;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation class for the {@link BlockItem Firefly Jar Block Item}
 */
public final class FireflyJarItem extends BlockItem {

    /**
     * Constructor. Set the {@link Settings Item Settings}
     *
     * @param settings The {@link Settings Item Settings}
     */
    public FireflyJarItem(final Settings settings) {
        super(BSTLBlocks.FIREFLY_JAR, settings);
    }

    /**
     * Get the {@link BlockState Block State} that will be placed
     *
     * @param context The {@link ItemPlacementContext Item Placement Context}
     * @return The {@link BlockState Placement Block State}
     */
    @Override
    protected @Nullable BlockState getPlacementState(final ItemPlacementContext context) {
        final BlockState blockState = super.getPlacementState(context);
        return blockState == null ? null : blockState.with(FireflyJarBlock.FIREFLIES, Math.min(FireflyJarBlock.MAX_FIREFLIES, FireflyJarBlock.getFireflies(context.getStack())));
    }

}