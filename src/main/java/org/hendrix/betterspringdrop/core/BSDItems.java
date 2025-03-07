package org.hendrix.betterspringdrop.core;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

import java.util.function.Supplier;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link Item Items}
 */
public final class BSDItems {

    //#region Items

    //#endregion

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param identifier The {@link Identifier Block Identifier}
     * @param blockSupplier The {@link Supplier < Block > Block Supplier}
     */
    public static void registerBlockItem(final Identifier identifier, final Supplier<Block> blockSupplier) {
        Item.Settings itemSettings = new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier)).useBlockPrefixedTranslationKey();
        Registry.register(Registries.ITEM, identifier, new BlockItem(blockSupplier.get(), itemSettings));
    }

    /**
     * Register an {@link Item Item}
     *
     * @param name The {@link String Item Name}
     * @param itemSupplier The {@link Supplier<Item> Item Supplier}
     */
    private static Item registerItem(final String name, final Supplier<Item> itemSupplier) {
        return Registry.register(Registries.ITEM, IdentifierUtils.modIdentifier(name), itemSupplier.get());
    }

    /**
     * Register all {@link Item Items}
     */
    public static void register() {

    }

}