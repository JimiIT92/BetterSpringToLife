package org.hendrix.betterspringdrop.core;

import com.google.common.base.Suppliers;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.block.*;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;
import org.hendrix.betterspringdrop.utils.WoodUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link Block Blocks}
 */
public final class BSDBlocks {

    //#region Blocks

    //#region Hollow Logs

    public static final Block HOLLOW_OAK_LOG = registerHollowLog(WoodType.OAK, false);
    public static final Block HOLLOW_STRIPPED_OAK_LOG = registerHollowLog(WoodType.OAK, true);
    public static final Block HOLLOW_SPRUCE_LOG = registerHollowLog(WoodType.SPRUCE, false);
    public static final Block HOLLOW_STRIPPED_SPRUCE_LOG = registerHollowLog(WoodType.SPRUCE, true);
    public static final Block HOLLOW_BIRCH_LOG = registerHollowLog(WoodType.BIRCH, false);
    public static final Block HOLLOW_STRIPPED_BIRCH_LOG = registerHollowLog(WoodType.BIRCH, true);
    public static final Block HOLLOW_JUNGLE_LOG = registerHollowLog(WoodType.JUNGLE, false);
    public static final Block HOLLOW_STRIPPED_JUNGLE_LOG = registerHollowLog(WoodType.JUNGLE, true);
    public static final Block HOLLOW_ACACIA_LOG = registerHollowLog(WoodType.ACACIA, false);
    public static final Block HOLLOW_STRIPPED_ACACIA_LOG = registerHollowLog(WoodType.ACACIA, true);
    public static final Block HOLLOW_DARK_OAK_LOG = registerHollowLog(WoodType.DARK_OAK, false);
    public static final Block HOLLOW_STRIPPED_DARK_OAK_LOG = registerHollowLog(WoodType.DARK_OAK, true);
    public static final Block HOLLOW_MANGROVE_LOG = registerHollowLog(WoodType.MANGROVE, false);
    public static final Block HOLLOW_STRIPPED_MANGROVE_LOG = registerHollowLog(WoodType.MANGROVE, true);
    public static final Block HOLLOW_BAMBOO_BLOCK = registerHollowLog(WoodType.BAMBOO, false);
    public static final Block HOLLOW_STRIPPED_BAMBOO_BLOCK = registerHollowLog(WoodType.BAMBOO, true);
    public static final Block HOLLOW_CHERRY_LOG = registerHollowLog(WoodType.CHERRY, false);
    public static final Block HOLLOW_STRIPPED_CHERRY_LOG = registerHollowLog(WoodType.CHERRY, true);
    public static final Block HOLLOW_PALE_OAK_LOG = registerHollowLog(WoodType.PALE_OAK, false);
    public static final Block HOLLOW_STRIPPED_PALE_OAK_LOG = registerHollowLog(WoodType.PALE_OAK, true);
    public static final Block HOLLOW_CRIMSON_STEM = registerHollowLog(WoodType.CRIMSON, false);
    public static final Block HOLLOW_STRIPPED_CRIMSON_STEM = registerHollowLog(WoodType.CRIMSON, true);
    public static final Block HOLLOW_WARPED_STEM = registerHollowLog(WoodType.WARPED, false);
    public static final Block HOLLOW_STRIPPED_WARPED_STEM = registerHollowLog(WoodType.WARPED, true);

    //#endregion

    //#region Leaf Piles

