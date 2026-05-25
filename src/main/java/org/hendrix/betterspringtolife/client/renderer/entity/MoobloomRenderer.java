package org.hendrix.betterspringtolife.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.cow.BabyCowModel;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import org.hendrix.betterspringtolife.client.BetterSpringToLifeClient;
import org.hendrix.betterspringtolife.client.renderer.entity.layer.MoobloomButtercupsLayer;
import org.hendrix.betterspringtolife.client.renderer.entity.state.MoobloomRenderState;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.entity.Moobloom;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

@Environment(EnvType.CLIENT)
public class MoobloomRenderer extends AgeableMobRenderer<Moobloom, MoobloomRenderState, CowModel> {

    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    private final BlockModelResolver blockModelResolver;

    public MoobloomRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel(context.bakeLayer(BetterSpringToLifeClient.MOOBLOOM)), new BabyCowModel(context.bakeLayer(BetterSpringToLifeClient.MOOBLOOM_BABY)), 0.7F);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new MoobloomButtercupsLayer(this));
    }

    @Override
    public Identifier getTextureLocation(MoobloomRenderState state) {
        return IdentifierUtils.modded("textures/entity/" + (state.isBaby ? BetterSpringToLifeClient.MOOBLOOM_BABY.model().getPath() : BetterSpringToLifeClient.MOOBLOOM.model().getPath()) + ".png");
    }

    @Override
    public MoobloomRenderState createRenderState() {
        return new MoobloomRenderState();
    }

    public void extractRenderState(final Moobloom entity, final MoobloomRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.hasFlowers = entity.hasFlowers();
        this.blockModelResolver.update(state.buttercupModel, BSTLBlocks.BUTTERCUP.defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
    }
}
