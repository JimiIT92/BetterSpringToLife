package org.hendrix.betterspringtolife.client.renderer.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.hendrix.betterspringtolife.client.renderer.entity.model.ButterflyModel;
import org.hendrix.betterspringtolife.entity.Butterfly;

@Environment(EnvType.CLIENT)
public class ButterflyRenderState extends LivingEntityRenderState {
    public Butterfly.Variant variant;
    public float flapAngle;
    public ButterflyModel.Pose pose;

    public ButterflyRenderState() {
        this.variant = Butterfly.Variant.RED;
        this.pose = ButterflyModel.Pose.FLYING;
    }

}