    public static final Block OAK_LEAVES_PILE = registerLeafPile(WoodType.OAK, BlockSoundGroup.GRASS);
    public static final Block SPRUCE_LEAVES_PILE = registerLeafPile(WoodType.SPRUCE, BlockSoundGroup.GRASS);
    public static final Block BIRCH_LEAVES_PILE = registerLeafPile(WoodType.BIRCH, BlockSoundGroup.GRASS);
    public static final Block JUNGLE_LEAVES_PILE = registerLeafPile(WoodType.JUNGLE, BlockSoundGroup.GRASS);
    public static final Block ACACIA_LEAVES_PILE = registerLeafPile(WoodType.ACACIA, BlockSoundGroup.GRASS);
    public static final Block CHERRY_LEAVES_PILE = registerLeafPile(WoodType.CHERRY, BlockSoundGroup.CHERRY_LEAVES);
    public static final Block DARK_OAK_LEAVES_PILE = registerLeafPile(WoodType.DARK_OAK, BlockSoundGroup.GRASS);
    public static final Block PALE_OAK_LEAVES_PILE = registerLeafPile(WoodType.PALE_OAK, BlockSoundGroup.GRASS);
    public static final Block MANGROVE_LEAVES_PILE = registerLeafPile(WoodType.MANGROVE, BlockSoundGroup.GRASS);
    public static final Block AZALEA_LEAVES_PILE = registerLeafPile("azalea", Blocks.AZALEA_LEAVES, BlockSoundGroup.AZALEA_LEAVES);
    public static final Block FLOWERING_AZALEA_LEAVES_PILE = registerLeafPile("flowering_azalea", Blocks.FLOWERING_AZALEA_LEAVES, BlockSoundGroup.AZALEA_LEAVES);

    //#endregion

    //#region Grass and Flowers

