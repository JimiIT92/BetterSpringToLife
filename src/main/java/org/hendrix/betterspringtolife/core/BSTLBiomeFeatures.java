package org.hendrix.betterspringtolife.core;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

public class BSTLBiomeFeatures {

    public static void addFallenTrees() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("fallen_hollow_oak_tree"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("fallen_hollow_spruce_tree"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("fallen_hollow_jungle_tree"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("fallen_hollow_birch_tree"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("fallen_hollow_super_birch_tree"))
        );
    }

    public static void addFlowers() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_asphodel"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_asphodel_2"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.SNOWY_TAIGA, Biomes.SNOWY_PLAINS, Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.SNOWY_SLOPES, Biomes.GROVE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_snowy_bush"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.SNOWY_TAIGA, Biomes.SNOWY_PLAINS),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_snowy_grass"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.SNOWY_SLOPES, Biomes.GROVE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_snowy_grass_mountains"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.WOODED_BADLANDS),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, IdentifierUtils.modded("patch_cactus_mesa"))
        );
    }

    public static void addSpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST),
                MobCategory.CREATURE,
                BSTLEntityTypes.MOOBLOOM,
                20,
                1,
                3
        );
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP),
                MobCategory.CREATURE,
                BSTLEntityTypes.MUDDY_PIG,
                15,
                2,
                4
        );
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.FOREST, Biomes.FLOWER_FOREST),
                MobCategory.CREATURE,
                BSTLEntityTypes.BUTTERFLY,
                30,
                3,
                5
        );
    }


    public static void register() {
        addFallenTrees();
        addFlowers();
        addSpawns();
    }

}