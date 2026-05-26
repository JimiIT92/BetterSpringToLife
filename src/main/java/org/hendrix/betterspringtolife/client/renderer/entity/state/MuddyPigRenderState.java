package org.hendrix.betterspringtolife.client.renderer.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class MuddyPigRenderState extends LivingEntityRenderState {

    public ItemStack saddle;
    public boolean hasFlowers;
    public final BlockModelRenderState poppyModel;

    public MuddyPigRenderState() {
        this.saddle = ItemStack.EMPTY;
        this.hasFlowers = true;
        this.poppyModel = new BlockModelRenderState();
    }

}
