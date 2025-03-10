package org.hendrix.betterspringdrop.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.biome.FoliageColors;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.client.particle.AsphodelParticle;
import org.hendrix.betterspringdrop.core.BSDBlocks;
import org.hendrix.betterspringdrop.core.BSDParticles;

import java.util.Arrays;
import java.util.Objects;

/**
 * {@link BetterSpringDrop Hendrix's Better Spring Drop} {@link ClientModInitializer Client initializer}
 */
@Environment(EnvType.CLIENT)
public final class BetterSpringDropClient implements ClientModInitializer {

    /**
     * Initialize the mod's client stuffs
     */
    @Override
    public void onInitializeClient() {
        Arrays.asList(
                BSDBlocks.FIREFLY_JAR,
                BSDBlocks.EMPTY_FIREFLY_BUSH,
                BSDBlocks.SNOWY_BUSH,
                BSDBlocks.SHORT_SNOWY_GRASS,
                BSDBlocks.TALL_SNOWY_GRASS,
                BSDBlocks.OAK_LEAVES_PILE,
                BSDBlocks.SPRUCE_LEAVES_PILE,
                BSDBlocks.BIRCH_LEAVES_PILE,
                BSDBlocks.JUNGLE_LEAVES_PILE,
                BSDBlocks.ACACIA_LEAVES_PILE,
                BSDBlocks.CHERRY_LEAVES_PILE,
                BSDBlocks.DARK_OAK_LEAVES_PILE,
                BSDBlocks.PALE_OAK_LEAVES_PILE,
                BSDBlocks.MANGROVE_LEAVES_PILE,
                BSDBlocks.AZALEA_LEAVES_PILE,
                BSDBlocks.FLOWERING_AZALEA_LEAVES_PILE,
                BSDBlocks.POTTED_CACTUS_FLOWER,
                BSDBlocks.POTTED_BUSH,
                BSDBlocks.POTTED_SNOWY_BUSH,
                BSDBlocks.POTTED_SHORT_DRY_GRASS,
                BSDBlocks.POTTED_TALL_DRY_GRASS,
                BSDBlocks.POTTED_SHORT_SNOWY_GRASS,
                BSDBlocks.POTTED_TALL_SNOWY_GRASS,
                BSDBlocks.ASPHODEL,
                BSDBlocks.PRICKLY_PEAR
        ).forEach(block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped()));

        Arrays.asList(
                BSDBlocks.OAK_LEAVES_PILE,
                BSDBlocks.JUNGLE_LEAVES_PILE,
                BSDBlocks.ACACIA_LEAVES_PILE,
                BSDBlocks.DARK_OAK_LEAVES_PILE
        ).forEach(block -> ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> FoliageColors.DEFAULT, block));

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> FoliageColors.BIRCH, BSDBlocks.BIRCH_LEAVES_PILE);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> FoliageColors.SPRUCE, BSDBlocks.SPRUCE_LEAVES_PILE);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> FoliageColors.MANGROVE, BSDBlocks.MANGROVE_LEAVES_PILE);

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(Objects.requireNonNull(view), pos), BSDBlocks.POTTED_BUSH);

        ParticleFactoryRegistry.getInstance().register(BSDParticles.ASPHODEL, AsphodelParticle.Factory::new);

    }

}