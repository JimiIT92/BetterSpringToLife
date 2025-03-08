package org.hendrix.betterspringdrop.core;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.component.type.FirefliesComponent;
import org.hendrix.betterspringdrop.item.FireflyJarItem;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

import java.util.function.Supplier;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link Item Items}
 */
public final class BSDItems {

    //#region Items

    public static final Item FIREFLY_JAR = registerFireflyJar();

    //#endregion

    /**
     * Register the {@link BSDBlocks#FIREFLY_JAR Firefly Jar Item}
     *
     * @return The {@link Item registered Item}
     */
    private static Item registerFireflyJar() {
        final String name = "firefly_jar";
        return registerItem(name, Suppliers.memoize(() -> new FireflyJarItem(blockItemSettings(IdentifierUtils.modIdentifier(name)).component(BSDDataComponentTypes.FIREFLIES, FirefliesComponent.DEFAULT))));
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param identifier The {@link Identifier Block Identifier}
     * @param blockSupplier The {@link Supplier < Block > Block Supplier}
     */
    public static void registerBlockItem(final Identifier identifier, final Supplier<Block> blockSupplier) {
        Registry.register(Registries.ITEM, identifier, new BlockItem(blockSupplier.get(), blockItemSettings(identifier)));
    }

    /**
     * Get the {@link Item.Settings Item Settings} of a {@link BlockItem Block Item}
     *
     * @param identifier The {@link Identifier Item identifier}
     * @return The {@link Item.Settings Block Item Settings}
     */
    private static Item.Settings blockItemSettings(final Identifier identifier) {
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier)).useBlockPrefixedTranslationKey();
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