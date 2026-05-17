package org.hendrix.betterspringtolife;

import net.fabricmc.api.ModInitializer;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.core.BSTLEvents;
import org.hendrix.betterspringtolife.core.BSTLItems;
import org.hendrix.betterspringtolife.core.BSTLParticles;

/**
 * Hendrix's Better Spring to Life
 * Improve the Minecraft Forests with new Hollow Logs, Firefly Jars, new ambient mobs and more!
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

        BSTLParticles.register();
        BSTLItems.register();
        BSTLBlocks.register();
        BSTLEvents.register();

    }

}