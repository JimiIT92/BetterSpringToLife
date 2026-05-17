package org.hendrix.betterspringtolife.core;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

public final class BSTLParticles {

    //#region Particles

    public static final SimpleParticleType ASPHODEL = register("asphodel");

    //#endregion

    private static SimpleParticleType register(final String name) {
        final ResourceKey<ParticleType<?>> particleResourceKey = ResourceKey.create(Registries.PARTICLE_TYPE, IdentifierUtils.modded(name));
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, particleResourceKey, FabricParticleTypes.simple());
    }

    public static void register() {

    }

}
