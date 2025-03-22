package org.hendrix.betterspringtolife.utils;

import net.minecraft.util.Identifier;
import org.hendrix.betterspringtolife.BetterSpringToLife;

/**
 * Utility methods for {@link Identifier Identifier}
 */
public final class IdentifierUtils {

    /**
     * Get a {@link Identifier modded Identifier}
     *
     * @param name The {@link String resource name}
     * @return The {@link Identifier modded Identifier}
     */
    public static Identifier modIdentifier(final String name) {
        return Identifier.of(BetterSpringToLife.MOD_ID, name);
    }

}