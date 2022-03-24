package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Generic.GenericConfig;
import com.somrpg.swordofmagic7.Core.Generic.Item.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Generic.Item.SomEquipmentSlot;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import com.somrpg.swordofmagic7.Core.Generic.Timer.SomTimer;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.*;
import com.somrpg.swordofmagic7.Core.Player.Inventory.*;
import com.somrpg.swordofmagic7.Core.Player.PlayerCharacon;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;
import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.SpawnLocation;

public class PlayerDataContainer implements PlayerData {
    public static void remove(Player player) {
        playerDataList.remove(player);
    }
    private static final HashMap<Player, PlayerData> playerDataList = new HashMap<>();
    public static PlayerData getData(Player player) {
        if (!playerDataList.containsKey(player)) {
            playerDataList.put(player, new PlayerDataContainer(player));
        }
        return playerDataList.get(player);
    }
    public static Collection<PlayerData> getDataList() {
        return playerDataList.values();
    }

    private final Player player;
    private final SomTimer somTimer = SomTimer.create();

    private File playerFile;
    private final PlayerEntityContainer playerEntity;
    private final PlayerSettingContainer playerSetting;
    private final PlayerStatisticsContainer playerStatistics;
    private final PlayerBankContainer playerBank;
    private final PlayerMenuContainer playerMenu;
    private final PlayerClassContainer playerClass;
    private final PlayerInputContainer playerInput;
    private final PlayerOtherContainer playerOther;

    private final PlayerCharacon playerCharacon;
    private final PlayerDisplay playerDisplay;

    private final ItemInventory itemInventory;
    private final RuneInventory runeInventory;
    private final PetInventory petInventory;
    private final SkillSlot skillSlot;


    private MapDataInterface mapData;

    PlayerDataContainer(Player player) {
        this.player = player;
        playerEntity = new PlayerEntityContainer(this);
        playerSetting = new PlayerSettingContainer(this);
        playerStatistics = new PlayerStatisticsContainer(this);
        playerMenu = new PlayerMenuContainer(this);
        playerBank = new PlayerBankContainer(this);
        playerInput = new PlayerInputContainer(this);
        playerClass = new PlayerClassContainer(this);

        playerOther = new PlayerOtherContainer(this);

        playerCharacon = new PlayerCharacon(this);
        playerDisplay = new PlayerDisplayContainer(this);

        itemInventory = new ItemInventoryContainer(this);
        runeInventory = new RuneInventoryContainer(this);
        petInventory = new PetInventoryContainer(this);
        skillSlot = new SkillSlotContainer(this);
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
    @Override public SomTimer getTimer() {
        return somTimer;
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
    @Override public PlayerClassContainer getPlayerClassContainer() {
        return playerClass;
    }
    @Override public PlayerMenuContainer getPlayerMenuContainer() {
        return playerMenu;
    }
    @Override public PlayerInputContainer getPlayerInputContainer() {
        return playerInput;
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
    @Override public PlayerClass getPlayerClass() {
        return playerClass;
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
    @Override public SkillSlot getSkillSlot() {
        return skillSlot;
    }

    //Map
    @Override public void setMapData(MapData mapData) {
        this.mapData = mapData;
    }
    @Override public MapDataInterface getMapData() {
        return mapData;
    }


    private static final String PathMel = "Mel";
    private static final String PathLevel = "Level";
    private static final String PathExp = "Exp";
    private static final String PathHealth = "Health";
    private static final String PathMana = "Mana";
    private static final String PathLocationX = "Location.x";
    private static final String PathLocationY = "Location.y";
    private static final String PathLocationZ = "Location.z";
    private static final String PathItemInventory = "Inventory.Item";
    private static final String PathRuneInventory = "Inventory.Rune";
    private static final String PathPetInventory = "Inventory.Pet";
    private static final String PathSkillSlot = "Inventory.SkillSlot";
    private static final String PathEquipment = "Inventory.";

    @Override
    public void save() {
        try {
            playerFile = new File(DataBasePath, "PlayerData/" + player.getUniqueId() + ".yml");
            if (!playerFile.exists()) playerFile.createNewFile();
            FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);

            //基本データ
            data.set(PathMel, getMel());
            data.set(PathLevel, getLevel());
            data.set(PathExp, getExp());
            data.set(PathHealth, getHealth());
            data.set(PathMana, getMana());

            //各インベントリ
            for (SomEquipmentSlot slot : SomEquipmentSlot.values()) {
                String path = PathEquipment + slot;
                if (getItemInventory().hasEquipment(slot)) {
                    data.set(path, getItemInventory().getEquipment(slot).toDataString());
                } else {
                    data.set(path, GenericConfig.NullString);
                }
            }
            data.set(PathItemInventory, getItemInventory().getContentsToString());
            data.set(PathRuneInventory, getRuneInventory().getContentsToString());
            data.set(PathPetInventory, getPetInventory().getContentsToString());
            data.set(PathSkillSlot, getSkillSlot().toDataString());

            //クラス
            getPlayerClass().saveClass(data);
            //設定
            getPlayerSetting().saveSetting(data);

            data.set(PathLocationX, getPlayer().getLocation().getX());
            data.set(PathLocationY, getPlayer().getLocation().getY());
            data.set(PathLocationZ, getPlayer().getLocation().getZ());

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
                getPlayerEntity().setMana(getPlayerEntity().getMaxMana());
                GenericConfig.spawnPlayer(player);
                return;
            }
            FileConfiguration data = YamlConfiguration.loadConfiguration(playerFile);

            //基本データ
            setMel(data.getInt(PathMel, 10000));
            setLevel(data.getInt(PathLevel, 1));
            setExp(data.getInt(PathExp, 0));
            getPlayerEntity().setHealthUnsafe(data.getDouble(PathHealth, Double.MAX_VALUE));
            getPlayerEntity().setManaUnsafe(data.getDouble(PathMana, Double.MAX_VALUE));

            //各インベントリ
            for (SomEquipmentSlot slot : SomEquipmentSlot.values()) {
                String path = PathEquipment + slot;
                String dataString = data.getString(path, GenericConfig.NullString);
                if (!dataString.equals(GenericConfig.NullString)) {
                    getItemInventory().setEquipment((EquipmentItem) SomItemStack.fromDataString(dataString));
                }
            }
            getItemInventory().fromContentsFromString(data.getStringList(PathItemInventory));
            getRuneInventory().fromContentsFromString(data.getStringList(PathRuneInventory));
            getPetInventory().fromContentsFromString(data.getStringList(PathPetInventory));
            getSkillSlot().fromDataString(data.getStringList(PathSkillSlot));

            //クラス
            getPlayerClass().loadClass(data);
            //設定
            getPlayerSetting().loadSetting(data);

            if (isPlayMode()) {
                double x = data.getDouble(PathLocationX, SpawnLocation.getX());
                double y = data.getDouble(PathLocationY, SpawnLocation.getY());
                double z = data.getDouble(PathLocationZ, SpawnLocation.getZ());
                getPlayer().teleportAsync(new Location(getPlayer().getWorld(), x, y, z, getPlayer().getLocation().getYaw(), getPlayer().getLocation().getPitch()));
            }
            getPlayerEntity().statusUpdate();
            viewUpdate();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aが§b完了§aしました", SomSound.Tick);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eプレイヤーデータ§aの§b読み込み§aに§c失敗§aしました", SomSound.Nope);
        }
    }
}
