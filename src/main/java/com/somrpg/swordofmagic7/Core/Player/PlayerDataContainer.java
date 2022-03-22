package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Inventory.ItemInventory;
import com.somrpg.swordofmagic7.Core.Inventory.PetInventory;
import com.somrpg.swordofmagic7.Core.Inventory.RuneInventory;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Menu.PlayerSettingMenu;
import com.somrpg.swordofmagic7.Core.Menu.PlayerUserMenu;
import com.somrpg.swordofmagic7.Core.Menu.TeleportGateMenu;
import com.somrpg.swordofmagic7.Core.SomCore;
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
    private final TeleportGateMenu teleportGateMenu;

    private MapDataInterface mapData;
    private List<String> activeTeleportGate = new ArrayList<>();

    PlayerDataContainer(Player player) {
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
        SomCore.getJavaPlugin().getCommand("menu").setExecutor(playerUserMenu);
        playerSettingMenu = new PlayerSettingMenu(this);
        SomCore.getJavaPlugin().getCommand("settingMenu").setExecutor(playerSettingMenu);
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
    public Player getPlayer() {
        return player;
    }

    @Override
    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    @Override
    public PlayerCharacon getPlayerCharacon() {
        return playerCharacon;
    }

    @Override
    public PlayerViewBar getPlayerViewBar() {
        return playerViewBar;
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
    public List<String> getActiveTeleportGate() {
        return activeTeleportGate;
    }

    @Override
    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    @Override
    public RuneInventory getRuneInventory() {
        return runeInventory;
    }

    @Override
    public PetInventory getPetInventory() {
        return petInventory;
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

            data.set("Mel", getMel());
            data.set("Level", getLevel());
            data.set("Exp", getExp());
            data.set("Health", getHealth());
            data.set("Mana", getMana());

            data.set("ItemInventory", itemInventory.getContentsToString());
            data.set("RuneInventory", runeInventory.getContentsToString());
            data.set("PetInventory", petInventory.getContentsToString());

            data.set("ActiveTeleportGate", getActiveTeleportGate());

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

            playerBank.setMel(data.getInt("Mel", 10000));
            playerEntity.setLevel(data.getInt("Level", 1));
            playerEntity.setExp(data.getInt("Exp", 0));
            playerEntity.setHealthUnsafe(data.getDouble("Health", Double.MAX_VALUE));
            playerEntity.setManaUnsafe(data.getDouble("Mana", Double.MAX_VALUE));

            itemInventory.fromContentsFromString(data.getStringList("ItemInventory"));
            runeInventory.fromContentsFromString(data.getStringList("RuneInventory"));
            petInventory.fromContentsFromString(data.getStringList("PetInventory"));

            activeTeleportGate = data.getStringList("ActiveTeleportGate");

            getPlayerEntity().statusUpdate();
            viewUpdate();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aが§b完了§aしました", SomSound.Tick);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aに§c失敗§aしました", SomSound.Nope);
        }
    }
}
