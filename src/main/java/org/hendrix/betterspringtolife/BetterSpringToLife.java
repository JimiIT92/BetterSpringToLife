package org.hendrix.betterspringtolife;

import net.fabricmc.api.ModInitializer;
import org.hendrix.betterspringtolife.core.*;
import org.hendrix.betterspringtolife.world.gen.BSTLBiomeFeatures;

/**
 * Hendrix's Better Spring to Life
 * Improve the Spring to Life Drop with new Hollow Logs, Firefly Jars, Butterflies and more!
 */
public final class BetterSpringToLife implements ModInitializer {

    /**
     * The {@link String Mod ID}
     */
    public static final String MOD_ID = "betterspringtolife";

    /**
     * Initialize the mod
     */
    @Override
    public void onInitialize() {
        BSTLSounds.register();
        BSTLItemGroups.register();
        BSTLDataComponentTypes.register();
        BSTLParticles.register();
        BSTLItems.register();
        BSTLBlocks.register();
        BSTLEntities.register();

        BSTLBlocks.registerStrippables();
        BSTLBlocks.registerFlammableBlocks();
        BSTLBlocks.registerCompostableBlocks();

        BSTLBiomeFeatures.modifyBiomes();

        BSTLEvents.register();
    }

}