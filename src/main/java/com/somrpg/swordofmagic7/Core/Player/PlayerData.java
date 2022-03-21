package com.somrpg.swordofmagic7.Core.Player;

import com.somrpg.swordofmagic7.Core.Inventory.ItemInventory;
import com.somrpg.swordofmagic7.Core.Inventory.PetInventory;
import com.somrpg.swordofmagic7.Core.Inventory.RuneInventory;
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
import java.util.HashMap;
import java.util.UUID;

import static com.somrpg.swordofmagic7.Core.Generic.GenericConfig.DataBasePath;

public class PlayerData implements PlayerDataInterface, PlayerViewUpdate {

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
    private final FileConfiguration data;
    private final PlayerEntity playerEntity;
    private final PlayerCharacon playerCharacon;
    private final PlayerViewBar playerViewBar;
    private final PlayerBank playerBank;
    private final PlayerSetting playerSetting;
    private final PlayerInput playerInput;

    private final ItemInventory itemInventory;
    private final RuneInventory runeInventory;
    private final PetInventory petInventory;

    private final PlayerUserMenu playerUserMenu;
    private final PlayerSettingMenu playerSettingMenu;

    PlayerData(Player player) {
        this.player = player;
        playerEntity = new PlayerEntity(this);
        playerCharacon = new PlayerCharacon(this);
        playerViewBar = new PlayerViewBar(this);
        playerBank = new PlayerBank(this);
        playerInput = new PlayerInput(this);
        playerSetting = new PlayerSetting(this);

        itemInventory = new ItemInventory(this);
        runeInventory = new RuneInventory(this);
        petInventory = new PetInventory(this);

        playerUserMenu = new PlayerUserMenu(this);
        SomCore.getJavaPlugin().getCommand("menu").setExecutor(playerUserMenu);
        playerSettingMenu = new PlayerSettingMenu(this);
        SomCore.getJavaPlugin().getCommand("setting").setExecutor(playerSettingMenu);

        playerFile = new File(DataBasePath, "PlayerData/" + player.getUniqueId() + ".yml");
        data = YamlConfiguration.loadConfiguration(playerFile);
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

    public void save() {
        try {

            if (!playerFile.exists()) playerFile.createNewFile();

            data.set("Mel", playerBank.getMel());
            data.set("Level", playerEntity.getLevel());
            data.set("Exp", playerEntity.getExp());
            data.set("Health", playerEntity.getHealth());
            data.set("Mana", playerEntity.getMana());

            data.set("ItemInventory", itemInventory.getContentsToString());
            data.set("RuneInventory", runeInventory.getContentsToString());
            data.set("PetInventory", petInventory.getContentsToString());

            data.save(playerFile);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eセーブデータ§aの§b保存§aに§c失敗§aしました", SomSound.Nope);}
    }

    public void load() {
        try {
            playerBank.setMel(data.getInt("Mel", 10000));
            playerEntity.setLevel(data.getInt("Level", 1));
            playerEntity.setExp(data.getInt("Exp", 0));
            playerEntity.setHealth(data.getDouble("Health", Double.MAX_VALUE));
            playerEntity.setMana(data.getDouble("Mana", Double.MAX_VALUE));

            itemInventory.fromContentsFromString(data.getStringList("ItemInventory"));
            runeInventory.fromContentsFromString(data.getStringList("RuneInventory"));
            petInventory.fromContentsFromString(data.getStringList("PetInventory"));
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage("§eセーブデータ§aの§b読み込み§aに§c失敗§aしました", SomSound.Nope);
        }
    }
}
