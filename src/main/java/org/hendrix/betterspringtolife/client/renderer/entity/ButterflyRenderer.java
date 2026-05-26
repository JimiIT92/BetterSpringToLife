package org.hendrix.betterspringtolife.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.parrot.Parrot;
import org.hendrix.betterspringtolife.client.BetterSpringToLifeClient;
import org.hendrix.betterspringtolife.client.renderer.entity.model.ButterflyModel;
import org.hendrix.betterspringtolife.client.renderer.entity.state.ButterflyRenderState;
import org.hendrix.betterspringtolife.entity.Butterfly;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

@Environment(EnvType.CLIENT)
public class ButterflyRenderer extends MobRenderer<Butterfly, ButterflyRenderState, ButterflyModel> {

    public ButterflyRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterflyModel(context.bakeLayer(BetterSpringToLifeClient.BUTTERFLY)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(ButterflyRenderState state) {
        if(state.variant == null) {
            state.variant = Butterfly.Variant.RED;
        }
        return IdentifierUtils.modded("textures/entity/butterfly/" + state.variant.getSerializedName() + ".png");
    }

    @Override
    public ButterflyRenderState createRenderState() {
        return new ButterflyRenderState();
    }

    public void extractRenderState(final Butterfly entity, final ButterflyRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.variant = entity.getVariant();
        float flap = Mth.lerp(partialTicks, entity.oFlap, entity.flap);
        float flapSpeed = Mth.lerp(partialTicks, entity.oFlapSpeed, entity.flapSpeed);
        state.flapAngle = (Mth.sin((double)flap) + 1.0F) * flapSpeed;
        state.pose = ButterflyModel.getPose(entity);
    }
}
