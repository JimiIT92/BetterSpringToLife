package org.hendrix.betterspringtolife.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.hendrix.betterspringtolife.client.renderer.entity.state.MoobloomRenderState;

@Environment(EnvType.CLIENT)
public class MoobloomButtercupsLayer extends RenderLayer<MoobloomRenderState, CowModel> {


    public MoobloomButtercupsLayer(RenderLayerParent<MoobloomRenderState, CowModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, MoobloomRenderState state, float yRot, float xRot) {
        if (!state.isBaby && state.hasFlowers) {
            final boolean appearsGlowingWithInvisibility = state.appearsGlowing() && state.isInvisible;
            if (!state.isInvisible || appearsGlowingWithInvisibility) {
                final int overlay = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
                poseStack.pushPose();
                poseStack.translate(0.0F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitButtercup(poseStack, submitNodeCollector, lightCoords, appearsGlowingWithInvisibility, state.outlineColor, state.buttercupModel, overlay);
                poseStack.popPose();

                poseStack.pushPose();
                poseStack.translate(0.2F, -0.35F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.1F, 0.0F, -0.7F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitButtercup(poseStack, submitNodeCollector, lightCoords, appearsGlowingWithInvisibility, state.outlineColor, state.buttercupModel, overlay);
                poseStack.popPose();

                poseStack.pushPose();
                poseStack.translate(0.3F, -0.35F, 0.4F);
                poseStack.mulPose(Axis.YP.rotationDegrees(42.0F));
                poseStack.translate(0.2F, 0.0F, -0.3F);
                poseStack.mulPose(Axis.YP.rotationDegrees(48.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitButtercup(poseStack, submitNodeCollector, lightCoords, appearsGlowingWithInvisibility, state.outlineColor, state.buttercupModel, overlay);
                poseStack.popPose();

                poseStack.pushPose();
                this.getParentModel().getHead().translateAndRotate(poseStack);
                poseStack.translate(0.0F, -0.7F, -0.2F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-78.0F));
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                poseStack.translate(-0.5F, -0.5F, -0.5F);
                this.submitButtercup(poseStack, submitNodeCollector, lightCoords, appearsGlowingWithInvisibility, state.outlineColor, state.buttercupModel, overlay);
                poseStack.popPose();
            }
        }
    }

    private void submitButtercup(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final boolean appearsGlowingWithInvisibility, final int outlineColor, final BlockModelRenderState buttercupModel, final int overlayCoords) {
        if (appearsGlowingWithInvisibility) {
            buttercupModel.submitOnlyOutline(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);
        } else {
            buttercupModel.submit(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);
        }

    }

}
