package org.hendrix.betterspringdrop.core;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link SoundEvent Sounds}
 */
public final class BSDSounds {

    //#region Sounds

    public static final RegistryEntry.Reference<SoundEvent> MUSIC_DISC_BLOCK_BY_BLOCK = registerSoundReference("music_disc_block_by_block");

    //#endregion

    /**
     * Register a {@link RegistryEntry.Reference<SoundEvent> Sound Reference}
     *
     * @param name The {@link String sound name}
     * @return The {@link RegistryEntry.Reference<SoundEvent> Sound Reference}
     */
    private static RegistryEntry.Reference<SoundEvent> registerSoundReference(final String name) {
        Identifier identifier = IdentifierUtils.modIdentifier(name);
        return Registry.registerReference(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    /**
     * Register all {@link SoundEvent Sounds}
     */
    public static void register() {

    }

}