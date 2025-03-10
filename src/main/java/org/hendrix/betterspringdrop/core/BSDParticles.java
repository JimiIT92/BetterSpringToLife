package org.hendrix.betterspringdrop.core;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link ParticleType Particles}
 */
public final class BSDParticles {

    //#region Particles

    public static final SimpleParticleType ASPHODEL = registerParticle("asphodel");

    //#endregion

    /**
     * Register a {@link SimpleParticleType Particle}
     *
     * @param name The {@link String particle name}
     * @return The {@link SimpleParticleType registered Particle}
     */
    private static SimpleParticleType registerParticle(final String name) {
        return Registry.register(Registries.PARTICLE_TYPE, IdentifierUtils.modIdentifier(name),  FabricParticleTypes.simple());
    }

    /**
     * Register all {@link ParticleType Particles}
     */
    public static void register() {

    }

}