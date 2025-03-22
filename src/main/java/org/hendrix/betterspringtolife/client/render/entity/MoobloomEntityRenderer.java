package org.hendrix.betterspringtolife.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import org.hendrix.betterspringtolife.client.render.entity.feature.MoobloomFlowersFeatureRenderer;
import org.hendrix.betterspringtolife.client.render.entity.state.MoobloomEntityRenderState;
import org.hendrix.betterspringtolife.entity.MoobloomEntity;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

/**
 * Renderer class for a {@link MoobloomEntity Moobloom}
 */
@Environment(EnvType.CLIENT)
public final class MoobloomEntityRenderer extends AgeableMobEntityRenderer<MoobloomEntity, MoobloomEntityRenderState, CowEntityModel> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context The {@link EntityRendererFactory.Context Entity Renderer Factory Context}
     */
    public MoobloomEntityRenderer(final EntityRendererFactory.Context context) {
        super(context, new CowEntityModel(context.getPart(EntityModelLayers.MOOSHROOM)), new CowEntityModel(context.getPart(EntityModelLayers.MOOSHROOM_BABY)), 0.7F);
        this.addFeature(new MoobloomFlowersFeatureRenderer(this, context.getBlockRenderManager()));
    }

    /**
     * Get the {@link Identifier entity texture}
     *
     * @param state The {@link MoobloomEntityRenderState Moobloom Entity Render State}
     * @return The {@link Identifier entity texture}
     */
    @Override
    public Identifier getTexture(final MoobloomEntityRenderState state) {
        return IdentifierUtils.modIdentifier("textures/entity/moobloom.png");
    }

    /**
     * Get the {@link MoobloomEntityRenderState Moobloom Entity Render State}
     *
     * @return The {@link MoobloomEntityRenderState Moobloom Entity Render State}
     */
    @Override
    public MoobloomEntityRenderState createRenderState() {
        return new MoobloomEntityRenderState();
    }

    /**
     * Update the {@link MoobloomEntityRenderState Moobloom Entity Render State}
     *
     * @param mooshroomEntity The {@link MoobloomEntity entity}
     * @param mooshroomEntityRenderState The {@link MoobloomEntityRenderState Moobloom Entity Render State}
     * @param ticks The {@link Float entity ticks}
     */
    public void updateRenderState(final MoobloomEntity mooshroomEntity, final MoobloomEntityRenderState mooshroomEntityRenderState, final float ticks) {
        super.updateRenderState(mooshroomEntity, mooshroomEntityRenderState, ticks);
        mooshroomEntityRenderState.hasFlowers = mooshroomEntity.hasFlowers();
    }

}