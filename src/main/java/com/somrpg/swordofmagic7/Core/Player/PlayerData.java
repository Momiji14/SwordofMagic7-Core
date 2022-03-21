package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Menu.PlayerUserMenu;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public class PlayerData implements PlayerDataInterface {

    private static final HashMap<UUID, PlayerData> playerDataList = new HashMap<>();
    public static PlayerData getData(Player player) {
        UUID uuid = player.getUniqueId();
        if (!playerDataList.containsKey(uuid)) {
            playerDataList.put(uuid, new PlayerData(player));
        }
        return playerDataList.get(uuid);
    }

    private final Player player;

    private final File playerFile;
    private final FileConfiguration playerData;
    private final PlayerEntity playerEntity;
    private final PlayerCharacon playerCharacon;
    private final PlayerViewBar playerViewBar;
    private final PlayerBank playerBank;
    private final PlayerSetting playerSetting;
    private final PlayerInput playerInput;

    private final PlayerUserMenu userMenu;

    PlayerData(Player player) {
        this.player = player;
        playerEntity = new PlayerEntity(this);
        playerCharacon = new PlayerCharacon(this);
        playerViewBar = new PlayerViewBar(this);
        playerBank = new PlayerBank(this);
        playerInput = new PlayerInput(this);
        playerSetting = new PlayerSetting(this);

        userMenu = new PlayerUserMenu(this);
        SomCore.getJavaPlugin().getCommand("menu").setExecutor(userMenu);

        playerFile = new File(DataBasePath, "PlayerData/" + player.getUniqueId() + ".yml");
        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playerData = YamlConfiguration.loadConfiguration(playerFile);
    }

    @Override
    public PlayerData getPlayerData() {
        return this;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public PlayerCharacon getPlayerCharacon() {
        return playerCharacon;
    }

    public PlayerViewBar getPlayerViewBar() {
        return playerViewBar;
    }

    public PlayerBank getPlayerBank() {
        return playerBank;
    }

    public PlayerSetting getPlayerSetting() {
        return playerSetting;
    }

    public PlayerInput getPlayerInput() {
        return playerInput;
    }

    public PlayerUserMenu getUserMenu() {
        return userMenu;
    }

    public void save() {
        try {
            playerData.set("Mel", playerBank.getMel());
            playerData.set("Level", playerEntity.getLevel());
            playerData.set("Exp", playerEntity.getExp());
            playerData.set("Health", playerEntity.getHealth());
            playerData.set("Mana", playerEntity.getMana());

            playerData.save(playerFile);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eセーブデータ§aの§b保存§aに§c失敗§aしました", SomSound.Nope);}
    }

    public void load() {
        try {
            playerBank.setMel(playerData.getInt("Mel", 10000));
            playerEntity.setLevel(playerData.getInt("Level", 1));
            playerEntity.setExp(playerData.getInt("Exp", 0));
            playerEntity.setHealth(playerData.getDouble("Health", Double.MAX_VALUE));
            playerEntity.setMana(playerData.getDouble("Mana", Double.MAX_VALUE));
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eセーブデータ§aの§b読み込み§aに§c失敗§aしました", SomSound.Nope);
        }
    }
}
