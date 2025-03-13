package org.hendrix.betterspringdrop.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import org.hendrix.betterspringdrop.client.render.entity.state.ButterflyEntityRenderState;
import org.hendrix.betterspringdrop.entity.ButterflyEntity;

/**
 * Model class for a {@link ButterflyEntity Butterfly}
 */
@Environment(EnvType.CLIENT)
public class ButterflyEntityModel extends EntityModel<ButterflyEntityRenderState> {

    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart rightWing;

    /**
     * Constructor. Set the model properties
     *
     * @param root The {@link ModelPart root model part}
     */
    public ButterflyEntityModel(final ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
    }

    /**
     * Get the {@link TexturedModelData Textured Model Data}
     *
     * @return The {@link TexturedModelData Textured Model Data}
     */
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("body", ModelPartBuilder.create().uv(2, 8).cuboid(-2.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F), ModelTransform.of(0.0F, 16.5F, -3.0F, 0.4937F, 0.0F, 0.0F));
        modelPartData.addChild("left_wing", ModelPartBuilder.create().uv(19, 8).cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F), ModelTransform.of(1.5F, 16.94F, -2.76F, -0.6981F, -(float)Math.PI, 0.0F));
        modelPartData.addChild("right_wing", ModelPartBuilder.create().uv(19, 8).cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F), ModelTransform.of(-1.5F, 16.94F, -2.76F, -0.6981F, -(float)Math.PI, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    /**
     * Set the entity angles
     *
     * @param butterflyEntityRenderState The {@link ButterflyEntityRenderState Butterfly Entity Render State}
     */
    @Override
    public void setAngles(ButterflyEntityRenderState butterflyEntityRenderState) {
        super.setAngles(butterflyEntityRenderState);
        this.animateModel(butterflyEntityRenderState.butterflyPose);
        switch (butterflyEntityRenderState.butterflyPose.ordinal()) {
            case 1:
                break;
            case 0:
            default:
                float angle = butterflyEntityRenderState.flapAngle * 0.3F;
                this.leftWing.roll = -0.0873F - butterflyEntityRenderState.flapAngle;
                this.leftWing.originY += angle;
                this.rightWing.roll = 0.0873F + butterflyEntityRenderState.flapAngle;
                this.rightWing.originY += angle;
        }

    }

    private void animateModel(ButterflyEntityModel.Pose pose) {

    }

    public static ButterflyEntityModel.Pose getPose(ButterflyEntity butterfly) {
        return butterfly.isInAir() ? ButterflyEntityModel.Pose.FLYING : ButterflyEntityModel.Pose.STANDING;
    }

    @Environment(EnvType.CLIENT)
    public enum Pose {
        FLYING,
        STANDING
    }

}