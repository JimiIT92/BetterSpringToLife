package org.hendrix.betterspringdrop.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.core.BSDPlacedFeatures;

/**
 * {@link BetterSpringDrop Better Spring Drop} class for adding Features to Biomes
 */
public final class BSDBiomeFeatures {

    /**
     * Add modded features to Biomes
     */
    public static void modifyBiomes() {
        addFallenTrees();
    }

    /**
     * Add the modded Fallen Trees to biomes
     */
    private static void addFallenTrees() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSDPlacedFeatures.FALLEN_HOLLOW_OAK_TREE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSDPlacedFeatures.FALLEN_HOLLOW_SPRUCE_TREE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.JUNGLE),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSDPlacedFeatures.FALLEN_HOLLOW_JUNGLE_TREE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSDPlacedFeatures.FALLEN_HOLLOW_BIRCH_TREE
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                BSDPlacedFeatures.FALLEN_HOLLOW_SUPER_BIRCH_TREE
        );
    }

}