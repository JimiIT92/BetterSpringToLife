package org.hendrix.betterspringtolife.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

public final class BSTLTags {

    public static class BlockTags {

        //#region Tags

        public static final TagKey<Block> LEAF_PILE_CANNOT_SURVIVE_ON = register("leaf_pile_cannot_survive_on");
        public static final TagKey<Block> LEAF_PILE_CAN_SURVIVE_ON = register("leaf_pile_can_survive_on");

        //#endregion

        /**
         * Register a block {@link TagKey}
         *
         * @param name The tag name
         * @return The block {@link TagKey}
         */
        private static TagKey<Block> register(final String name) {
            return TagKey.create(Registries.BLOCK, IdentifierUtils.modded(name));
        }

    }

}
