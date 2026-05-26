package org.hendrix.betterspringtolife.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

public final class BSTLTags {

    public static class BlockTags {

        //#region Tags

        public static final TagKey<Block> SNOWY_VEGETATION_MAY_PLACE_ON = register("snowy_vegetation_may_place_on");
        public static final TagKey<Block> BUTTERFLIES_SPAWNABLE_ON = register("butterflies_spawnable_on");
        public static final TagKey<Block> MUDDY_PIGS_SPAWNABLE_ON = register("muddy_pigs_spawnable_on");

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
