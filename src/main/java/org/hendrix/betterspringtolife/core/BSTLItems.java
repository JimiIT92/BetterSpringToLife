package org.hendrix.betterspringtolife.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

import java.util.function.Function;

/**
 * {@link BetterSpringToLife} {@link Item Items}
 */
public final class BSTLItems {

    //#region Items

    public static final Item PRICKLY_PEAR = register(
            "prickly_pear",
            settings -> new BlockItem(BSTLBlocks.PRICKLY_PEAR, settings),
            new Item.Properties().useBlockDescriptionPrefix().food(BSTLFoods.PRICKLY_PEAR)
    );

    //#endregion

    /**
     * Register an {@link Item}
     *
     * @param name The item name
     * @param itemFactory The item factory
     * @param properties The {@link Item.Properties item properties}
     * @return The registered {@link Item}
     * @param <T> The item type
     */
    public static <T extends Item> T register(final String name, final Function<Item.Properties, T> itemFactory, final Item.Properties properties) {
        final ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, IdentifierUtils.modded(name));
        final T item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    /**
     * Register all {@link Item items}
     */
    public static void register() {

    }

}