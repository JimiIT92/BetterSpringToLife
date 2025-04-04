package org.hendrix.betterspringtolife.client.render.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.hendrix.betterspringtolife.client.render.entity.model.ButterflyEntityModel;
import org.hendrix.betterspringtolife.entity.ButterflyEntity;
import org.jetbrains.annotations.Nullable;

/**
 * The {@link ButterflyEntity Butterfly} {@link LivingEntityRenderState Entity Render State}
 */
@Environment(EnvType.CLIENT)
public final class ButterflyEntityRenderState extends LivingEntityRenderState {

    /**
     * The {@link Float Butterfly Wing Flap angle}
     */
    public float flapAngle;
    /**
     * The {@link ButterflyEntityModel.Pose Butterfly Pose}
     */
    public ButterflyEntityModel.Pose butterflyPose;
    /**
     * The {@link ButterflyEntity.Variant Butterfly Variant}
     */
    @Nullable
    public ButterflyEntity.Variant variant;

    /**
     * Constructor. Set the render state properties
     */
    public ButterflyEntityRenderState() {
        this.butterflyPose = ButterflyEntityModel.Pose.FLYING;
    }

}