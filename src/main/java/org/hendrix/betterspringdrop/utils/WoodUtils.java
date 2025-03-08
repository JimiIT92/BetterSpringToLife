package org.hendrix.betterspringdrop.utils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WoodType;

import java.util.Locale;

/**
 * Utility methods for {@link WoodType Wood}
 */
public final class WoodUtils {

    /**
     * Get the {@link String Wood Name} related to the {@link WoodType Wood Type}
     *
     * @param woodType The {@link WoodType Wood Type}
     * @param stripped {@link Boolean Whether the Log is stripped}
     * @return The {@link String Wood Name}
     */
    public static String woodName(final WoodType woodType, final boolean stripped) {
        String suffix = "_log";
        if(woodType.equals(WoodType.CRIMSON) || woodType.equals(WoodType.WARPED)) {
            suffix = "_stem";
        }
        if(woodType.equals(WoodType.BAMBOO)) {
            suffix = "_block";
        }
        return "hollow_" + (stripped ? "stripped_" : "") + woodType.name().toLowerCase(Locale.ROOT) + suffix;
    }

    /**
     * Get the {@link Block Log Block} related to the {@link WoodType Wood Type}
     *
     * @param woodType The {@link WoodType Wood Type}
     * @param stripped {@link Boolean Whether the Log is stripped}
     * @return The {@link Block Log Block}
     */
    public static Block blockFromWood(final WoodType woodType, final boolean stripped) {
        if(woodType.equals(WoodType.OAK)) {
            return stripped ? Blocks.STRIPPED_OAK_LOG : Blocks.OAK_LOG;
        }
        if(woodType.equals(WoodType.SPRUCE)) {
            return stripped ? Blocks.STRIPPED_SPRUCE_LOG : Blocks.SPRUCE_LOG;
        }
        if(woodType.equals(WoodType.BIRCH)) {
            return stripped ? Blocks.STRIPPED_BIRCH_LOG : Blocks.BIRCH_LOG;
        }
        if(woodType.equals(WoodType.ACACIA)) {
            return stripped ? Blocks.STRIPPED_ACACIA_LOG : Blocks.ACACIA_LOG;
        }
        if(woodType.equals(WoodType.CHERRY)) {
            return stripped ? Blocks.STRIPPED_CHERRY_LOG : Blocks.CHERRY_LOG;
        }
        if(woodType.equals(WoodType.JUNGLE)) {
            return stripped ? Blocks.STRIPPED_JUNGLE_LOG : Blocks.JUNGLE_LOG;
        }
        if(woodType.equals(WoodType.DARK_OAK)) {
            return stripped ? Blocks.STRIPPED_DARK_OAK_LOG : Blocks.DARK_OAK_LOG;
        }
        if(woodType.equals(WoodType.PALE_OAK)) {
            return stripped ? Blocks.STRIPPED_PALE_OAK_LOG : Blocks.PALE_OAK_LOG;
        }
        if(woodType.equals(WoodType.CRIMSON)) {
            return stripped ? Blocks.STRIPPED_CRIMSON_STEM : Blocks.CRIMSON_STEM;
        }
        if(woodType.equals(WoodType.WARPED)) {
            return stripped ? Blocks.STRIPPED_WARPED_STEM : Blocks.WARPED_STEM;
        }
        if(woodType.equals(WoodType.MANGROVE)) {
            return stripped ? Blocks.STRIPPED_MANGROVE_LOG : Blocks.MANGROVE_LOG;
        }
        if(woodType.equals(WoodType.BAMBOO)) {
            return stripped ? Blocks.STRIPPED_BAMBOO_BLOCK : Blocks.BAMBOO_BLOCK;
        }
        return stripped ? Blocks.STRIPPED_OAK_LOG : Blocks.OAK_LOG;
    }

    /**
     * Get the {@link Block Leaves Block} corresponding to the {@link WoodType Wood Type}
     *
     * @param woodType The {@link WoodType Wood Type}
     * @return The {@link Block Leaves Block}
     */
    public static Block leaves(final WoodType woodType) {
        if(woodType.equals(WoodType.OAK)) {
            return Blocks.OAK_LEAVES;
        }
        if(woodType.equals(WoodType.SPRUCE)) {
            return Blocks.SPRUCE_LEAVES;
        }
        if(woodType.equals(WoodType.BIRCH)) {
            return Blocks.BIRCH_LEAVES;
        }
        if(woodType.equals(WoodType.ACACIA)) {
            return Blocks.ACACIA_LEAVES;
        }
        if(woodType.equals(WoodType.CHERRY)) {
            return Blocks.CHERRY_LEAVES;
        }
        if(woodType.equals(WoodType.JUNGLE)) {
            return Blocks.JUNGLE_LEAVES;
        }
        if(woodType.equals(WoodType.DARK_OAK)) {
            return Blocks.DARK_OAK_LEAVES;
        }
        if(woodType.equals(WoodType.PALE_OAK)) {
            return Blocks.PALE_OAK_LEAVES;
        }
        if(woodType.equals(WoodType.MANGROVE)) {
            return Blocks.MANGROVE_LEAVES;
        }
        return Blocks.OAK_LEAVES;
    }

}