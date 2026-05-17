package org.hendrix.betterspringtolife.core;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.block.*;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;

/**
 * {@link BetterSpringToLife} {@link Block Blocks}
 */
public final class BSTLBlocks {

    //#region Blocks

    public static final Block HOLLOW_OAK_LOG = registerHollowBlock(WoodType.OAK, false, Blocks.OAK_LOG);
    public static final Block HOLLOW_STRIPPED_OAK_LOG = registerHollowBlock(WoodType.OAK, true, Blocks.STRIPPED_OAK_LOG);
    public static final Block HOLLOW_SPRUCE_LOG = registerHollowBlock(WoodType.SPRUCE, false, Blocks.SPRUCE_LOG);
    public static final Block HOLLOW_STRIPPED_SPRUCE_LOG = registerHollowBlock(WoodType.SPRUCE, true, Blocks.STRIPPED_SPRUCE_LOG);
    public static final Block HOLLOW_BIRCH_LOG = registerHollowBlock(WoodType.BIRCH, false, Blocks.BIRCH_LOG);
    public static final Block HOLLOW_STRIPPED_BIRCH_LOG = registerHollowBlock(WoodType.BIRCH, true, Blocks.STRIPPED_BIRCH_LOG);
    public static final Block HOLLOW_JUNGLE_LOG = registerHollowBlock(WoodType.JUNGLE, false, Blocks.JUNGLE_LOG);
    public static final Block HOLLOW_STRIPPED_JUNGLE_LOG = registerHollowBlock(WoodType.JUNGLE, true, Blocks.STRIPPED_JUNGLE_LOG);
    public static final Block HOLLOW_ACACIA_LOG = registerHollowBlock(WoodType.ACACIA, false, Blocks.ACACIA_LOG);
    public static final Block HOLLOW_STRIPPED_ACACIA_LOG = registerHollowBlock(WoodType.ACACIA, true, Blocks.STRIPPED_ACACIA_LOG);
    public static final Block HOLLOW_DARK_OAK_LOG = registerHollowBlock(WoodType.DARK_OAK, false, Blocks.DARK_OAK_LOG);
    public static final Block HOLLOW_STRIPPED_DARK_OAK_LOG = registerHollowBlock(WoodType.DARK_OAK, true, Blocks.STRIPPED_DARK_OAK_LOG);
    public static final Block HOLLOW_MANGROVE_LOG = registerHollowBlock(WoodType.MANGROVE, false, Blocks.MANGROVE_LOG);
    public static final Block HOLLOW_STRIPPED_MANGROVE_LOG = registerHollowBlock(WoodType.MANGROVE, true, Blocks.STRIPPED_MANGROVE_LOG);
    public static final Block HOLLOW_BAMBOO_BLOCK = registerHollowBlock(WoodType.BAMBOO, false, Blocks.BAMBOO_BLOCK);
    public static final Block HOLLOW_STRIPPED_BAMBOO_BLOCK = registerHollowBlock(WoodType.BAMBOO, true, Blocks.STRIPPED_BAMBOO_BLOCK);
    public static final Block HOLLOW_CHERRY_LOG = registerHollowBlock(WoodType.CHERRY, false, Blocks.CHERRY_LOG);
    public static final Block HOLLOW_STRIPPED_CHERRY_LOG = registerHollowBlock(WoodType.CHERRY, true, Blocks.STRIPPED_CHERRY_LOG);
    public static final Block HOLLOW_PALE_OAK_LOG = registerHollowBlock(WoodType.PALE_OAK, false, Blocks.PALE_OAK_LOG);
    public static final Block HOLLOW_STRIPPED_PALE_OAK_LOG = registerHollowBlock(WoodType.PALE_OAK, true, Blocks.STRIPPED_PALE_OAK_LOG);
    public static final Block HOLLOW_CRIMSON_STEM = registerHollowBlock(WoodType.CRIMSON, false, Blocks.CRIMSON_STEM);
    public static final Block HOLLOW_STRIPPED_CRIMSON_STEM = registerHollowBlock(WoodType.CRIMSON, true, Blocks.STRIPPED_CRIMSON_STEM);
    public static final Block HOLLOW_WARPED_STEM = registerHollowBlock(WoodType.WARPED, false, Blocks.WARPED_STEM);
    public static final Block HOLLOW_STRIPPED_WARPED_STEM = registerHollowBlock(WoodType.WARPED, true, Blocks.STRIPPED_WARPED_STEM);

