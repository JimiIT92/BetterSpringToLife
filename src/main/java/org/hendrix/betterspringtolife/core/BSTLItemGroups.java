package org.hendrix.betterspringtolife.core;

import com.google.common.base.Suppliers;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

/**
 * {@link BetterSpringToLife Better Spring to Life} {@link ItemGroup Item Groups}
 */
public final class BSTLItemGroups {

    //#region Item Groups

    public static final ItemGroup BETTER_SPRING_TO_LIFE = Registry.register(
            Registries.ITEM_GROUP,
            IdentifierUtils.modIdentifier(BetterSpringToLife.MOD_ID),
            FabricItemGroup.builder()
                    .icon(Suppliers.memoize(() -> new ItemStack(BSTLBlocks.HOLLOW_BIRCH_LOG)))
                    .displayName(Text.translatable("itemgroup." + BetterSpringToLife.MOD_ID + "." + BetterSpringToLife.MOD_ID))
                    .entries((displayContext, entries) -> {
                        entries.add(BSTLBlocks.HOLLOW_OAK_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_OAK_LOG);
                        entries.add(BSTLBlocks.HOLLOW_SPRUCE_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG);
                        entries.add(BSTLBlocks.HOLLOW_BIRCH_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_BIRCH_LOG);
                        entries.add(BSTLBlocks.HOLLOW_JUNGLE_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG);
                        entries.add(BSTLBlocks.HOLLOW_ACACIA_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_ACACIA_LOG);
                        entries.add(BSTLBlocks.HOLLOW_DARK_OAK_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG);
                        entries.add(BSTLBlocks.HOLLOW_MANGROVE_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG);
                        entries.add(BSTLBlocks.HOLLOW_BAMBOO_BLOCK);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK);
                        entries.add(BSTLBlocks.HOLLOW_CHERRY_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_CHERRY_LOG);
                        entries.add(BSTLBlocks.HOLLOW_PALE_OAK_LOG);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_PALE_OAK_LOG);
                        entries.add(BSTLBlocks.HOLLOW_CRIMSON_STEM);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM);
                        entries.add(BSTLBlocks.HOLLOW_WARPED_STEM);
                        entries.add(BSTLBlocks.HOLLOW_STRIPPED_WARPED_STEM);
                        entries.add(BSTLBlocks.OAK_LEAVES_PILE);
                        entries.add(BSTLBlocks.SPRUCE_LEAVES_PILE);
                        entries.add(BSTLBlocks.BIRCH_LEAVES_PILE);
                        entries.add(BSTLBlocks.JUNGLE_LEAVES_PILE);
                        entries.add(BSTLBlocks.ACACIA_LEAVES_PILE);
                        entries.add(BSTLBlocks.CHERRY_LEAVES_PILE);
                        entries.add(BSTLBlocks.DARK_OAK_LEAVES_PILE);
                        entries.add(BSTLBlocks.PALE_OAK_LEAVES_PILE);
                        entries.add(BSTLBlocks.MANGROVE_LEAVES_PILE);
                        entries.add(BSTLBlocks.AZALEA_LEAVES_PILE);
                        entries.add(BSTLBlocks.FLOWERING_AZALEA_LEAVES_PILE);
                        entries.add(BSTLBlocks.SNOWY_BUSH);
                        entries.add(BSTLBlocks.SHORT_SNOWY_GRASS);
                        entries.add(BSTLBlocks.TALL_SNOWY_GRASS);
                        entries.add(BSTLBlocks.ASPHODEL);
                        entries.add(BSTLBlocks.BUTTERCUP);
                        entries.add(BSTLItems.FIREFLY_JAR);
                        entries.add(BSTLItems.PRICKLY_PEAR);
                        entries.add(BSTLItems.MUSIC_DISC_SWEDEN);
                        entries.add(BSTLItems.MOOBLOOM_SPAWN_EGG);
                        entries.add(BSTLItems.MUDDY_PIG_SPAWN_EGG);
                        entries.add(BSTLItems.BUTTERFLY_SPAWN_EGG);
                    })
                    .build()
    );

    //#endregion

    /**
     * Register all {@link ItemGroup Item Groups}
     */
    public static void register() {

    }

}