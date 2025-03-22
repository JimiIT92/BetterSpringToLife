package org.hendrix.betterspringtolife.core;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.utils.IdentifierUtils;

/**
 * {@link BetterSpringToLife Better Spring to Life} {@link JukeboxSong Jukebox Songs}
 */
public final class BSTLJukeboxSongs {

    //#region Jukebox Songs

    public static final RegistryKey<JukeboxSong> SWEDEN = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, IdentifierUtils.modIdentifier("sweden"));

    //#endregion

}