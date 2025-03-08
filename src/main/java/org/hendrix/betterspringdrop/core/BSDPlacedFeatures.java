package org.hendrix.betterspringdrop.core;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link PlacedFeature Placed Features}
 */
public final class BSDPlacedFeatures {

    //#region Placed Features

    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_OAK_TREE = registerKey("fallen_hollow_oak_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_SPRUCE_TREE = registerKey("fallen_hollow_spruce_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_BIRCH_TREE = registerKey("fallen_hollow_birch_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_SUPER_BIRCH_TREE = registerKey("fallen_hollow_super_birch_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_JUNGLE_TREE = registerKey("fallen_hollow_jungle_tree");
    public static final RegistryKey<PlacedFeature> PATCH_SNOWY_GRASS = registerKey("patch_snowy_grass");
    public static final RegistryKey<PlacedFeature> PATCH_SNOWY_GRASS_MOUNTAINS = registerKey("patch_snowy_grass_mountains");

    //#endregion

    /**
     * Get a {@link PlacedFeature Placed Feature} {@link RegistryKey Registry Key}
     *
     * @param name The {@link String Placed Feature Name}
     * @return The {@link RegistryKey<PlacedFeature> Placed Feature Registry Key}
     */
    private static RegistryKey<PlacedFeature> registerKey(final String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, IdentifierUtils.modIdentifier(name));
    }

}