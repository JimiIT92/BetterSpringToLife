package org.hendrix.betterspringtolife.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSources;
import org.hendrix.betterspringtolife.client.particle.AsphodelParticle;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.core.BSTLParticles;

import java.util.List;

@Environment(EnvType.CLIENT)
public final class BetterSpringToLifeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockColorRegistry.register(
                List.of(BlockTintSources.foliage()),
                BSTLBlocks.OAK_LEAVES_PILE,
                BSTLBlocks.JUNGLE_LEAVES_PILE,
                BSTLBlocks.ACACIA_LEAVES_PILE,
                BSTLBlocks.DARK_OAK_LEAVES_PILE,
                BSTLBlocks.MANGROVE_LEAVES_PILE
        );
        BlockColorRegistry.register(
                List.of(BlockTintSources.constant(-8345771)),
                BSTLBlocks.BIRCH_LEAVES_PILE
        );
        BlockColorRegistry.register(
                List.of(BlockTintSources.constant(-10380959)),
                BSTLBlocks.SPRUCE_LEAVES_PILE
        );
        BlockColorRegistry.register(
                List.of(BlockTintSources.grass()),
                BSTLBlocks.POTTED_BUSH
        );

        ParticleProviderRegistry.getInstance().register(BSTLParticles.ASPHODEL, AsphodelParticle.Provider::new);
    }
}
