package org.hendrix.betterspringtolife.core;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

import java.util.Arrays;

public class BSTLCreativeModeTabs {

    //#region Creative Mode Tabs

    public static final CreativeModeTab BETTER_SPRING_TO_LIFE = register(
            BetterSpringToLife.MOD_ID,
            FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(BSTLBlocks.HOLLOW_BIRCH_LOG))
                    .title(Component.translatable("creativeTab." + BetterSpringToLife.MOD_ID + "." + BetterSpringToLife.MOD_ID))
                    .displayItems((params, output) -> {
                        addContent(
                                output,
                                BSTLBlocks.HOLLOW_OAK_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_OAK_LOG,
                                BSTLBlocks.HOLLOW_SPRUCE_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG,
                                BSTLBlocks.HOLLOW_BIRCH_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_BIRCH_LOG,
                                BSTLBlocks.HOLLOW_JUNGLE_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG,
                                BSTLBlocks.HOLLOW_ACACIA_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_ACACIA_LOG,
                                BSTLBlocks.HOLLOW_DARK_OAK_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG,
                                BSTLBlocks.HOLLOW_MANGROVE_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG,
                                BSTLBlocks.HOLLOW_BAMBOO_BLOCK,
                                BSTLBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK,
                                BSTLBlocks.HOLLOW_CHERRY_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_CHERRY_LOG,
                                BSTLBlocks.HOLLOW_PALE_OAK_LOG,
                                BSTLBlocks.HOLLOW_STRIPPED_PALE_OAK_LOG,
                                BSTLBlocks.HOLLOW_CRIMSON_STEM,
                                BSTLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM,
                                BSTLBlocks.HOLLOW_WARPED_STEM,
                                BSTLBlocks.HOLLOW_STRIPPED_WARPED_STEM,
                                BSTLBlocks.OAK_LEAVES_PILE,
                                BSTLBlocks.SPRUCE_LEAVES_PILE,
                                BSTLBlocks.BIRCH_LEAVES_PILE,
                                BSTLBlocks.JUNGLE_LEAVES_PILE,
                                BSTLBlocks.ACACIA_LEAVES_PILE,
                                BSTLBlocks.CHERRY_LEAVES_PILE,
                                BSTLBlocks.DARK_OAK_LEAVES_PILE,
                                BSTLBlocks.PALE_OAK_LEAVES_PILE,
                                BSTLBlocks.MANGROVE_LEAVES_PILE,
                                BSTLBlocks.AZALEA_LEAVES_PILE,
                                BSTLBlocks.FLOWERING_AZALEA_LEAVES_PILE,
                                BSTLBlocks.SNOWY_BUSH,
                                BSTLBlocks.SHORT_SNOWY_GRASS,
                                BSTLBlocks.TALL_SNOWY_GRASS,
                                BSTLBlocks.ASPHODEL,
                                BSTLBlocks.BUTTERCUP,
                                BSTLItems.FIREFLY_JAR,
                                BSTLItems.PRICKLY_PEAR,
                                BSTLItems.MUSIC_DISC_SWEDEN//,
                                //BSTLItems.MOOBLOOM_SPAWN_EGG,
                                //BSTLItems.MUDDY_PIG_SPAWN_EGG,
                                //BSTLItems.BUTTERFLY_SPAWN_EGG
                        );
                    })
                    .build()
    );

    //#endregion

    /**
     * Add some content to a creative mode tab
     *
     * @param output The {@link CreativeModeTab.Output}
     * @param content The {@link ItemLike content to add}
     */
    private static void addContent(final CreativeModeTab.Output output, final ItemLike... content) {
        Arrays.stream(content).forEach(output::accept);
    }

    /**
     * Register a {@link CreativeModeTab}
     *
     * @param name The creative mode tab name
     * @param creativeModeTab The {@link CreativeModeTab to register}
     * @return The registered {@link CreativeModeTab}
     */
    private static CreativeModeTab register(final String name, final CreativeModeTab creativeModeTab) {
        final ResourceKey<CreativeModeTab> resourceKey = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), IdentifierUtils.modded(name));
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceKey, creativeModeTab);
    }

    /**
     * Register all creative mode tabs
     */
    public static void register() {

    }
}
