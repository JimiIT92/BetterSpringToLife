package org.hendrix.betterspringdrop.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import org.hendrix.betterspringdrop.client.render.entity.feature.MuddyPigFlowerFeatureRenderer;
import org.hendrix.betterspringdrop.client.render.entity.state.MuddyPigEntityRenderState;
import org.hendrix.betterspringdrop.entity.MuddyPigEntity;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

/**
 * Renderer class for a {@link MuddyPigEntity Muddy Pig}
 */
@Environment(EnvType.CLIENT)
public class MuddyPigEntityRenderer extends MobEntityRenderer<MuddyPigEntity, MuddyPigEntityRenderState, PigEntityModel> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context The {@link EntityRendererFactory.Context Entity Renderer Factory Context}
     */
    public MuddyPigEntityRenderer(final EntityRendererFactory.Context context) {
        super(context, new PigEntityModel(context.getPart(EntityModelLayers.PIG)), 0.7F);
        this.addFeature(new MuddyPigFlowerFeatureRenderer(this, context.getBlockRenderManager()));
        this.addFeature(new SaddleFeatureRenderer<>(this, context.getEquipmentRenderer(), EquipmentModel.LayerType.PIG_SADDLE, (pigEntityRenderState) -> pigEntityRenderState.saddleStack, new PigEntityModel(context.getPart(EntityModelLayers.PIG_SADDLE)), new PigEntityModel(context.getPart(EntityModelLayers.PIG_BABY_SADDLE))));
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