    public static final Block OAK_LEAVES_PILE = registerLeafPile(WoodType.OAK, Blocks.OAK_LEAVES, SoundType.GRASS);
    public static final Block SPRUCE_LEAVES_PILE = registerLeafPile(WoodType.SPRUCE, Blocks.SPRUCE_LEAVES, SoundType.GRASS);
    public static final Block BIRCH_LEAVES_PILE = registerLeafPile(WoodType.BIRCH, Blocks.BIRCH_LEAVES, SoundType.GRASS);
    public static final Block JUNGLE_LEAVES_PILE = registerLeafPile(WoodType.JUNGLE, Blocks.JUNGLE_LEAVES, SoundType.GRASS);
    public static final Block ACACIA_LEAVES_PILE = registerLeafPile(WoodType.ACACIA, Blocks.ACACIA_LEAVES, SoundType.GRASS);
    public static final Block CHERRY_LEAVES_PILE = registerLeafPile(WoodType.CHERRY, Blocks.CHERRY_LEAVES, SoundType.CHERRY_LEAVES);
    public static final Block DARK_OAK_LEAVES_PILE = registerLeafPile(WoodType.DARK_OAK, Blocks.DARK_OAK_LEAVES, SoundType.GRASS);
    public static final Block PALE_OAK_LEAVES_PILE = registerLeafPile(WoodType.PALE_OAK, Blocks.PALE_OAK_LEAVES, SoundType.GRASS);
    public static final Block MANGROVE_LEAVES_PILE = registerLeafPile(WoodType.MANGROVE, Blocks.MANGROVE_LEAVES, SoundType.GRASS);
    public static final Block AZALEA_LEAVES_PILE = registerLeafPile("azalea", Blocks.AZALEA_LEAVES, SoundType.AZALEA_LEAVES);
    public static final Block FLOWERING_AZALEA_LEAVES_PILE = registerLeafPile("flowering_azalea", Blocks.FLOWERING_AZALEA_LEAVES, SoundType.AZALEA_LEAVES);

