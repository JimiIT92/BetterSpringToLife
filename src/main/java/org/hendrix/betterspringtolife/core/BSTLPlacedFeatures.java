package org.hendrix.betterspringtolife.core;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

/**
 * {@link BetterSpringToLife Better Spring to Life} {@link PlacedFeature Placed Features}
 */
public final class BSTLPlacedFeatures {

    //#region Placed Features

    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_OAK_TREE = registerKey("fallen_hollow_oak_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_SPRUCE_TREE = registerKey("fallen_hollow_spruce_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_BIRCH_TREE = registerKey("fallen_hollow_birch_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_SUPER_BIRCH_TREE = registerKey("fallen_hollow_super_birch_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_HOLLOW_JUNGLE_TREE = registerKey("fallen_hollow_jungle_tree");
    public static final RegistryKey<PlacedFeature> PATCH_SNOWY_BUSH = registerKey("patch_snowy_bush");
    public static final RegistryKey<PlacedFeature> PATCH_SNOWY_GRASS = registerKey("patch_snowy_grass");
    public static final RegistryKey<PlacedFeature> PATCH_SNOWY_GRASS_MOUNTAINS = registerKey("patch_snowy_grass_mountains");
    public static final RegistryKey<PlacedFeature> PATCH_ASPHODEL = registerKey("patch_asphodel");
    public static final RegistryKey<PlacedFeature> PATCH_ASPHODEL_2 = registerKey("patch_asphodel_2");
    public static final RegistryKey<PlacedFeature> PATCH_CACTUS_MESA = registerKey("patch_cactus_mesa");

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