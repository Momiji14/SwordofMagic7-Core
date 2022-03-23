package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntity;
import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Inventory.*;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Menu.PlayerSettingMenu;
import com.somrpg.swordofmagic7.Core.Menu.PlayerUserMenu;
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
    private final PlayerCharacon playerCharacon;
    private final PlayerDisplay playerDisplayContainer;
    private final PlayerBank playerBank;
    private final PlayerSetting playerSettingContainer;
    private final PlayerInput playerInput;
    private final PlayerStatistics playerStatistics;
    private final PlayerOtherContainer playerOther;

    private final ItemInventory itemInventoryContainer;
    private final RuneInventory runeInventoryContainer;
    private final PetInventory petInventoryContainer;

    private final PlayerUserMenu playerUserMenu;
    private final PlayerSettingMenu playerSettingMenu;
    private final TeleportGateMenu teleportGateMenu;

    private MapDataInterface mapData;

    PlayerDataContainer(Player player) {
        this.player = player;
        playerEntity = new PlayerEntityContainer(this);
        playerCharacon = new PlayerCharacon(this);
        playerDisplayContainer = new PlayerDisplayContainer(this);
        playerBank = new PlayerBankContainer(this);
        playerInput = new PlayerInputContainer(this);
        playerSettingContainer = new PlayerSettingContainer(this);
        playerStatistics = new PlayerStatisticsContainer(this);
        playerOther = new PlayerOtherContainer(this);

        itemInventoryContainer = new ItemInventoryContainer(this);
        runeInventoryContainer = new RuneInventoryContainer(this);
        petInventoryContainer = new PetInventoryContainer(this);

        playerUserMenu = new PlayerUserMenu(this);
        playerSettingMenu = new PlayerSettingMenu(this);
        teleportGateMenu = new TeleportGateMenu(this);
    }

    @Override
    public PlayerOtherContainer getPlayerOtherContainer() {
        return playerOther;
    }

    @Override
    public PlayerData getPlayerData() {
        return this;
    }

    @Override
    public Set<String> getActiveTeleportGate() {
        return null;
    }

    @Override
    public PlayerDataContainer getPlayerDataContainer() {
        return this;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public SomInventory getBaseViewInventory() {
        return getBaseInventory(getViewInventory());
    }

    @Override
    public SomInventoryType getViewInventory() {
        return getPlayerSetting().getViewInventory();
    }

    @Override
    public void save(FileConfiguration data) {

    }

    @Override
    public void load(FileConfiguration data) {

    }

    @Override
    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    @Override
    public BaseEntityContainer getBaseEntityContainer() {
        return playerEntity;
    }

    @Override
    public PlayerEntityContainer getPlayerEntityContainer() {
        return playerEntity;
    }

    @Override
    public PlayerCharacon getPlayerCharacon() {
        return playerCharacon;
    }

    @Override
    public PlayerDisplay getPlayerViewBar() {
        return playerDisplayContainer;
    }

    @Override
    public PlayerBank getPlayerBank() {
        return playerBank;
    }

    @Override
    public PlayerSetting getPlayerSetting() {
        return playerSettingContainer;
    }

    @Override
    public PlayerInput getPlayerInput() {
        return playerInput;
    }

    @Override
    public PlayerStatistics getPlayerStatistics() {
        return playerStatistics;
    }

    @Override
    public PlayerUserMenu getUserMenu() {
        return playerUserMenu;
    }

    @Override
    public PlayerSettingMenu getSettingMenu() {
        return playerSettingMenu;
    }

    @Override
    public ItemInventory getItemInventory() {
        return itemInventoryContainer;
    }

    @Override
    public RuneInventory getRuneInventory() {
        return runeInventoryContainer;
    }

    @Override
    public PetInventory getPetInventory() {
        return petInventoryContainer;
    }

    @Override
    public PlayerOther getPlayerOther() {
        return playerOther;
    }

    @Override
    public TeleportGateMenu getTeleportGateMenu() {
        return teleportGateMenu;
    }

    @Override
    public void setMapData(MapData mapData) {
        this.mapData = mapData;
    }

    @Override
    public MapDataInterface getMapData() {
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
            data.set("ActiveTeleportGate", getActiveTeleportGate());

            getPlayerSetting().save(data);

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

            setMel(data.getInt("Mel", 10000));
            setLevel(data.getInt("Level", 1));
            setExp(data.getInt("Exp", 0));
            getPlayerEntity().setHealthUnsafe(data.getDouble("Health", Double.MAX_VALUE));
            getPlayerEntity().setManaUnsafe(data.getDouble("Mana", Double.MAX_VALUE));

            getItemInventory().fromContentsFromString(data.getStringList("ItemInventoryContainer"));
            getRuneInventory().fromContentsFromString(data.getStringList("RuneInventoryContainer"));
            getPetInventory().fromContentsFromString(data.getStringList("PetInventoryContainer"));

            setActiveTeleportGate(new HashSet<>(data.getStringList("ActiveTeleportGate")));

            getPlayerSetting().load(data);

            getPlayerEntity().statusUpdate();
            viewUpdate();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aが§b完了§aしました", SomSound.Tick);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aに§c失敗§aしました", SomSound.Nope);
        }
    }
}
