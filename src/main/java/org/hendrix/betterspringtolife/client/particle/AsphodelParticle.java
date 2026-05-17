package org.hendrix.betterspringtolife.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

@Environment(EnvType.CLIENT)
public class AsphodelParticle extends BaseAshSmokeParticle {

    protected AsphodelParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, float scale, SpriteSet sprites) {
        super(level, x, y, z, 0.1F, -0.1F, 0.1F, xa, ya, za, scale, sprites, 0, 20, 0.0125F, false);
        this.rCol = 1.0F;
        this.gCol = 1.0F;
        this.bCol = 1.0F;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(final SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(final SimpleParticleType options, final ClientLevel level, final double x, final double y, final double z, final double xAux, final double yAux, final double zAux, final RandomSource random) {
            final double particleVelocityX = (double)random.nextFloat() * (double)random.nextFloat() * 0.1;
            final double particleVelocityY = (double)random.nextFloat() * (double)-0.5F * (double)random.nextFloat() * 0.1;
            final double particleVelocityZ = (double)random.nextFloat() * (double)random.nextFloat() * 0.1;
            return new AsphodelParticle(level, x, y, z, particleVelocityX, particleVelocityY, particleVelocityZ, 1.0F, this.sprites);
        }
    }
}
