package com.somrpg.swordofmagic7.Core.Sound;

import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public enum SomSound {
    Tick(Sound.BLOCK_LEVER_CLICK, 1, 1),
    Open(Sound.BLOCK_CHEST_OPEN, 1, 1),
    Close(Sound.BLOCK_CHEST_CLOSE, 1, 1),

    LevelUp(Sound.ENTITY_PLAYER_LEVELUP, 1, 1),
    Nope(Sound.BLOCK_NOTE_BLOCK_HARP, 1, 0),
    Equip(Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 1),

    Shoot(Sound.ENTITY_WITHER_SHOOT, 1, 1),
    ;

    private final Sound sound;
    private final float pitch;
    private final float volume;
    private final SoundCategory category = SoundCategory.PLAYERS;

    SomSound(Sound sound, float pitch, float volume) {
        this.sound = sound;
        this.pitch = pitch;
        this.volume = volume;
    }

    public void play(@NonNull Player player) {
        SomCore.getSomTask().AsyncTaskLater(() -> player.playSound(player.getLocation(), sound, category, volume, pitch), 1);
    }

    public void play(@NonNull Location location) {
        SomCore.getSomTask().AsyncTaskLater(() -> location.getWorld().playSound(location, sound, category, volume, pitch), 1);
    }
}
