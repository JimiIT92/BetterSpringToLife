package org.hendrix.betterspringtolife.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.hendrix.betterspringtolife.client.BSTLEntityModelLayers;
import org.hendrix.betterspringtolife.client.render.entity.model.ButterflyEntityModel;
import org.hendrix.betterspringtolife.client.render.entity.state.ButterflyEntityRenderState;
import org.hendrix.betterspringtolife.entity.ButterflyEntity;
import org.hendrix.betterspringtolife.entity.MoobloomEntity;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

/**
 * Renderer class for a {@link ButterflyEntity Butterfly}
 */
@Environment(EnvType.CLIENT)
public final class ButterflyEntityRenderer extends MobEntityRenderer<ButterflyEntity, ButterflyEntityRenderState, ButterflyEntityModel> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context The {@link EntityRendererFactory.Context Entity Renderer Factory Context}
     */
    public ButterflyEntityRenderer(final EntityRendererFactory.Context context) {
        super(context, new ButterflyEntityModel(context.getPart(BSTLEntityModelLayers.BUTTERFLY)), 0.3F);
    }

    /**
     * Get the {@link Identifier entity texture}
     *
     * @param butterflyEntityRenderState The {@link ButterflyEntityRenderState Butterfly Entity Render State}
     * @return The {@link Identifier entity texture}
     */
    @Override
    public Identifier getTexture(final ButterflyEntityRenderState butterflyEntityRenderState) {
        if(butterflyEntityRenderState.variant == null) {
            butterflyEntityRenderState.variant = ButterflyEntity.Variant.RED;
        }
        return IdentifierUtils.modIdentifier("textures/entity/butterfly/" + butterflyEntityRenderState.variant.asString() + ".png");
    }

    /**
     * Get the {@link ButterflyEntityRenderState Butterfly Entity Render State}
     *
     * @return The {@link ButterflyEntityRenderState Butterfly Entity Render State}
     */
    @Override
    public ButterflyEntityRenderState createRenderState() {
        return new ButterflyEntityRenderState();
    }

    /**
     * Update the {@link ButterflyEntityRenderState Butterfly Entity Render State}
     *
     * @param butterflyEntity The {@link MoobloomEntity entity}
     * @param butterflyEntityRenderState The {@link ButterflyEntityRenderState Butterfly Entity Render State}
     * @param ticks The {@link Float entity ticks}
     */
    public void updateRenderState(final ButterflyEntity butterflyEntity, final ButterflyEntityRenderState butterflyEntityRenderState, final float ticks) {
        super.updateRenderState(butterflyEntity, butterflyEntityRenderState, ticks);
        final float flapAngle = MathHelper.lerp(ticks, butterflyEntity.lastFlapProgress, butterflyEntity.flapProgress);
        final float wingDeviation = MathHelper.lerp(ticks, butterflyEntity.lastMaxWingDeviation, butterflyEntity.maxWingDeviation);
        butterflyEntityRenderState.flapAngle = (MathHelper.sin(flapAngle) + 1.0F) * wingDeviation;
        butterflyEntityRenderState.butterflyPose = ButterflyEntityModel.getPose(butterflyEntity);
        butterflyEntityRenderState.variant = butterflyEntity.getVariant();
    }

}