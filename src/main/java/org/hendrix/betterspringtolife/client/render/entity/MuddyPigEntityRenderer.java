package org.hendrix.betterspringtolife.client.render.entity;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.BabyModelPair;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.PigVariant;
import net.minecraft.util.Identifier;
import org.hendrix.betterspringtolife.client.render.entity.feature.MuddyPigFlowerFeatureRenderer;
import org.hendrix.betterspringtolife.client.render.entity.state.MuddyPigEntityRenderState;
import org.hendrix.betterspringtolife.entity.MuddyPigEntity;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

import java.util.EnumMap;
import java.util.Map;

/**
 * Renderer class for a {@link MuddyPigEntity Muddy Pig}
 */
@Environment(EnvType.CLIENT)
public final class MuddyPigEntityRenderer extends MobEntityRenderer<MuddyPigEntity, MuddyPigEntityRenderState, PigEntityModel> {

    /**
     * The {@link Map<PigVariant.Model> Entity Model Pairs}
     */
    private final Map<PigVariant.Model, BabyModelPair<PigEntityModel>> modelPairs;

    /**
     * Constructor. Set the renderer properties
     *
     * @param context The {@link EntityRendererFactory.Context Entity Renderer Factory Context}
     */
    public MuddyPigEntityRenderer(final EntityRendererFactory.Context context) {
        super(context, new PigEntityModel(context.getPart(EntityModelLayers.PIG)), 0.7F);
        this.modelPairs = createModelPairs(context);
        this.addFeature(new MuddyPigFlowerFeatureRenderer(this, context.getBlockRenderManager()));
        this.addFeature(new SaddleFeatureRenderer<>(this, context.getEquipmentRenderer(), EquipmentModel.LayerType.PIG_SADDLE, (pigEntityRenderState) -> pigEntityRenderState.saddleStack, new PigEntityModel(context.getPart(EntityModelLayers.PIG_SADDLE)), new PigEntityModel(context.getPart(EntityModelLayers.PIG_BABY_SADDLE))));
    }

    /**
     * Create the {@link Map<PigVariant.Model> Entity Model Pairs}
     *
     * @param context The {@link EntityRendererFactory.Context Entity Renderer Factory Context}
     * @return The {@link Map<PigVariant.Model> Entity Model Pairs}
     */
    private static EnumMap<PigVariant.Model, BabyModelPair<PigEntityModel>> createModelPairs(final EntityRendererFactory.Context context) {
        return Maps.newEnumMap(Map.of(PigVariant.Model.NORMAL, new BabyModelPair<>(new PigEntityModel(context.getPart(EntityModelLayers.PIG)), new PigEntityModel(context.getPart(EntityModelLayers.PIG_BABY)))));
    }

    /**
     * Get the {@link Identifier entity texture}
     *
     * @param state The {@link MuddyPigEntityRenderState Muddy Pig Entity Render State}
     * @return The {@link Identifier entity texture}
     */
    @Override
    public Identifier getTexture(final MuddyPigEntityRenderState state) {
        return IdentifierUtils.modIdentifier("textures/entity/muddy_pig.png");
    }

    /**
     * Get the {@link MuddyPigEntityRenderState Muddy Pig Entity Render State}
     *
     * @return The {@link MuddyPigEntityRenderState Muddy Pig Entity Render State}
     */
    @Override
    public MuddyPigEntityRenderState createRenderState() {
        return new MuddyPigEntityRenderState();
    }

    /**
     * Render the Entity
     *
     * @param muddyPigEntityRenderState The {@link MuddyPigEntityRenderState Muddy Pig Entity Render State}
     * @param matrixStack The {@link MatrixStack render matrix stack}
     * @param vertexConsumerProvider The {@link VertexConsumerProvider Vertex Consumer provider}
     * @param light The {@link Integer client light}
     */
    public void render(final MuddyPigEntityRenderState muddyPigEntityRenderState, final MatrixStack matrixStack, final VertexConsumerProvider vertexConsumerProvider, final int light) {
        this.model = this.modelPairs.get(PigVariant.Model.NORMAL).get(muddyPigEntityRenderState.baby);
        super.render(muddyPigEntityRenderState, matrixStack, vertexConsumerProvider, light);
    }

    /**
     * Update the {@link MuddyPigEntityRenderState Muddy Pig Entity Render State}
     *
     * @param muddyPigEntity The {@link MuddyPigEntity entity}
     * @param muddyPigEntityRenderState The {@link MuddyPigEntityRenderState Muddy Pig Entity Render State}
     * @param ticks The {@link Float entity ticks}
     */
    public void updateRenderState(final MuddyPigEntity muddyPigEntity, final MuddyPigEntityRenderState muddyPigEntityRenderState, final float ticks) {
        super.updateRenderState(muddyPigEntity, muddyPigEntityRenderState, ticks);
        muddyPigEntityRenderState.saddleStack = muddyPigEntity.getEquippedStack(EquipmentSlot.SADDLE).copy();
        muddyPigEntityRenderState.hasFlower = muddyPigEntity.hasFlower();
    }

}