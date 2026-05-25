package org.hendrix.betterspringtolife.client.renderer.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Environment(EnvType.CLIENT)
public class MoobloomRenderState extends LivingEntityRenderState {

    public boolean hasFlowers;
    public final BlockModelRenderState buttercupModel;

    public MoobloomRenderState() {
        this.hasFlowers = true;
        this.buttercupModel = new BlockModelRenderState();
    }

}
