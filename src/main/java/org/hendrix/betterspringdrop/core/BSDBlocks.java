package org.hendrix.betterspringdrop.core;

import com.google.common.base.Suppliers;
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
import org.hendrix.betterspringdrop.block.FireflyJarBlock;
import org.hendrix.betterspringdrop.block.HollowBlock;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;
import org.hendrix.betterspringdrop.utils.WoodUtils;

import java.util.Arrays;
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

    public static final Block FIREFLY_JAR = registerFireflyJar();

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
    }

    /**
     * Register all flammable {@link PillarBlock Logs}
     *
     * @param blocks The {@link Block Flammable Logs to register}
     */
    private static void registerFlammableLogs(final Block... blocks) {
        final FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        if(blocks != null) {
            Arrays.stream(blocks).forEach(block -> registry.add(block, 5, 5));
        }
    }

    /**
     * Register all {@link Block Blocks}
     */
    public static void register() {

    }
}