package org.hendrix.betterspringtolife.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.hendrix.betterspringtolife.block.FireflyJarBlock;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.jspecify.annotations.Nullable;

public class FireflyJarItem extends BlockItem {

    public FireflyJarItem(Properties properties) {
        super(BSTLBlocks.FIREFLY_JAR, properties);
    }

    @Override
    protected @Nullable BlockState getPlacementState(BlockPlaceContext context) {
        final BlockState blockState = super.getPlacementState(context);
        return blockState == null ? null : blockState.setValue(FireflyJarBlock.FIREFLIES, Math.min(FireflyJarBlock.MAX_FIREFLIES, FireflyJarBlock.getFireflies(context.getItemInHand())));
    }
}
