package org.hendrix.betterspringdrop.core;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link TagKey Tags}
 */
public final class BSDTags {

    /**
     * {@link BetterSpringDrop Better Spring Drop} {@link TagKey<Block> Block Tags}
     */
    public static class BlockTags {

        //#region Tags

        public static final TagKey<Block> SNOWY_VEGETATION_MAY_PLACE_ON = tag("snowy_vegetation_may_place_on");
        public static final TagKey<Block> LEAF_PILE_CANNOT_SURVIVE_ON = tag("leaf_pile_cannot_survive_on");
        public static final TagKey<Block> LEAF_PILE_CAN_SURVIVE_ON = tag("leaf_pile_can_survive_on");
        public static final TagKey<Block> BUTTERFLIES_SPAWNABLE_ON = tag("butterflies_spawnable_on");

        //#endregion

        /**
         * Get a {@link TagKey<Block> Block Tag}
         *
         * @param name The {@link String Tag name}
         * @return The {@link TagKey<Block> Block Tag}
         */
        private static TagKey<Block> tag(final String name) {
            return TagKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier(name));
        }

    }

}