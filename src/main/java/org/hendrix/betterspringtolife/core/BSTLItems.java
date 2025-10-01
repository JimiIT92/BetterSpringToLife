package org.hendrix.betterspringtolife.core;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.component.type.FirefliesComponent;
import org.hendrix.betterspringtolife.item.FireflyJarItem;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

import java.util.function.Supplier;

/**
 * {@link BetterSpringToLife Better Spring to Life} {@link Item Items}
 */
public final class BSTLItems {

    //#region Items

    public static final Item FIREFLY_JAR = registerFireflyJar();
    public static final Item PRICKLY_PEAR = registerPricklyPear();

    public static final Item MUSIC_DISC_SWEDEN = registerMusicDisc("music_disc_sweden", BSTLJukeboxSongs.SWEDEN);

    public static final Item MOOBLOOM_SPAWN_EGG = registerSpawnEgg("moobloom_spawn_egg", Suppliers.memoize(() -> BSTLEntities.MOOBLOOM));
    public static final Item MUDDY_PIG_SPAWN_EGG = registerSpawnEgg("muddy_pig_spawn_egg", Suppliers.memoize(() -> BSTLEntities.MUDDY_PIG));
    public static final Item BUTTERFLY_SPAWN_EGG = registerSpawnEgg("butterfly_spawn_egg", Suppliers.memoize(() -> BSTLEntities.BUTTERFLY));

    //#endregion

    /**
     * Register the {@link BSTLBlocks#FIREFLY_JAR Firefly Jar Item}
     *
     * @return The {@link Item registered Item}
     */
    private static Item registerFireflyJar() {
        final String name = "firefly_jar";
        return registerItem(name, Suppliers.memoize(() -> new FireflyJarItem(blockItemSettings(IdentifierUtils.modIdentifier(name))
                .maxCount(1)
                .component(BSTLDataComponentTypes.FIREFLIES, FirefliesComponent.DEFAULT)
        )));
    }

    /**
     * Register the {@link BSTLBlocks#PRICKLY_PEAR Prickly Pear Item}
     *
     * @return The {@link Item registered Item}
     */
    private static Item registerPricklyPear() {
        final Identifier identifier = IdentifierUtils.modIdentifier("prickly_pear");
        return registerBlockItem(identifier, Suppliers.memoize(() -> BSTLBlocks.PRICKLY_PEAR), blockItemSettings(identifier).food(BSTLFoods.PRICKLY_PEAR));
    }

    /**
     * Register a {@link Item Music Disc Item}
     *
     * @param name The {@link String Item name}
     * @param song The {@link RegistryKey<JukeboxSong> Jukebox Song}
     * @return The {@link Item registered Item}
     */
    private static Item registerMusicDisc(final String name, final RegistryKey<JukeboxSong> song) {
        final Identifier identifier = IdentifierUtils.modIdentifier(name);
        return registerItem(name, Suppliers.memoize(() -> new Item(new Item.Settings()
                .maxCount(1)
                .rarity(Rarity.UNCOMMON)
                .jukeboxPlayable(song)
                .useItemPrefixedTranslationKey()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier))
        )));
    }

    /**
     * Register a {@link SpawnEggItem Spawn Egg Item}
     *
     * @param name The {@link String Item name}
     * @param entityType The {@link Supplier<EntityType> Spawn Egg Entity Type Supplier}
     * @return The {@link Item registered Item}
     */
    private static Item registerSpawnEgg(final String name, final Supplier<EntityType<? extends MobEntity>> entityType) {
        final Identifier identifier = IdentifierUtils.modIdentifier(name);
        return registerItem(name, Suppliers.memoize(() -> new SpawnEggItem(new Item.Settings()
                .spawnEgg(entityType.get())
                .useItemPrefixedTranslationKey()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier))
        )));
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param identifier    The {@link Identifier Block Identifier}
     * @param blockSupplier The {@link Supplier<Block> Block Supplier}
     */
    public static void registerBlockItem(final Identifier identifier, final Supplier<Block> blockSupplier) {
        registerBlockItem(identifier, blockSupplier, blockItemSettings(identifier));
    }

    /**
     * Register a {@link BlockItem Block Item}
     *
     * @param identifier The {@link Identifier Block Identifier}
     * @param blockSupplier The {@link Supplier<Block> Block Supplier}
     * @param settings The {@link Item.Settings Item Settings}
     */
    public static Item registerBlockItem(final Identifier identifier, final Supplier<Block> blockSupplier, final Item.Settings settings) {
        return registerItem(identifier.getPath(), Suppliers.memoize(() -> new BlockItem(blockSupplier.get(), settings)));
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