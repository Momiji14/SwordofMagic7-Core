package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Inventory.*;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Menu.PlayerSettingMenu;
import com.somrpg.swordofmagic7.Core.Menu.PlayerUserMenu;
import com.somrpg.swordofmagic7.Core.Menu.TeleportGateMenu;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerOther;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
    private final PlayerDisplayContainer playerDisplayContainer;
    private final PlayerBank playerBank;
    private final PlayerSetting playerSetting;
    private final PlayerInput playerInput;
    private final PlayerStatistics playerStatistics;
    private final PlayerOther playerOther;

    private final ItemInventoryContainer itemInventoryContainer;
    private final RuneInventoryContainer runeInventoryContainer;
    private final PetInventoryContainer petInventoryContainer;

    private final PlayerUserMenu playerUserMenu;
    private final PlayerSettingMenu playerSettingMenu;
    private final TeleportGateMenu teleportGateMenu;

    private MapDataInterface mapData;
    private List<String> activeTeleportGate = new ArrayList<>();

    PlayerDataContainer(Player player) {
        this.player = player;
        playerEntity = new PlayerEntityContainer(this);
        playerCharacon = new PlayerCharacon(this);
        playerDisplayContainer = new PlayerDisplayContainer(this);
        playerBank = new PlayerBank(this);
        playerInput = new PlayerInput(this);
        playerSetting = new PlayerSetting(this);
        playerStatistics = new PlayerStatistics(this);
        playerOther = new PlayerOtherContainer(this);

        itemInventoryContainer = new ItemInventoryContainer(this);
        runeInventoryContainer = new RuneInventoryContainer(this);
        petInventoryContainer = new PetInventoryContainer(this);

        playerUserMenu = new PlayerUserMenu(this);
        playerSettingMenu = new PlayerSettingMenu(this);
        teleportGateMenu = new TeleportGateMenu(this);
    }

    @Override
    public PlayerData getPlayerData() {
        return this;
    }

    @Override
    public PlayerDataContainer getPlayerDataContainer() {
        return this;
    }

    @Override
    public BaseEntityContainer getBaseEntityContainer() {
        return playerEntity;
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
    public PlayerEntityContainer getPlayerEntity() {
        return playerEntity;
    }

    @Override
    public PlayerCharacon getPlayerCharacon() {
        return playerCharacon;
    }

    @Override
    public PlayerDisplayContainer getPlayerViewBar() {
        return playerDisplayContainer;
    }

    @Override
    public PlayerBank getPlayerBank() {
        return playerBank;
    }

    @Override
    public PlayerSetting getPlayerSetting() {
        return playerSetting;
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
    public List<String> getActiveTeleportGate() {
        return activeTeleportGate;
    }

    @Override
    public ItemInventoryContainer getItemInventory() {
        return itemInventoryContainer;
    }

    @Override
    public RuneInventoryContainer getRuneInventory() {
        return runeInventoryContainer;
    }

    @Override
    public PetInventoryContainer getPetInventory() {
        return petInventoryContainer;
    }

    @Override
    public PlayerOther getPlayerOther() {
        return null;
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

            activeTeleportGate = data.getStringList("ActiveTeleportGate");

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
