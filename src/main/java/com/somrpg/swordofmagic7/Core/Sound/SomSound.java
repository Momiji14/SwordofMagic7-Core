package com.somrpg.swordofmagic7.Core.Sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public enum SomSound {
    Tick(Sound.BLOCK_LEVER_CLICK, 1, 1),
    Open(Sound.BLOCK_CHEST_OPEN, 1, 1),
    Close(Sound.BLOCK_CHEST_CLOSE, 1, 1),

    LevelUp(Sound.ENTITY_PLAYER_LEVELUP, 1, 1),
    Nope(Sound.BLOCK_NOTE_BLOCK_HARP, 1, 0),
    Equip(Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 1),
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

    public void play(Player player) {
        player.playSound(player.getLocation(), sound, category, volume, pitch);
    }

    public void play(Location location) {
        location.getWorld().playSound(location, sound, category, volume, pitch);
    }
}
