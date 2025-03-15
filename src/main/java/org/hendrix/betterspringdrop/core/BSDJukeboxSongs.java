package org.hendrix.betterspringdrop.core;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.utils.IdentifierUtils;

/**
 * {@link BetterSpringDrop Better Spring Drop} {@link JukeboxSong Jukebox Songs}
 */
public final class BSDJukeboxSongs {

    //#region Jukebox Songs

    public static final RegistryKey<JukeboxSong> SWEDEN = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, IdentifierUtils.modIdentifier("sweden"));

    //#endregion

}