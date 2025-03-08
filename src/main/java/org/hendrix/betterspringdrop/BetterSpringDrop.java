package org.hendrix.betterspringdrop;

import net.fabricmc.api.ModInitializer;
import org.hendrix.betterspringdrop.core.*;
import org.hendrix.betterspringdrop.world.gen.BSDBiomeFeatures;

/**
 * Hendrix's Better Spring Drop
 * Improve the Spring Drop with new Hollow Logs, Firefly Jars, Butterflies and more!
 */
public final class BetterSpringDrop implements ModInitializer {

    /**
     * The {@link String Mod ID}
     */
    public static final String MOD_ID = "betterspringdrop";

    /**
     * Initialize the mod
     */
    @Override
    public void onInitialize() {
        BSDItemGroups.register();
        BSDDataComponentTypes.register();
        BSDItems.register();
        BSDBlocks.register();

        BSDBlocks.registerStrippables();
        BSDBlocks.registerFlammableBlocks();
        BSDBlocks.registerCompostableBlocks();

        BSDBiomeFeatures.modifyBiomes();

        BSDEvents.register();
    }

}