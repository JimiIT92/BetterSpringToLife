package org.hendrix.betterspringtolife.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.core.BSTLEntities;
import org.hendrix.betterspringtolife.core.BSTLPlacedFeatures;

/**
 * {@link BetterSpringToLife Better Spring to Life} class for adding Features to Biomes
 */
public final class BSTLBiomeFeatures {

    /**
     * Add modded features to Biomes
     */
    public static void modifyBiomes() {
        addFallenTrees();
        addFlowers();
        addSpawns();
    }

    /**
     * Add the modded Fallen Trees to biomes
     */
    private static void addFallenTrees() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.FALLEN_HOLLOW_OAK_TREE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.FALLEN_HOLLOW_SPRUCE_TREE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.JUNGLE),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.FALLEN_HOLLOW_JUNGLE_TREE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.FALLEN_HOLLOW_BIRCH_TREE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.FALLEN_HOLLOW_SUPER_BIRCH_TREE
        );
    }

    /**
     * Add the modded flowers to biomes
     */
    private static void addFlowers() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.PATCH_ASPHODEL
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.PATCH_ASPHODEL_2
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_PLAINS, BiomeKeys.JAGGED_PEAKS, BiomeKeys.FROZEN_PEAKS, BiomeKeys.SNOWY_SLOPES, BiomeKeys.GROVE),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.PATCH_SNOWY_BUSH
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.PATCH_SNOWY_GRASS
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.JAGGED_PEAKS, BiomeKeys.FROZEN_PEAKS, BiomeKeys.SNOWY_SLOPES, BiomeKeys.GROVE),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.PATCH_SNOWY_GRASS_MOUNTAINS
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.WOODED_BADLANDS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSTLPlacedFeatures.PATCH_CACTUS_MESA
        );
    }

    /**
     * Add the modded mobs to biomes
     */
    private static void addSpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST),
                SpawnGroup.CREATURE,
                BSTLEntities.MOOBLOOM,
                20,
                1,
                3
        );
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.MANGROVE_SWAMP),
                SpawnGroup.CREATURE,
                BSTLEntities.MUDDY_PIG,
                15,
                2,
                4
        );
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST),
                SpawnGroup.CREATURE,
                BSTLEntities.BUTTERFLY,
                30,
                3,
                5
        );
    }

}