    public static final Block SNOWY_BUSH = register(
            "snowy_bush",
            SnowyBushBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .replaceable()
                    .noCollision()
                    .instabreak()
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS)
    );
    public static final Block SHORT_SNOWY_GRASS = register(
            "short_snowy_grass",
            ShortSnowyGrassBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SNOWY_BUSH)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
    );
    public static final Block TALL_SNOWY_GRASS = register(
            "tall_snowy_grass",
            TallSnowyGrassBlock::new,
            BlockBehaviour.Properties.ofFullCopy(SHORT_SNOWY_GRASS)
    );

    public static final Block POTTED_CACTUS_FLOWER = registerFlowerPot("potted_cactus_flower", Blocks.CACTUS_FLOWER);
    public static final Block POTTED_BUSH = registerFlowerPot("potted_bush", Blocks.BUSH);
    public static final Block POTTED_SNOWY_BUSH = registerFlowerPot("potted_snowy_bush", SNOWY_BUSH);
    public static final Block POTTED_SHORT_DRY_GRASS = registerFlowerPot("potted_short_dry_grass", Blocks.SHORT_DRY_GRASS);
    public static final Block POTTED_TALL_DRY_GRASS = registerFlowerPot("potted_tall_dry_grass", Blocks.TALL_DRY_GRASS);
    public static final Block POTTED_SHORT_SNOWY_GRASS = registerFlowerPot("potted_short_snowy_grass", SHORT_SNOWY_GRASS);
    public static final Block POTTED_TALL_SNOWY_GRASS = registerFlowerPot("potted_tall_snowy_grass", TALL_SNOWY_GRASS);

    //#endregion

    private static Block registerHollowBlock(final WoodType woodType, final boolean stripped, final Block sourceBlock) {
        String woodSuffix = "_log";
        if(woodType.equals(WoodType.CRIMSON) || woodType.equals(WoodType.WARPED)) {
            woodSuffix = "_stem";
        }
        if(woodType.equals(WoodType.BAMBOO)) {
            woodSuffix = "_block";
        }
        final String name = "hollow_" + (stripped ? "stripped_" : "") + woodType.name().toLowerCase(Locale.ROOT) + woodSuffix;
        return register(name, HollowBlock::new, BlockBehaviour.Properties.ofFullCopy(sourceBlock).isViewBlocking(Blocks::never).noOcclusion());
    }

    private static Block registerLeafPile(WoodType woodType, final Block leaves, SoundType sound) {
        return registerLeafPile(
                woodType.name().toLowerCase(Locale.ROOT),
                leaves,
                sound
        );
    }

    private static Block registerLeafPile(String woodName, final Block leaves, SoundType sound) {
        return register(
                woodName + "_leaves_pile",
                LeafPileBlock::new,
                BlockBehaviour.Properties.of()
                        .mapColor(leaves.defaultMapColor())
                        .replaceable()
                        .isValidSpawn(Blocks::never)
                        .strength(0.1F)
                        .noCollision()
                        .sound(sound)
                        .isViewBlocking((state, level, pos) -> state.getValue(LeafPileBlock.LAYERS) >= 8)
                        .pushReaction(PushReaction.DESTROY)
        );
    }

    private static Block registerFlowerPot(final String name, final Block block) {
        return registerBlockWithoutBlockItem(
                name,
                settings -> new FlowerPotBlock(block, settings),
                Blocks.flowerPotProperties()
        );
    }

    /**
     * Register a {@link Block} without registering a {@link BlockItem}
     *
     * @param name The block name
     * @param blockFactory The block factory
     * @param properties The {@link BlockBehaviour.Properties block properties}
     * @return The registered {@link Block}
     */
    private static Block registerBlockWithoutBlockItem(final String name, final Function<BlockBehaviour.Properties, Block> blockFactory, final BlockBehaviour.Properties properties) {
        final ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, IdentifierUtils.modded(name));
        final Block block = blockFactory.apply(properties.setId(blockResourceKey));
        return Registry.register(BuiltInRegistries.BLOCK, blockResourceKey, block);
    }

    /**
     * Register a {@link Block}
     *
     * @param name The block name
     * @param blockFactory The block factory
     * @param properties The {@link BlockBehaviour.Properties block properties}
     * @return The registered {@link Block}
     */
    private static Block register(final String name, final Function<BlockBehaviour.Properties, Block> blockFactory, final BlockBehaviour.Properties properties) {
        final Block block = registerBlockWithoutBlockItem(name, blockFactory, properties);
        final ResourceKey<Item> blockItemResourceKey = ResourceKey.create(Registries.ITEM, IdentifierUtils.modded(name));
        final BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(blockItemResourceKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, blockItemResourceKey, blockItem);
        return block;
    }

    private static void registerStrippableBlocks() {
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
        StrippableBlockRegistry.register(Blocks.STRIPPED_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_OAK_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_SPRUCE_LOG, BSTLBlocks.HOLLOW_STRIPPED_SPRUCE_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_BIRCH_LOG, BSTLBlocks.HOLLOW_STRIPPED_BIRCH_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_JUNGLE_LOG, BSTLBlocks.HOLLOW_STRIPPED_JUNGLE_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_ACACIA_LOG, BSTLBlocks.HOLLOW_STRIPPED_ACACIA_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_DARK_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_MANGROVE_LOG, BSTLBlocks.HOLLOW_STRIPPED_MANGROVE_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_BAMBOO_BLOCK, BSTLBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK);
        StrippableBlockRegistry.register(Blocks.STRIPPED_CHERRY_LOG, BSTLBlocks.HOLLOW_STRIPPED_CHERRY_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_PALE_OAK_LOG, BSTLBlocks.HOLLOW_STRIPPED_PALE_OAK_LOG);
        StrippableBlockRegistry.register(Blocks.STRIPPED_CRIMSON_STEM, BSTLBlocks.HOLLOW_STRIPPED_CRIMSON_STEM);
        StrippableBlockRegistry.register(Blocks.STRIPPED_WARPED_STEM, BSTLBlocks.HOLLOW_STRIPPED_WARPED_STEM);
    }

    private static void registerFlammableBlocks(int igniteOdds, int burnOdds, Block... blocks) {
        var flammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance();
        Arrays.stream(blocks).forEach(block -> flammableBlockRegistry.add(block, igniteOdds, burnOdds));
    }

    private static void registerCompostableBlocks(float chance, Block... blocks) {
        var composterRegistry = CompostableRegistry.INSTANCE;
        Arrays.stream(blocks).forEach(block -> composterRegistry.add(block, chance));
    }

    /**
     * Register all {@link Block Blocks}
     */
    public static void register() {
        registerStrippableBlocks();
        registerFlammableBlocks(
                5,
                5,
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
        registerFlammableBlocks(
                5,
                100,
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
        registerFlammableBlocks(
                60,
                100,
                SNOWY_BUSH,
                SHORT_SNOWY_GRASS,
                TALL_SNOWY_GRASS
        );
        registerCompostableBlocks(
                0.3F,
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
        );
    }

}