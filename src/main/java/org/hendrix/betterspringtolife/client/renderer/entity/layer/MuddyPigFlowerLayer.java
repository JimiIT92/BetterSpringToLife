package org.hendrix.betterspringtolife.client.renderer.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.pig.PigModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import org.hendrix.betterspringtolife.client.renderer.entity.state.MuddyPigRenderState;

@Environment(EnvType.CLIENT)
public class MuddyPigFlowerLayer extends RenderLayer<MuddyPigRenderState, PigModel> {


    public MuddyPigFlowerLayer(RenderLayerParent<MuddyPigRenderState, PigModel> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, MuddyPigRenderState state, float yRot, float xRot) {
        if (state.hasFlowers) {
            final boolean appearsGlowingWithInvisibility = state.appearsGlowing() && state.isInvisible;
            if (!state.isInvisible || appearsGlowingWithInvisibility) {
                final int overlay = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
                poseStack.pushPose();
                ModelPart head = this.getParentModel().getChildPart("head");
                if(head != null) {
                    head.translateAndRotate(poseStack);
                    poseStack.translate(0.0F, -0.7F, -0.2F);
                    poseStack.mulPose(Axis.YP.rotationDegrees(-65.0F));
                    poseStack.scale(-0.5F, -0.5F, 0.5F);
                    poseStack.translate(-0.3F, -1.1F, -0.75F);
                    poseStack.mulPose(Axis.XP.rotationDegrees(-10.0F));
                    poseStack.translate(0.0F, -0.115F, 0.0F);
                    this.submitPoppy(poseStack, submitNodeCollector, lightCoords, appearsGlowingWithInvisibility, state.outlineColor, state.poppyModel, overlay);
                }
                poseStack.popPose();
            }
        }
    }

    private void submitPoppy(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final boolean appearsGlowingWithInvisibility, final int outlineColor, final BlockModelRenderState poppyModel, final int overlayCoords) {
        if (appearsGlowingWithInvisibility) {
            poppyModel.submitOnlyOutline(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);
        } else {
            poppyModel.submit(poseStack, submitNodeCollector, lightCoords, overlayCoords, outlineColor);
        }

    }

}
