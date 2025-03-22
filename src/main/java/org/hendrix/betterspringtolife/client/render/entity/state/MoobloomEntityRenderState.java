package org.hendrix.betterspringtolife.client.render.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.hendrix.betterspringtolife.entity.MoobloomEntity;

/**
 * The {@link MoobloomEntity Moobloom} {@link LivingEntityRenderState Entity Render State}
 */
@Environment(EnvType.CLIENT)
public final class MoobloomEntityRenderState extends LivingEntityRenderState {

    /**
     * {@link Boolean Whether the Moobloom has flowers}
     */
    public boolean hasFlowers;

    /**
     * Constructor. Set the render state properties
     */
    public MoobloomEntityRenderState() {
        this.hasFlowers = true;
    }

}