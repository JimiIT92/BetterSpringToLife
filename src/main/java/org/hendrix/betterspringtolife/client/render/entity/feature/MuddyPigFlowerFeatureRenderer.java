package org.hendrix.betterspringtolife.client.render.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import org.hendrix.betterspringtolife.client.render.entity.state.MuddyPigEntityRenderState;
import org.hendrix.betterspringtolife.entity.MuddyPigEntity;

/**
 * Render a flower on top of a {@link MuddyPigEntity Muddy Pig}
 */
@Environment(EnvType.CLIENT)
public final class MuddyPigFlowerFeatureRenderer extends FeatureRenderer<MuddyPigEntityRenderState, PigEntityModel> {

    /**
     * The {@link BlockRenderManager Block Render Manager instance}
     */
    private final BlockRenderManager blockRenderManager;

    /**
     * Constructor. Set the render feature properties
     *
     * @param context The {@link FeatureRendererContext Feature Renderer Context}
     * @param blockRenderManager The {@link BlockRenderManager Block Render Manager instance}
     */
    public MuddyPigFlowerFeatureRenderer(final FeatureRendererContext<MuddyPigEntityRenderState, PigEntityModel> context, final BlockRenderManager blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    /**
     * Render the flower on top of a {@link MuddyPigEntity Muddy Pig}
     *
     * @param matrixStack The {@link MatrixStack render matrix stack}
     * @param orederedRenderCommandQueue The {@link OrderedRenderCommandQueue Ordered Render Command Queue provider}
     * @param light The {@link Integer client light}
     * @param muddyPigEntityRenderState The {@link MuddyPigEntityRenderState Muddy Pig Entity Render State}
     * @param limbAngle The {@link Float entity limb angle}
     * @param limbDistance The {@link Float entity limb distance}
     */
    @Override
    public void render(final MatrixStack matrixStack, final OrderedRenderCommandQueue orederedRenderCommandQueue, final int light, final MuddyPigEntityRenderState muddyPigEntityRenderState, final float limbAngle, final float limbDistance) {
        if (muddyPigEntityRenderState.hasFlower) {
            final boolean renderFlowerModel = muddyPigEntityRenderState.hasOutline() && muddyPigEntityRenderState.invisible;
            if (!muddyPigEntityRenderState.invisible || renderFlowerModel) {
                final BlockState blockState = Blocks.POPPY.getDefaultState();
                final int overlay = LivingEntityRenderer.getOverlay(muddyPigEntityRenderState, 0.0F);
                final BlockStateModel blockStateModel = this.blockRenderManager.getModel(blockState);

                matrixStack.push();
                ModelPart head = this.getContextModel().getRootPart().getChild("head");
                if(head != null) {
                    head.applyTransform(matrixStack);
                    matrixStack.translate(0.0F, -0.7F, -0.2F);
                    matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-65.0F));
                    matrixStack.scale(-0.5F, -0.5F, 0.5F);
                    matrixStack.translate(-0.3F, -1.1F, -0.75F);
                    matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-10.0F));
                    matrixStack.translate(0.0F, -0.115F, 0.0F);
                    this.renderFlower(matrixStack, orederedRenderCommandQueue, light, renderFlowerModel, muddyPigEntityRenderState.outlineColor, blockState, overlay, blockStateModel);
                }
                matrixStack.pop();
            }
        }
    }

    /**
     * Render a flower on top of a {@link MuddyPigEntity Muddy Pig}
     *
     * @param matrixStack The {@link MatrixStack render matrix stack}
     * @param orderedRenderCommandQueue The {@link OrderedRenderCommandQueue Ordered Render Command Queue provider}
     * @param light The {@link Integer client light}
     * @param renderAsModel {@link Boolean Whether the flower should be rendered on the entity model or not}
     * @param outline The {@link Integer entity outline color}
     * @param flowerState The {@link BlockState flower Block State}
     * @param overlay The {@link Integer client overlay value}
     * @param flowerModel The {@link BlockStateModel flower Block State Model}
     */
    private void renderFlower(final MatrixStack matrixStack, final OrderedRenderCommandQueue orderedRenderCommandQueue, final int light, final boolean renderAsModel, final int outline, final BlockState flowerState, final int overlay, final BlockStateModel flowerModel) {
        if (renderAsModel) {
            orderedRenderCommandQueue.submitBlockStateModel(matrixStack, RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE), flowerModel, 0.0F, 0.0F, 0.0F, light, overlay, outline);
        } else {
            orderedRenderCommandQueue.submitBlock(matrixStack, flowerState, light, overlay, outline);
        }
    }

}