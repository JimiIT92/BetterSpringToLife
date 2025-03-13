package org.hendrix.betterspringdrop.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.hendrix.betterspringdrop.client.render.entity.model.BSDEntityModelLayers;
import org.hendrix.betterspringdrop.client.render.entity.model.ButterflyEntityModel;
import org.hendrix.betterspringdrop.client.render.entity.state.ButterflyEntityRenderState;
import org.hendrix.betterspringdrop.entity.ButterflyEntity;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

@Environment(EnvType.CLIENT)
public class ButterflyEntityRenderer extends MobEntityRenderer<ButterflyEntity, ButterflyEntityRenderState, ButterflyEntityModel> {

    public ButterflyEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ButterflyEntityModel(context.getPart(BSDEntityModelLayers.BUTTERFLY)), 0.3F);
    }

    public Identifier getTexture(ButterflyEntityRenderState parrotEntityRenderState) {
        return IdentifierUtils.modIdentifier("textures/entity/butterfly/red.png");
    }

    public ButterflyEntityRenderState createRenderState() {
        return new ButterflyEntityRenderState();
    }

    public void updateRenderState(ButterflyEntity parrotEntity, ButterflyEntityRenderState parrotEntityRenderState, float f) {
        super.updateRenderState(parrotEntity, parrotEntityRenderState, f);
        float g = MathHelper.lerp(f, parrotEntity.lastFlapProgress, parrotEntity.flapProgress);
        float h = MathHelper.lerp(f, parrotEntity.lastMaxWingDeviation, parrotEntity.maxWingDeviation);
        parrotEntityRenderState.flapAngle = (MathHelper.sin(g) + 1.0F) * h;
        parrotEntityRenderState.butterflyPose = ButterflyEntityModel.getPose(parrotEntity);
    }

}