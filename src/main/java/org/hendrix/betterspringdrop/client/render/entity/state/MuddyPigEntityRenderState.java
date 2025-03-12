package org.hendrix.betterspringdrop.client.render.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.item.ItemStack;
import org.hendrix.betterspringdrop.entity.MuddyPigEntity;

/**
 * The {@link MuddyPigEntity Muddy Pig} {@link LivingEntityRenderState Entity Render State}
 */
@Environment(EnvType.CLIENT)
public class MuddyPigEntityRenderState extends LivingEntityRenderState {

    /**
     * The {@link ItemStack Saddle Item Stack}
     */
    public ItemStack saddleStack;
    /**
     * {@link Boolean Whether the Muddy Pig has a flower}
     */
    public boolean hasFlower;

    /**
     * Constructor. Set the render state properties
     */
    public MuddyPigEntityRenderState() {
        this.hasFlower = true;
    }

}