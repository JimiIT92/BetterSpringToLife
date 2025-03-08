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
                        entries.add(BSDBlocks.FIREFLY_JAR);
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