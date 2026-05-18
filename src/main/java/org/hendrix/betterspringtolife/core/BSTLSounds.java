package org.hendrix.betterspringtolife.core;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

public class BSTLSounds {

    //#region Sounds

    public static final SoundEvent ENTITY_MOOBLOOM_SUSPICIOUS_MILK = registerSound("entity.moobloom.suspicious_milk");
    public static final SoundEvent ENTITY_MOOBLOOM_SHEAR = registerSound("entity.moobloom.shear");
    public static final Holder.Reference<SoundEvent> MUSIC_DISC_SWEDEN = registerForHolder("music_disc_sweden");

    //#endregion

    /**
     * Register a {@link SoundEvent}
     *
     * @param name The sound name
     * @return The registered {@link SoundEvent}
     */
    private static SoundEvent registerSound(final String name) {
        final Identifier identifier = IdentifierUtils.modded(name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    private static Holder.Reference<SoundEvent> registerForHolder(final String name) {
        final Identifier identifier = IdentifierUtils.modded(name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void register() {

    }

}
