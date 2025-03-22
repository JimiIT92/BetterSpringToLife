package org.hendrix.betterspringtolife.client.render.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import org.hendrix.betterspringtolife.client.render.entity.state.MoobloomEntityRenderState;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.entity.MoobloomEntity;

/**
 * Render flowers on top of a {@link MoobloomEntity Moobloom}
 */
@Environment(EnvType.CLIENT)
public final class MoobloomFlowersFeatureRenderer extends FeatureRenderer<MoobloomEntityRenderState, CowEntityModel> {

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
    public MoobloomFlowersFeatureRenderer(final FeatureRendererContext<MoobloomEntityRenderState, CowEntityModel> context, final BlockRenderManager blockRenderManager) {
        super(context);
        this.blockRenderManager = blockRenderManager;
    }

    /**
     * Render the flowers on top of a {@link MoobloomEntity Moobloom}
     *
     * @param matrixStack The {@link MatrixStack render matrix stack}
     * @param vertexConsumerProvider The {@link VertexConsumerProvider Vertex Consumer Provider instance}
     * @param light The {@link Integer client light}
     * @param moobloomEntityRenderState The {@link MoobloomEntityRenderState Moobloom Entity Render State}
     * @param limbAngle The {@link Float entity limb angle}
     * @param limbDistance The {@link Float entity limb distance}
     */
    @Override
    public void render(final MatrixStack matrixStack, final VertexConsumerProvider vertexConsumerProvider, final int light, final MoobloomEntityRenderState moobloomEntityRenderState, final float limbAngle, final float limbDistance) {
        if (!moobloomEntityRenderState.baby && moobloomEntityRenderState.hasFlowers) {
            final boolean renderFlowerModel = moobloomEntityRenderState.hasOutline && moobloomEntityRenderState.invisible;
            if (!moobloomEntityRenderState.invisible || renderFlowerModel) {
                final BlockState blockState = BSTLBlocks.BUTTERCUP.getDefaultState();
                final int overlay = LivingEntityRenderer.getOverlay(moobloomEntityRenderState, 0.0F);
                final BlockStateModel blockStateModel = this.blockRenderManager.getModel(blockState);

                matrixStack.push();
                matrixStack.translate(0.0F, -0.35F, 0.5F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderFlower(matrixStack, vertexConsumerProvider, light, renderFlowerModel, blockState, overlay, blockStateModel);
                matrixStack.pop();

                matrixStack.push();
                matrixStack.translate(0.2F, -0.35F, 0.5F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(42.0F));
                matrixStack.translate(0.1F, 0.0F, -0.7F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderFlower(matrixStack, vertexConsumerProvider, light, renderFlowerModel, blockState, overlay, blockStateModel);
                matrixStack.pop();

                matrixStack.push();
                matrixStack.translate(0.3F, -0.35F, 0.4F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(42.0F));
                matrixStack.translate(0.2F, 0.0F, -0.3F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(48.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderFlower(matrixStack, vertexConsumerProvider, light, renderFlowerModel, blockState, overlay, blockStateModel);
                matrixStack.pop();

                matrixStack.push();
                this.getContextModel().getHead().applyTransform(matrixStack);
                matrixStack.translate(0.0F, -0.7F, -0.2F);
                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-78.0F));
                matrixStack.scale(-1.0F, -1.0F, 1.0F);
                matrixStack.translate(-0.5F, -0.5F, -0.5F);
                this.renderFlower(matrixStack, vertexConsumerProvider, light, renderFlowerModel, blockState, overlay, blockStateModel);
                matrixStack.pop();
            }
        }
    }

    /**
     * Render a flower on top of a {@link MoobloomEntity Moobloom}
     *
     * @param matrixStack The {@link MatrixStack render matrix stack}
     * @param vertexConsumerProvider The {@link VertexConsumerProvider Vertex Consumer Provider instance}
     * @param light The {@link Integer client light}
     * @param renderAsModel {@link Boolean Whether the flower should be rendered on the entity model or not}
     * @param flowerState The {@link BlockState flower Block State}
     * @param overlay The {@link Integer client overlay value}
     * @param flowerModel The {@link BlockStateModel flower Block State Model}
     */
    private void renderFlower(final MatrixStack matrixStack, final VertexConsumerProvider vertexConsumerProvider, final int light, final boolean renderAsModel, final BlockState flowerState, final int overlay, final BlockStateModel flowerModel) {
        if (renderAsModel) {
            BlockModelRenderer.render(matrixStack.peek(), vertexConsumerProvider.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), flowerModel, 0.0F, 0.0F, 0.0F, light, overlay);
        } else {
            this.blockRenderManager.renderBlockAsEntity(flowerState, matrixStack, vertexConsumerProvider, light, overlay);
        }
    }

}