package org.hendrix.betterspringtolife.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.hendrix.betterspringtolife.client.renderer.entity.state.ButterflyRenderState;
import org.hendrix.betterspringtolife.entity.Butterfly;

@Environment(EnvType.CLIENT)
public class ButterflyModel extends EntityModel<ButterflyRenderState> {

    private final ModelPart leftWing;
    private final ModelPart rightWing;

    public ButterflyModel(final ModelPart root) {
        super(root);
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(2, 8).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 20.5F, -3.0F, 0.8937F, 0.0F, 0.0F));
        root.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(19, 8).addBox(-2.5F, 0.0F, 0F, 5.0F, 7.0F, 0.0F), PartPose.offsetAndRotation(0.5F, 20.94F, -2.76F, -1.0981F, -(float)Math.PI, 0.0F));
        root.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(19, 8).addBox(-2.5F, 0.0F, 0F, 5.0F, 7.0F, 0.0F), PartPose.offsetAndRotation(-0.5F, 20.94F, -2.76F, -1.0981F, -(float)Math.PI, 0.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void setupAnim(final ButterflyRenderState state) {
        super.setupAnim(state);
        switch (state.pose.ordinal()) {
            case 1:
                break;
            case 0:
            default:
                float angle = state.flapAngle * 0.3F;
                this.leftWing.zRot = -0.0873F - state.flapAngle;
                this.leftWing.y += angle;
                this.rightWing.zRot = 0.0873F + state.flapAngle;
                this.rightWing.y += angle;
        }
    }

    public static ButterflyModel.Pose getPose(final Butterfly entity) {
        return entity.isFlying() ? ButterflyModel.Pose.FLYING : ButterflyModel.Pose.STANDING;
    }

    @Environment(EnvType.CLIENT)
    public enum Pose {
        FLYING,
        STANDING
    }
}