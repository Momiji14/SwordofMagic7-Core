package com.somrpg.swordofmagic7.Core.Player.Container;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntityContainer;
import com.somrpg.swordofmagic7.Core.Generic.Timer.SomTimer;
import com.somrpg.swordofmagic7.Core.Player.Inventory.*;
import com.somrpg.swordofmagic7.Core.Map.MapData;
import com.somrpg.swordofmagic7.Core.Map.MapDataInterface;
import com.somrpg.swordofmagic7.Core.Player.Interface.*;
import com.somrpg.swordofmagic7.Core.Player.PlayerCharacon;
import com.somrpg.swordofmagic7.Core.Sound.SomSound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

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
    private static final String PathItemInventory = "Inventory.Item";
    private static final String PathRuneInventory = "Inventory.Rune";
    private static final String PathPetInventory = "Inventory.Pet";
    private static final String PathSkillSlot = "Inventory.SkillSlot";

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
            data.set(PathItemInventory, getItemInventory().getContentsToString());
            data.set(PathRuneInventory, getRuneInventory().getContentsToString());
            data.set(PathPetInventory, getPetInventory().getContentsToString());
            data.set(PathSkillSlot, getSkillSlot().toDataString());

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
            setMel(data.getInt(PathMel, 10000));
            setLevel(data.getInt(PathLevel, 1));
            setExp(data.getInt(PathExp, 0));
            getPlayerEntity().setHealthUnsafe(data.getDouble(PathHealth, Double.MAX_VALUE));
            getPlayerEntity().setManaUnsafe(data.getDouble(PathMana, Double.MAX_VALUE));

            //各インベントリ
            getItemInventory().fromContentsFromString(data.getStringList(PathItemInventory));
            getRuneInventory().fromContentsFromString(data.getStringList(PathRuneInventory));
            getPetInventory().fromContentsFromString(data.getStringList(PathPetInventory));
            getSkillSlot().fromDataString(data.getStringList(PathSkillSlot));

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
