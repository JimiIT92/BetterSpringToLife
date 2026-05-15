package org.hendrix.betterspringtolife;

import net.fabricmc.api.ModInitializer;
import org.hendrix.betterspringtolife.core.BSTLBlocks;
import org.hendrix.betterspringtolife.core.BSTLEvents;

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
        BSTLBlocks.register();
        BSTLEvents.register();
    }

}