    public static final Block SNOWY_BUSH = registerBlock("snowy_bush", Suppliers.memoize(() -> new SnowyBushBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.WHITE)
            .replaceable()
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .burnable()
            .pistonBehavior(PistonBehavior.DESTROY)
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier("snowy_bush")))
    )));

    public static final Block SHORT_SNOWY_GRASS = registerBlock("short_snowy_grass", Suppliers.memoize(() -> new ShortSnowyGrassBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.WHITE)
            .replaceable()
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .burnable()
            .offset(AbstractBlock.OffsetType.XYZ)
            .pistonBehavior(PistonBehavior.DESTROY)
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier("short_snowy_grass")))
    )));

    public static final Block TALL_SNOWY_GRASS = registerBlock("tall_snowy_grass", Suppliers.memoize(() -> new TallSnowyGrassBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.WHITE)
            .replaceable()
            .noCollision()
            .breakInstantly()
            .sounds(BlockSoundGroup.GRASS)
            .burnable()
            .offset(AbstractBlock.OffsetType.XYZ)
            .pistonBehavior(PistonBehavior.DESTROY)
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier("tall_snowy_grass")))
    )));

    public static final Block ASPHODEL = registerBlock("asphodel", Suppliers.memoize(() -> new AsphodelBlock(
            AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .offset(AbstractBlock.OffsetType.XZ)
                    .burnable()
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.GRASS)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier("asphodel")))
    )));

    public static final Block PRICKLY_PEAR = registerBlockWithoutBlockItem("prickly_pear", Suppliers.memoize(() -> new CactusFlowerBlock(AbstractBlock.Settings.copy(Blocks.CACTUS_FLOWER)
            .offset(AbstractBlock.OffsetType.XZ)
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier("prickly_pear")))
    )));

    public static final Block BROWN_WALL_MUSHROOM = registerWallMushroom("brown_wall_mushroom", Blocks.BROWN_MUSHROOM);
    public static final Block RED_WALL_MUSHROOM = registerWallMushroom("red_wall_mushroom", Blocks.RED_MUSHROOM);

    //#endregion

    //#region Flower Pots

    public static final Block POTTED_CACTUS_FLOWER = registerFlowerPot("potted_cactus_flower", Suppliers.memoize(() -> Blocks.CACTUS_FLOWER));

    public static final Block POTTED_BUSH = registerFlowerPot("potted_bush", Suppliers.memoize(() -> Blocks.BUSH));
    public static final Block POTTED_SNOWY_BUSH = registerFlowerPot("potted_snowy_bush", Suppliers.memoize(() -> SNOWY_BUSH));

    public static final Block POTTED_SHORT_DRY_GRASS = registerFlowerPot("potted_short_dry_grass", Suppliers.memoize(() -> Blocks.SHORT_DRY_GRASS));
    public static final Block POTTED_TALL_DRY_GRASS = registerFlowerPot("potted_tall_dry_grass", Suppliers.memoize(() -> Blocks.TALL_DRY_GRASS));

    public static final Block POTTED_SHORT_SNOWY_GRASS = registerFlowerPot("potted_short_snowy_grass", Suppliers.memoize(() -> SHORT_SNOWY_GRASS));
    public static final Block POTTED_TALL_SNOWY_GRASS = registerFlowerPot("potted_tall_snowy_grass", Suppliers.memoize(() -> TALL_SNOWY_GRASS));

    //#endregion

    //#region Misc

    public static final Block FIREFLY_JAR = registerFireflyJar();
    public static final Block EMPTY_FIREFLY_BUSH = registerEmptyFireflyBush();

    //#endregion

    //#endregion

    /**
     * Register an {@link HollowBlock Hollow Block}
     *
     * @param woodType The {@link WoodType Wood Type}
     * @param stripped {@link Boolean Whether the Log is stripped}
     * @return The {@link Block registered Block}
     */
    private static Block registerHollowLog(final WoodType woodType, final boolean stripped) {
        final String name = WoodUtils.woodName(woodType, stripped);
        return registerBlock(
                name,
                Suppliers.memoize(() -> new HollowBlock(AbstractBlock.Settings.copy(WoodUtils.blockFromWood(woodType, stripped))
                        .nonOpaque()
                        .blockVision(Blocks::never)
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier(name)))
                )));
    }

    /**
     * Register an {@link FireflyJarBlock Firefly Jar Block}
     *
     * @return The {@link Block registered Block}
     */
    private static Block registerFireflyJar() {
        final String name = "firefly_jar";
        return registerBlockWithoutBlockItem(
                name,
                Suppliers.memoize(() -> new FireflyJarBlock(AbstractBlock.Settings.create()
                        .strength(0.3F)
                        .sounds(BlockSoundGroup.GLASS)
                        .nonOpaque()
                        .blockVision(Blocks::never)
                        .allowsSpawning(Blocks::never)
                        .solidBlock(Blocks::never)
                        .suffocates(Blocks::never)
                        .sounds(BlockSoundGroup.GLASS)
                        .luminance((state) -> state.get(FireflyJarBlock.FIREFLIES) * 3)
                        .pistonBehavior(PistonBehavior.DESTROY)
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier(name)))
                ))
        );
    }

    /**
     * Register an {@link EmptyFireflyBush Empty Firefly Bush Block}
     *
     * @return The {@link Block registered Block}
     */
    private static Block registerEmptyFireflyBush() {
        final String name = "empty_firefly_bush";
        return registerBlockWithoutBlockItem(
                name,
                Suppliers.memoize(() -> new EmptyFireflyBush(AbstractBlock.Settings.copy(Blocks.FIREFLY_BUSH)
                        .luminance(state -> 0)
                        .ticksRandomly()
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier(name)))
                )));
    }

    /**
     * Register a {@link LeafPileBlock Leaf Pile Block}
     *
     * @param woodType The {@link WoodType Wood Type}
     * @param sound The {@link BlockSoundGroup Block Sound}
     * @return The {@link Block registered Block}
     */
    private static Block registerLeafPile(final WoodType woodType, final BlockSoundGroup sound) {
        return registerLeafPile(
                woodType.name().toLowerCase(Locale.ROOT),
                WoodUtils.leaves(woodType),
                sound
        );
    }

    /**
     * Register a {@link LeafPileBlock Leaf Pile Block}
     *
     * @param woodName The {@link String Wood Name}
     * @param leaves The {@link Block Leaves Block}
     * @param sound The {@link BlockSoundGroup Block Sound}
     * @return The {@link Block registered Block}
     */
    private static Block registerLeafPile(final String woodName, final Block leaves, final BlockSoundGroup sound) {
        final String name = woodName + "_leaves_pile";
        return registerBlock(
                name,
                Suppliers.memoize(() -> new LeafPileBlock(AbstractBlock.Settings.create()
                        .mapColor(leaves.getDefaultMapColor())
                        .replaceable()
                        .solidBlock(Blocks::never)
                        .strength(0.1F)
                        .nonOpaque()
                        .ticksRandomly()
                        .sounds(sound)
                        .blockVision((state, world, pos) -> state.get(SnowBlock.LAYERS) >= 8)
                        .pistonBehavior(PistonBehavior.DESTROY)
                        .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier(name)))
                ))
        );
    }

    /**
     * Register a {@link Block Flower Pot Block}
     *
     * @param name The {@link String Block Name}
     * @param blockSupplier The {@link Supplier<Block> Flower Block Supplier}
     * @return The {@link Block registered Block}
     */
    private static Block registerFlowerPot(final String name, final Supplier<Block> blockSupplier) {
        return registerBlockWithoutBlockItem(name, Suppliers.memoize(() -> new FlowerPotBlock(blockSupplier.get(), AbstractBlock.Settings.create()
                .breakInstantly()
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY)
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier(name)))
        )));
    }

    /**
     * Register a {@link WallMushroomBlock Wall Mushroom Block}
     *
     * @param name The {@link String Block Name}
     * @param mushroomBlock The {@link Block Mushroom Block}
     * @return The {@link Block registered Block}
     */
    private static Block registerWallMushroom(final String name, final Block mushroomBlock) {
        return registerBlockWithoutBlockItem(name, Suppliers.memoize(() -> new WallMushroomBlock(mushroomBlock, AbstractBlock.Settings.copy(mushroomBlock)
                .noCollision()
                .breakInstantly()
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, IdentifierUtils.modIdentifier(name))))));
    }

    /**
     * Register a {@link Block Block}
     *
     * @param name The {@link String Block name}
     * @param blockSupplier The {@link Supplier <Block> Block Supplier}
     * @return The {@link Block registered Block}
     */
    private static Block registerBlock(final String name, final Supplier<Block> blockSupplier) {
        final Block block = registerBlockWithoutBlockItem(name, blockSupplier);
        BSDItems.registerBlockItem(IdentifierUtils.modIdentifier(name), blockSupplier);
        return block;
    }

    /**
     * Register a {@link Block Block}
     *
     * @param name The {@link String Block name}
     * @param blockSupplier The {@link Supplier<Block> Block Supplier}
     * @return The {@link Block registered Block}
     */
    private static Block registerBlockWithoutBlockItem(final String name, final Supplier<Block> blockSupplier) {
        final Identifier identifier = IdentifierUtils.modIdentifier(name);
        return Registry.register(Registries.BLOCK, identifier, blockSupplier.get());
    }

    /**
     * Register all strippable blocks
     */
    public static void registerStrippables() {
        StrippableBlockRegistry.register(HOLLOW_OAK_LOG, HOLLOW_STRIPPED_OAK_LOG);
        StrippableBlockRegistry.register(HOLLOW_SPRUCE_LOG, HOLLOW_STRIPPED_SPRUCE_LOG);
        StrippableBlockRegistry.register(HOLLOW_BIRCH_LOG, HOLLOW_STRIPPED_BIRCH_LOG);
        StrippableBlockRegistry.register(HOLLOW_JUNGLE_LOG, HOLLOW_STRIPPED_JUNGLE_LOG);
        StrippableBlockRegistry.register(HOLLOW_ACACIA_LOG, HOLLOW_STRIPPED_ACACIA_LOG);
        StrippableBlockRegistry.register(HOLLOW_DARK_OAK_LOG, HOLLOW_STRIPPED_DARK_OAK_LOG);
        StrippableBlockRegistry.register(HOLLOW_MANGROVE_LOG, HOLLOW_STRIPPED_MANGROVE_LOG);
        StrippableBlockRegistry.register(HOLLOW_BAMBOO_BLOCK, HOLLOW_STRIPPED_BAMBOO_BLOCK);
        StrippableBlockRegistry.register(HOLLOW_CHERRY_LOG, HOLLOW_STRIPPED_CHERRY_LOG);
        StrippableBlockRegistry.register(HOLLOW_PALE_OAK_LOG, HOLLOW_STRIPPED_PALE_OAK_LOG);
        StrippableBlockRegistry.register(HOLLOW_CRIMSON_STEM, HOLLOW_STRIPPED_CRIMSON_STEM);
        StrippableBlockRegistry.register(HOLLOW_WARPED_STEM, HOLLOW_STRIPPED_WARPED_STEM);
    }

    /**
     * Register all flammable Blocks
     */
    public static void registerFlammableBlocks() {
        registerFlammableLogs(
                HOLLOW_OAK_LOG,
                HOLLOW_STRIPPED_OAK_LOG,
                HOLLOW_SPRUCE_LOG,
                HOLLOW_STRIPPED_SPRUCE_LOG,
                HOLLOW_BIRCH_LOG,
                HOLLOW_STRIPPED_BIRCH_LOG,
                HOLLOW_JUNGLE_LOG,
                HOLLOW_STRIPPED_JUNGLE_LOG,
                HOLLOW_ACACIA_LOG,
                HOLLOW_STRIPPED_ACACIA_LOG,
                HOLLOW_DARK_OAK_LOG,
                HOLLOW_STRIPPED_DARK_OAK_LOG,
                HOLLOW_MANGROVE_LOG,
                HOLLOW_STRIPPED_MANGROVE_LOG,
                HOLLOW_BAMBOO_BLOCK,
                HOLLOW_STRIPPED_BAMBOO_BLOCK,
                HOLLOW_CHERRY_LOG,
                HOLLOW_STRIPPED_CHERRY_LOG,
                HOLLOW_PALE_OAK_LOG,
                HOLLOW_STRIPPED_PALE_OAK_LOG,
                HOLLOW_CRIMSON_STEM,
                HOLLOW_STRIPPED_CRIMSON_STEM,
                HOLLOW_WARPED_STEM,
                HOLLOW_STRIPPED_WARPED_STEM
        );
        registerFlammableLeavesPile(
                OAK_LEAVES_PILE,
                SPRUCE_LEAVES_PILE,
                BIRCH_LEAVES_PILE,
                JUNGLE_LEAVES_PILE,
                ACACIA_LEAVES_PILE,
                CHERRY_LEAVES_PILE,
                DARK_OAK_LEAVES_PILE,
                PALE_OAK_LEAVES_PILE,
                MANGROVE_LEAVES_PILE,
                AZALEA_LEAVES_PILE,
                FLOWERING_AZALEA_LEAVES_PILE
        );
        registerFlammableBlocks(60, 100,
                SNOWY_BUSH,
                SHORT_SNOWY_GRASS,
                TALL_SNOWY_GRASS,
                ASPHODEL
        );
    }

    /**
     * Register all flammable {@link PillarBlock Logs}
     *
     * @param blocks The {@link Block Flammable Logs to register}
     */
    private static void registerFlammableLogs(final Block... blocks) {
        registerFlammableBlocks(5, 5, blocks);
    }

    /**
     * Register all flammable {@link PillarBlock Logs}
     *
     * @param blocks The {@link Block Flammable Logs to register}
     */
    private static void registerFlammableLeavesPile(final Block... blocks) {
        registerFlammableBlocks(5, 100, blocks);
    }

    /**
     * Register all flammable {@link Block Blocks}
     *
     * @param burnChance The {@link Integer burn chance}
     * @param spreadChance The {@link Integer fire spread chance}
     * @param blocks The {@link Block Flammable Logs to register}
     */
    private static void registerFlammableBlocks(final int burnChance, final int spreadChance, final Block... blocks) {
        final FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        if(blocks != null) {
            Arrays.stream(blocks).forEach(block -> registry.add(block, burnChance, spreadChance));
        }
    }

    /**
     * Register {@link ComposterBlock compostable} {@link Block Blocks}
     */
    public static void registerCompostableBlocks() {
        Arrays.asList(
                OAK_LEAVES_PILE,
                SPRUCE_LEAVES_PILE,
                BIRCH_LEAVES_PILE,
                JUNGLE_LEAVES_PILE,
                ACACIA_LEAVES_PILE,
                CHERRY_LEAVES_PILE,
                DARK_OAK_LEAVES_PILE,
                PALE_OAK_LEAVES_PILE,
                MANGROVE_LEAVES_PILE,
                AZALEA_LEAVES_PILE,
                FLOWERING_AZALEA_LEAVES_PILE,
                SNOWY_BUSH,
                SHORT_SNOWY_GRASS,
                TALL_SNOWY_GRASS

        ).forEach(block -> CompostingChanceRegistry.INSTANCE.add(block, 0.3F));
        CompostingChanceRegistry.INSTANCE.add(ASPHODEL, 0.5F);
    }

    /**
     * Register all {@link Block Blocks}
     */
    public static void register() {
    }

}