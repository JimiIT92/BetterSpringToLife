package org.hendrix.betterspringtolife.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.animal.pig.BabyPigModel;
import net.minecraft.client.model.animal.pig.PigModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SimpleEquipmentLayer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Blocks;
import org.hendrix.betterspringtolife.client.BetterSpringToLifeClient;
import org.hendrix.betterspringtolife.client.renderer.entity.layer.MuddyPigFlowerLayer;
import org.hendrix.betterspringtolife.client.renderer.entity.state.MuddyPigRenderState;
import org.hendrix.betterspringtolife.entity.MuddyPig;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

@Environment(EnvType.CLIENT)
public class MuddyPigRenderer extends MobRenderer<MuddyPig, MuddyPigRenderState, PigModel> {

    private final AdultAndBabyModelPair<PigModel> adultAndBabyModelPair;
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    private final BlockModelResolver blockModelResolver;

    public MuddyPigRenderer(EntityRendererProvider.Context context) {
        super(context, new PigModel(context.bakeLayer(ModelLayers.PIG)), 0.7F);
        this.adultAndBabyModelPair = new AdultAndBabyModelPair(new PigModel(context.bakeLayer(BetterSpringToLifeClient.MUDDY_PIG)), new BabyPigModel(context.bakeLayer(BetterSpringToLifeClient.MUDDY_PIG_BABY)));
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new SimpleEquipmentLayer<>(this, context.getEquipmentRenderer(), EquipmentClientInfo.LayerType.PIG_SADDLE, (state) -> state.saddle, new PigModel(context.bakeLayer(ModelLayers.PIG_SADDLE)), null));
        this.addLayer(new MuddyPigFlowerLayer(this));
    }

    public void submit(final MuddyPigRenderState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera) {
        this.model = this.adultAndBabyModelPair.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
    @Override
    public Identifier getTextureLocation(MuddyPigRenderState state) {
        return IdentifierUtils.modded("textures/entity/" + (state.isBaby ? BetterSpringToLifeClient.MUDDY_PIG_BABY.model().getPath() : BetterSpringToLifeClient.MUDDY_PIG.model().getPath()) + ".png");
    }

    @Override
    public MuddyPigRenderState createRenderState() {
        return new MuddyPigRenderState();
    }

    public void extractRenderState(final MuddyPig entity, final MuddyPigRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.saddle = entity.getItemBySlot(EquipmentSlot.SADDLE).copy();
        state.hasFlowers = entity.hasFlowers();
        this.blockModelResolver.update(state.poppyModel, Blocks.POPPY.defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
    }
}
