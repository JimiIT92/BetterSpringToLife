package org.hendrix.betterspringtolife.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.world.biome.FoliageColors;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.client.particle.AsphodelParticle;
import org.hendrix.betterspringtolife.client.render.entity.ButterflyEntityRenderer;
import org.hendrix.betterspringtolife.client.render.entity.MoobloomEntityRenderer;
import org.hendrix.betterspringtolife.client.render.entity.MuddyPigEntityRenderer;
import org.hendrix.betterspringtolife.client.render.entity.model.ButterflyEntityModel;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.core.BSTLEntities;
import org.hendrix.betterspringtolife.core.BSTLParticles;

import java.util.Arrays;
import java.util.Objects;

/**
 * {@link BetterSpringToLife Hendrix's Better Spring to Life} {@link ClientModInitializer Client initializer}
 */
@Environment(EnvType.CLIENT)
public final class BetterSpringToLifeClient implements ClientModInitializer {

    /**
     * Initialize the mod's client stuffs
     */
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlocks(
                BlockRenderLayer.CUTOUT_MIPPED,
                BSTLBlocks.FIREFLY_JAR,
                BSTLBlocks.EMPTY_FIREFLY_BUSH,
                BSTLBlocks.SNOWY_BUSH,
                BSTLBlocks.SHORT_SNOWY_GRASS,
                BSTLBlocks.TALL_SNOWY_GRASS,
                BSTLBlocks.OAK_LEAVES_PILE,
                BSTLBlocks.SPRUCE_LEAVES_PILE,
                BSTLBlocks.BIRCH_LEAVES_PILE,
                BSTLBlocks.JUNGLE_LEAVES_PILE,
                BSTLBlocks.ACACIA_LEAVES_PILE,
                BSTLBlocks.CHERRY_LEAVES_PILE,
                BSTLBlocks.DARK_OAK_LEAVES_PILE,
                BSTLBlocks.PALE_OAK_LEAVES_PILE,
                BSTLBlocks.MANGROVE_LEAVES_PILE,
                BSTLBlocks.AZALEA_LEAVES_PILE,
                BSTLBlocks.FLOWERING_AZALEA_LEAVES_PILE,
                BSTLBlocks.POTTED_CACTUS_FLOWER,
                BSTLBlocks.POTTED_BUSH,
                BSTLBlocks.POTTED_SNOWY_BUSH,
                BSTLBlocks.POTTED_SHORT_DRY_GRASS,
                BSTLBlocks.POTTED_TALL_DRY_GRASS,
                BSTLBlocks.POTTED_SHORT_SNOWY_GRASS,
                BSTLBlocks.POTTED_TALL_SNOWY_GRASS,
                BSTLBlocks.POTTED_BUTTERCUP,
                BSTLBlocks.POTTED_PRICKLY_PEAR,
                BSTLBlocks.ASPHODEL,
                BSTLBlocks.PRICKLY_PEAR,
                BSTLBlocks.BUTTERCUP
        );

        Arrays.asList(
                BSTLBlocks.OAK_LEAVES_PILE,
                BSTLBlocks.JUNGLE_LEAVES_PILE,
                BSTLBlocks.ACACIA_LEAVES_PILE,
                BSTLBlocks.DARK_OAK_LEAVES_PILE
        ).forEach(block -> ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> FoliageColors.DEFAULT, block));

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> FoliageColors.BIRCH, BSTLBlocks.BIRCH_LEAVES_PILE);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> FoliageColors.SPRUCE, BSTLBlocks.SPRUCE_LEAVES_PILE);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> FoliageColors.MANGROVE, BSTLBlocks.MANGROVE_LEAVES_PILE);

        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(Objects.requireNonNull(view), pos), BSTLBlocks.POTTED_BUSH);

        ParticleFactoryRegistry.getInstance().register(BSTLParticles.ASPHODEL, AsphodelParticle.Factory::new);

        EntityRendererRegistry.register(BSTLEntities.MOOBLOOM, MoobloomEntityRenderer::new);
        EntityRendererRegistry.register(BSTLEntities.MUDDY_PIG, MuddyPigEntityRenderer::new);
        EntityRendererRegistry.register(BSTLEntities.BUTTERFLY, ButterflyEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(BSTLEntityModelLayers.BUTTERFLY, ButterflyEntityModel::getTexturedModelData);

    }

}