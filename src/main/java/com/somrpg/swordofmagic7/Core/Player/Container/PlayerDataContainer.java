package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Inventory.*;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Menu.SettingMenu;
import com.somrpg.swordofmagic7.Core.Menu.UserMenu;
import com.somrpg.swordofmagic7.Core.Menu.TeleportGateMenu;
import com.somrpg.swordofmagic7.Core.Player.*;
import com.somrpg.swordofmagic7.Core.Player.Interface.*;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public class PlayerDataContainer implements PlayerData {
    private static final HashMap<String, PlayerData> playerDataList = new HashMap<>();
    public static PlayerData getData(Player player) {
        String uuid = String.valueOf(player.getUniqueId());
        if (!playerDataList.containsKey(uuid)) {
            playerDataList.put(uuid, new PlayerDataContainer(player));
        }
        return playerDataList.get(uuid);
    }
    public static Collection<PlayerData> getDataList() {
        return playerDataList.values();
    }

    private final Player player;

    private File playerFile;
    private final PlayerEntityContainer playerEntity;
    private final PlayerSettingContainer playerSetting;
    private final PlayerStatisticsContainer playerStatistics;
    private final PlayerBankContainer playerBank;
    private final PlayerMenuContainer playerMenu;
    private final PlayerOtherContainer playerOther;

    private final PlayerCharacon playerCharacon;
    private final PlayerDisplay playerDisplay;
    private final PlayerInput playerInput;

    private final ItemInventory itemInventory;
    private final RuneInventory runeInventory;
    private final PetInventory petInventory;


    private MapDataInterface mapData;

    PlayerDataContainer(Player player) {
        this.player = player;
        playerEntity = new PlayerEntityContainer(this);
        playerSetting = new PlayerSettingContainer(this);
        playerStatistics = new PlayerStatisticsContainer(this);
        playerMenu = new PlayerMenuContainer(this);
        playerBank = new PlayerBankContainer(this);

        playerOther = new PlayerOtherContainer(this);

        playerCharacon = new PlayerCharacon(this);
        playerDisplay = new PlayerDisplayContainer(this);
        playerInput = new PlayerInputContainer(this);

        itemInventory = new ItemInventoryContainer(this);
        runeInventory = new RuneInventoryContainer(this);
        petInventory = new PetInventoryContainer(this);
    }

    //this
    @Override public Player getPlayer() {
        return player;
    }
    @Override public PlayerData getPlayerData() {
        return this;
    }
    @Override public PlayerDataContainer getPlayerDataContainer() {
        return this;
    }

    //Container
    @Override public BaseEntityContainer getBaseEntityContainer() {
        return playerEntity;
    }
    @Override public PlayerEntityContainer getPlayerEntityContainer() {
        return playerEntity;
    }
    @Override public PlayerSettingContainer getPlayerSettingContainer() {
        return playerSetting;
    }
    @Override public PlayerStatisticsContainer getPlayerStatisticsContainer() {
        return playerStatistics;
    }
    @Override public PlayerBankContainer getPlayerBankContainer() {
        return playerBank;
    }
    @Override public PlayerMenuContainer getPlayerMenuContainer() {
        return playerMenu;
    }
    @Override public PlayerOtherContainer getPlayerOtherContainer() {
        return playerOther;
    }

    //Interface
    @Override public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }
    @Override public PlayerSetting getPlayerSetting() {
        return playerSetting;
    }
    @Override public PlayerStatistics getPlayerStatistics() {
        return playerStatistics;
    }
    @Override public PlayerBank getPlayerBank() {
        return playerBank;
    }
    @Override public PlayerOther getPlayerOther() {
        return playerOther;
    }

    //Other
    @Override public PlayerCharacon getPlayerCharacon() {
        return playerCharacon;
    }
    @Override public PlayerDisplay getPlayerDisplay() {
        return playerDisplay;
    }
    @Override public PlayerInput getPlayerInput() {
        return playerInput;
    }

    //Inventory
    @Override public ItemInventory getItemInventory() {
        return itemInventory;
    }
    @Override public RuneInventory getRuneInventory() {
        return runeInventory;
    }
    @Override public PetInventory getPetInventory() {
        return petInventory;
    }

    //Map
    @Override public void setMapData(MapData mapData) {
        this.mapData = mapData;
    }
    @Override public MapDataInterface getMapData() {
        return mapData;
    }

    @Override
    public void save() {
        try {
            playerFile = new File(DataBasePath, "PlayerData/" + player.getUniqueId() + ".yml");
            if (!playerFile.exists()) playerFile.createNewFile();
            FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);

            //基本データ
            data.set("Mel", getMel());
            data.set("Level", getLevel());
            data.set("Exp", getExp());
            data.set("Health", getHealth());
            data.set("Mana", getMana());

            //各インベントリ
            data.set("ItemInventoryContainer", getItemInventory().getContentsToString());
            data.set("RuneInventoryContainer", getRuneInventory().getContentsToString());
            data.set("PetInventoryContainer", getPetInventory().getContentsToString());

            //その他
            getPlayerSetting().saveSetting(data);

            data.save(playerFile);
            sendMessage("§eプレイヤーデータ§aの§b保存§aが§b完了§aしました", SomSound.Tick);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eプレイヤーデータ§aの§b保存§aに§c失敗§aしました", SomSound.Nope);}
    }

    @Override
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

            //基本データ
            setMel(data.getInt("Mel", 10000));
            setLevel(data.getInt("Level", 1));
            setExp(data.getInt("Exp", 0));
            getPlayerEntity().setHealthUnsafe(data.getDouble("Health", Double.MAX_VALUE));
            getPlayerEntity().setManaUnsafe(data.getDouble("Mana", Double.MAX_VALUE));

            //各インベントリ
            getItemInventory().fromContentsFromString(data.getStringList("ItemInventoryContainer"));
            getRuneInventory().fromContentsFromString(data.getStringList("RuneInventoryContainer"));
            getPetInventory().fromContentsFromString(data.getStringList("PetInventoryContainer"));

            //その他
            getPlayerSetting().loadSetting(data);

            getPlayerEntity().statusUpdate();
            viewUpdate();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aが§b完了§aしました", SomSound.Tick);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aに§c失敗§aしました", SomSound.Nope);
        }
    }
}
