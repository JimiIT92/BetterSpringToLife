package org.hendrix.betterspringdrop.client.render.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.hendrix.betterspringdrop.client.render.entity.model.ButterflyEntityModel;
import org.hendrix.betterspringdrop.entity.ButterflyEntity;

/**
 * The {@link ButterflyEntity Butterfly} {@link LivingEntityRenderState Entity Render State}
 */
@Environment(EnvType.CLIENT)
public class ButterflyEntityRenderState extends LivingEntityRenderState {

    public float flapAngle;
    public ButterflyEntityModel.Pose butterflyPose;

    /**
     * Constructor. Set the render state properties
     */
    public ButterflyEntityRenderState() {
        this.butterflyPose = ButterflyEntityModel.Pose.FLYING;
    }

}