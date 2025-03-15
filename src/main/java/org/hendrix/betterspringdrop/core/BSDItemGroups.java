package org.hendrix.betterspringdrop.core;

import com.google.common.base.Suppliers;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link ItemGroup Item Groups}
 */
public final class BSDItemGroups {

    //#region Item Groups

    public static final ItemGroup BETTER_SPRING_DROP = Registry.register(
            Registries.ITEM_GROUP,
            IdentifierUtils.modIdentifier(BetterSpringDrop.MOD_ID),
            FabricItemGroup.builder()
                    .icon(Suppliers.memoize(() -> new ItemStack(BSDBlocks.HOLLOW_BIRCH_LOG)))
                    .displayName(Text.translatable("itemgroup." + BetterSpringDrop.MOD_ID + "." + BetterSpringDrop.MOD_ID))
                    .entries((displayContext, entries) -> {
                        entries.add(BSDBlocks.HOLLOW_OAK_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_OAK_LOG);
                        entries.add(BSDBlocks.HOLLOW_SPRUCE_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_SPRUCE_LOG);
                        entries.add(BSDBlocks.HOLLOW_BIRCH_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_BIRCH_LOG);
                        entries.add(BSDBlocks.HOLLOW_JUNGLE_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_JUNGLE_LOG);
                        entries.add(BSDBlocks.HOLLOW_ACACIA_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_ACACIA_LOG);
                        entries.add(BSDBlocks.HOLLOW_DARK_OAK_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG);
                        entries.add(BSDBlocks.HOLLOW_MANGROVE_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_MANGROVE_LOG);
                        entries.add(BSDBlocks.HOLLOW_BAMBOO_BLOCK);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK);
                        entries.add(BSDBlocks.HOLLOW_CHERRY_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_CHERRY_LOG);
                        entries.add(BSDBlocks.HOLLOW_PALE_OAK_LOG);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_PALE_OAK_LOG);
                        entries.add(BSDBlocks.HOLLOW_CRIMSON_STEM);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_CRIMSON_STEM);
                        entries.add(BSDBlocks.HOLLOW_WARPED_STEM);
                        entries.add(BSDBlocks.HOLLOW_STRIPPED_WARPED_STEM);
                        entries.add(BSDBlocks.OAK_LEAVES_PILE);
                        entries.add(BSDBlocks.SPRUCE_LEAVES_PILE);
                        entries.add(BSDBlocks.BIRCH_LEAVES_PILE);
                        entries.add(BSDBlocks.JUNGLE_LEAVES_PILE);
                        entries.add(BSDBlocks.ACACIA_LEAVES_PILE);
                        entries.add(BSDBlocks.CHERRY_LEAVES_PILE);
                        entries.add(BSDBlocks.DARK_OAK_LEAVES_PILE);
                        entries.add(BSDBlocks.PALE_OAK_LEAVES_PILE);
                        entries.add(BSDBlocks.MANGROVE_LEAVES_PILE);
                        entries.add(BSDBlocks.AZALEA_LEAVES_PILE);
                        entries.add(BSDBlocks.FLOWERING_AZALEA_LEAVES_PILE);
                        entries.add(BSDBlocks.SNOWY_BUSH);
                        entries.add(BSDBlocks.SHORT_SNOWY_GRASS);
                        entries.add(BSDBlocks.TALL_SNOWY_GRASS);
                        entries.add(BSDBlocks.ASPHODEL);
                        entries.add(BSDBlocks.BUTTERCUP);
                        entries.add(BSDItems.FIREFLY_JAR);
                        entries.add(BSDItems.PRICKLY_PEAR);
                        entries.add(BSDItems.MOOBLOOM_SPAWN_EGG);
                        entries.add(BSDItems.MUDDY_PIG_SPAWN_EGG);
                        entries.add(BSDItems.BUTTERFLY_SPAWN_EGG);
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