package org.hendrix.betterspringtolife.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.model.animal.cow.BabyCowModel;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.animal.pig.BabyPigModel;
import net.minecraft.client.model.animal.pig.PigModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import org.hendrix.betterspringtolife.client.particle.AsphodelParticle;
import org.hendrix.betterspringtolife.client.renderer.entity.ButterflyRenderer;
import org.hendrix.betterspringtolife.client.renderer.entity.MoobloomRenderer;
import org.hendrix.betterspringtolife.client.renderer.entity.MuddyPigRenderer;
import org.hendrix.betterspringtolife.client.renderer.entity.model.ButterflyModel;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.core.BSTLEntityTypes;
import org.hendrix.betterspringtolife.core.BSTLParticles;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

import java.util.List;

@Environment(EnvType.CLIENT)
public final class BetterSpringToLifeClient implements ClientModInitializer {

    //#region Entity Model Layers

    public static final ModelLayerLocation MOOBLOOM = new ModelLayerLocation(IdentifierUtils.modded("moobloom"), "main");
    public static final ModelLayerLocation MOOBLOOM_BABY = new ModelLayerLocation(IdentifierUtils.modded("moobloom_baby"), "main");
    public static final ModelLayerLocation MUDDY_PIG = new ModelLayerLocation(IdentifierUtils.modded("muddy_pig"), "main");
    public static final ModelLayerLocation MUDDY_PIG_BABY = new ModelLayerLocation(IdentifierUtils.modded("muddy_pig_baby"), "main");
    public static final ModelLayerLocation BUTTERFLY = new ModelLayerLocation(IdentifierUtils.modded("butterfly"), "main");

    //#endregion

    @Override
    public void onInitializeClient() {
        BlockColorRegistry.register(
                List.of(BlockTintSources.foliage()),
                BSTLBlocks.OAK_LEAVES_PILE,
                BSTLBlocks.JUNGLE_LEAVES_PILE,
                BSTLBlocks.ACACIA_LEAVES_PILE,
                BSTLBlocks.DARK_OAK_LEAVES_PILE,
                BSTLBlocks.MANGROVE_LEAVES_PILE
        );
        BlockColorRegistry.register(
                List.of(BlockTintSources.constant(-8345771)),
                BSTLBlocks.BIRCH_LEAVES_PILE
        );
        BlockColorRegistry.register(
                List.of(BlockTintSources.constant(-10380959)),
                BSTLBlocks.SPRUCE_LEAVES_PILE
        );
        BlockColorRegistry.register(
                List.of(BlockTintSources.grass()),
                BSTLBlocks.POTTED_BUSH
        );

        ParticleProviderRegistry.getInstance().register(BSTLParticles.ASPHODEL, AsphodelParticle.Provider::new);
        BSTLClientEvents.register();

        ModelLayerRegistry.registerModelLayer(MOOBLOOM, CowModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(MOOBLOOM_BABY, BabyCowModel::createBodyLayer);
        ModelLayerRegistry.registerModelLayer(MUDDY_PIG, () -> PigModel.createBodyLayer(CubeDeformation.NONE));
        ModelLayerRegistry.registerModelLayer(MUDDY_PIG_BABY, () -> BabyPigModel.createBodyLayer(CubeDeformation.NONE));
        ModelLayerRegistry.registerModelLayer(BUTTERFLY, ButterflyModel::createBodyLayer);
        EntityRenderers.register(BSTLEntityTypes.MOOBLOOM, MoobloomRenderer::new);
        EntityRenderers.register(BSTLEntityTypes.MUDDY_PIG, MuddyPigRenderer::new);
        EntityRenderers.register(BSTLEntityTypes.BUTTERFLY, ButterflyRenderer::new);
    }
}
