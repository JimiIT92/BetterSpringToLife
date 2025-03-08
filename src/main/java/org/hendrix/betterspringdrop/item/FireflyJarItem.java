package org.hendrix.betterspringdrop.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.hendrix.betterspringdrop.block.FireflyJarBlock;
import org.hendrix.betterspringdrop.core.BSDBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Implementation class for the {@link BlockItem Firefly Jar Block Item}
 */
public class FireflyJarItem extends BlockItem {

    /**
     * Constructor. Set the {@link Settings Item Settings}
     *
     * @param settings The {@link Settings Item Settings}
     */
    public FireflyJarItem(final Settings settings) {
        super(BSDBlocks.FIREFLY_JAR, settings);
    }

    /**
     * Get the {@link BlockState Block State} that will be placed
     *
     * @param context The {@link ItemPlacementContext Item Placement Context}
     * @return The {@link BlockState Placement Block State}
     */
    @Override
    protected @Nullable BlockState getPlacementState(final ItemPlacementContext context) {
        return Objects.requireNonNull(super.getPlacementState(context)).with(FireflyJarBlock.FIREFLIES, Math.min(FireflyJarBlock.MAX_FIREFLIES, FireflyJarBlock.getFireflies(context.getStack())));
    }

}