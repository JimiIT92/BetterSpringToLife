package org.hendrix.betterspringtolife.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import org.hendrix.betterspringtolife.client.render.entity.state.ButterflyEntityRenderState;
import org.hendrix.betterspringtolife.entity.ButterflyEntity;

/**
 * Model class for a {@link ButterflyEntity Butterfly}
 */
@Environment(EnvType.CLIENT)
public final class ButterflyEntityModel extends EntityModel<ButterflyEntityRenderState> {

    /**
     * The {@link ModelPart Left Wing Model Part}
     */
    private final ModelPart leftWing;
    /**
     * The {@link ModelPart Right Wing Model Part}
     */
    private final ModelPart rightWing;

    /**
     * Constructor. Set the model properties
     *
     * @param root The {@link ModelPart root model part}
     */
    public ButterflyEntityModel(final ModelPart root) {
        super(root);
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
    }

    /**
     * Get the {@link TexturedModelData Textured Model Data}
     *
     * @return The {@link TexturedModelData Textured Model Data}
     */
    public static TexturedModelData getTexturedModelData() {
        final ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("body", ModelPartBuilder.create().uv(2, 8).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F), ModelTransform.of(0.0F, 20.5F, -3.0F, 0.8937F, 0.0F, 0.0F));
        modelPartData.addChild("left_wing", ModelPartBuilder.create().uv(19, 8).cuboid(-2.5F, 0.0F, 0F, 5.0F, 7.0F, 0.0F), ModelTransform.of(0.5F, 20.94F, -2.76F, -1.0981F, -(float)Math.PI, 0.0F));
        modelPartData.addChild("right_wing", ModelPartBuilder.create().uv(19, 8).cuboid(-2.5F, 0.0F, 0F, 5.0F, 7.0F, 0.0F), ModelTransform.of(-0.5F, 20.94F, -2.76F, -1.0981F, -(float)Math.PI, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }

    /**
     * Set the entity angles
     *
     * @param butterflyEntityRenderState The {@link ButterflyEntityRenderState Butterfly Entity Render State}
     */
    @Override
    public void setAngles(final ButterflyEntityRenderState butterflyEntityRenderState) {
        super.setAngles(butterflyEntityRenderState);
        switch (butterflyEntityRenderState.butterflyPose.ordinal()) {
            case 1:
                break;
            case 0:
            default:
                final float angle = butterflyEntityRenderState.flapAngle * 0.3F;
                this.leftWing.roll = -0.0873F - butterflyEntityRenderState.flapAngle;
                this.leftWing.originY += angle;
                this.rightWing.roll = 0.0873F + butterflyEntityRenderState.flapAngle;
                this.rightWing.originY += angle;
        }

    }

    /**
     * Get the {@link ButterflyEntityModel.Pose entity pose}
     *
     * @param butterfly The {@link ButterflyEntity entity}
     * @return The {@link ButterflyEntityModel.Pose entity pose}
     */
    public static ButterflyEntityModel.Pose getPose(final ButterflyEntity butterfly) {
        return butterfly.isInAir() ? ButterflyEntityModel.Pose.FLYING : ButterflyEntityModel.Pose.STANDING;
    }

    /**
     * The {@link ButterflyEntity Butterfly Entity Poses}
     */
    @Environment(EnvType.CLIENT)
    public enum Pose {
        FLYING,
        STANDING
    }

}