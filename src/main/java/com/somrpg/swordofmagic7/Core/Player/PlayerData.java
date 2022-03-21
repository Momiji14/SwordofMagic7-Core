package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Inventory.*;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Menu.PlayerSettingMenu;
import com.somrpg.swordofmagic7.Core.Menu.PlayerUserMenu;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerDataInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerViewUpdate;
import com.somrpg.swordofmagic7.Core.SomCore;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public class PlayerData implements PlayerDataInterface, PlayerViewUpdate {

    private static final HashMap<String, PlayerData> playerDataList = new HashMap<>();
    public static PlayerData getData(Player player) {
        String uuid = String.valueOf(player.getUniqueId());
        if (!playerDataList.containsKey(uuid)) {
            playerDataList.put(uuid, new PlayerData(player));
        }
        return playerDataList.get(uuid);
    }
    public static Collection<PlayerData> getDataList() {
        return playerDataList.values();
    }

    private final Player player;

    private File playerFile;
    private final PlayerEntity playerEntity;
    private final PlayerCharacon playerCharacon;
    private final PlayerViewBar playerViewBar;
    private final PlayerBank playerBank;
    private final PlayerSetting playerSetting;
    private final PlayerInput playerInput;
    private final PlayerStatistics playerStatistics;

    private final ItemInventory itemInventory;
    private final RuneInventory runeInventory;
    private final PetInventory petInventory;

    private final PlayerUserMenu playerUserMenu;
    private final PlayerSettingMenu playerSettingMenu;

    private MapDataInterface mapData;

    PlayerData(Player player) {
        this.player = player;
        playerEntity = new PlayerEntity(this);
        playerCharacon = new PlayerCharacon(this);
        playerViewBar = new PlayerViewBar(this);
        playerBank = new PlayerBank(this);
        playerInput = new PlayerInput(this);
        playerSetting = new PlayerSetting(this);
        playerStatistics = new PlayerStatistics(this);

        itemInventory = new ItemInventory(this);
        runeInventory = new RuneInventory(this);
        petInventory = new PetInventory(this);

        playerUserMenu = new PlayerUserMenu(this);
        SomCore.getJavaPlugin().getCommand("m").setExecutor(playerUserMenu);
        playerSettingMenu = new PlayerSettingMenu(this);
        SomCore.getJavaPlugin().getCommand("setting").setExecutor(playerSettingMenu);
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

    public PlayerStatistics getPlayerStatistics() {
        return playerStatistics;
    }

    public PlayerUserMenu getUserMenu() {
        return playerUserMenu;
    }

    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    public RuneInventory getRuneInventory() {
        return runeInventory;
    }

    public PetInventory getPetInventory() {
        return petInventory;
    }

    public BaseInventory getBaseInventory(SomInventoryType type) {
        switch (type) {
            case ItemInventory -> {
                return getItemInventory();
            }
            case RuneInventory -> {
                return getRuneInventory();
            }
            case PetInventory -> {
                return getPetInventory();
            }
        }
        return null;
    }

    public void setMapData(MapData mapData) {
        this.mapData = mapData;
    }

    public MapDataInterface getMapData() {
        return mapData;
    }

    public void save() {
        try {
            playerFile = new File(DataBasePath, "PlayerData/" + player.getUniqueId() + ".yml");
            if (!playerFile.exists()) playerFile.createNewFile();
            FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);

            data.set("Mel", playerBank.getMel());
            data.set("Level", playerEntity.getLevel());
            data.set("Exp", playerEntity.getExp());
            data.set("Health", playerEntity.getHealth());
            data.set("Mana", playerEntity.getMana());

            data.set("ItemInventory", itemInventory.getContentsToString());
            data.set("RuneInventory", runeInventory.getContentsToString());
            data.set("PetInventory", petInventory.getContentsToString());

            data.save(playerFile);
            sendMessage("§eプレイヤーデータ§aの§b保存§aが§b完了§aしました", SomSound.Tick);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eプレイヤーデータ§aの§b保存§aに§c失敗§aしました", SomSound.Nope);}
    }

    public void load() {
        try {
            playerFile = new File(DataBasePath, "PlayerData/" + player.getUniqueId() + ".yml");
            if (!playerFile.exists()) {
                getPlayerEntity().statusUpdate();
                getPlayerEntity().setHealth(getPlayerEntity().getMaxHealth());
                getPlayerEntity().setMana(getPlayerEntity().getMana());
                return;
            }
            FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);

            playerBank.setMel(data.getInt("Mel", 10000));
            playerEntity.setLevel(data.getInt("Level", 1));
            playerEntity.setExp(data.getInt("Exp", 0));
            playerEntity.setHealthUnsafe(data.getDouble("Health", Double.MAX_VALUE));
            playerEntity.setManaUnsafe(data.getDouble("Mana", Double.MAX_VALUE));

            itemInventory.fromContentsFromString(data.getStringList("ItemInventory"));
            runeInventory.fromContentsFromString(data.getStringList("RuneInventory"));
            petInventory.fromContentsFromString(data.getStringList("PetInventory"));

            getPlayerEntity().statusUpdate();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aが§b完了§aしました", SomSound.Tick);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aに§c失敗§aしました", SomSound.Nope);
        }
    }
}
