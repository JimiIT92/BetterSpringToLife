package org.hendrix.betterspringdrop.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AscendingParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

/**
 * Implementation class for the {@link AscendingParticle Asphodel Particle}
 */
@Environment(EnvType.CLIENT)
public class AsphodelParticle extends AscendingParticle {

    /**
     * Constructor. Set the particle properties
     *
     * @param world The {@link ClientWorld World reference}
     * @param x The {@link Double Particle X coordinate}
     * @param y The {@link Double Particle Y coordinate}
     * @param z The {@link Double Particle Z coordinate}
     * @param velocityX The {@link Double Particle X velocity}
     * @param velocityY The {@link Double Particle Y velocity}
     * @param velocityZ The {@link Double Particle Z velocity}
     * @param scaleMultiplier The {@link Float Particle Scale multiplier}
     * @param spriteProvider The {@link SpriteProvider Sprite Provider reference}
     */
    protected AsphodelParticle(final ClientWorld world, final double x, final double y, final double z, final double velocityX, final double velocityY, final double velocityZ, final float scaleMultiplier, final SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.1F, -0.1F, 0.1F, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, 0.0F, 20, 0.0125F, false);
        this.red = 1.0F;
        this.green = 1.0F;
        this.blue = 1.0F;
    }

    /**
     * Implementation class for the {@link ParticleFactory<SimpleParticleType> Apshodel Particle Factory}
     */
    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {

        /**
         * The {@link SpriteProvider Sprite Provider reference}
         */
        private final SpriteProvider spriteProvider;

        /**
         * Constructor. Set the {@link SpriteProvider Sprite Provider reference}
         *
         * @param spriteProvider The {@link SpriteProvider Sprite Provider reference}
         */
        public Factory(final SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        /**
         * Spawn a {@link SimpleParticleType Asphodel Particle}
         *
         * @param simpleParticleType The {@link SimpleParticleType Asphodel Particle}
         * @param world The {@link ClientWorld World reference}
         * @param x The {@link Double Particle X coordinate}
         * @param y The {@link Double Particle Y coordinate}
         * @param z The {@link Double Particle Z coordinate}
         * @param velocityX The {@link Double Particle X velocity}
         * @param velocityY The {@link Double Particle Y velocity}
         * @param velocityZ The {@link Double Particle Z velocity}
         * @return The {@link Particle spawned Particle}
         */
        public Particle createParticle(final SimpleParticleType simpleParticleType, final ClientWorld world, final double x, final double y, final double z, final double velocityX, final double velocityY, final double velocityZ) {
            final Random random = world.random;
            final double particleVelocityX = (double)random.nextFloat() * (double)random.nextFloat() * 0.1;
            final double particleVelocityY = (double)random.nextFloat() * (double)-0.5F * (double)random.nextFloat() * 0.1;
            final double particleVelocityZ = (double)random.nextFloat() * (double)random.nextFloat() * 0.1;
            return new AsphodelParticle(world, x, y, z, particleVelocityX, particleVelocityY, particleVelocityZ, 1.0F, this.spriteProvider);
        }
    }
}
