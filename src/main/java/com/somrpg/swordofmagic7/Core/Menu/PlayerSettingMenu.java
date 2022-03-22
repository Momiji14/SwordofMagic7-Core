package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Command.PlayerDataCommandExecutor;
import com.somrpg.swordofmagic7.Core.Generic.BaseGraphicalUser;
import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Player.Enum.DamageLog;
import com.somrpg.swordofmagic7.Core.Player.Enum.DropLog;
import com.somrpg.swordofmagic7.Core.Player.Enum.ExpLog;
import com.somrpg.swordofmagic7.Core.Player.PlayerData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PlayerSettingMenu implements BaseGraphicalUser, PlayerDataCommandExecutor, PlayerData {
    ItemStack DamageLogIcon = ViewableItemStack.create("ダメージログ", Material.RED_DYE, "ダメージログ表示を切り替えます").viewItemStack();
    ItemStack ExpLogIcon = ViewableItemStack.create("経験値ログ", Material.EXPERIENCE_BOTTLE, "経験値ログ表示を切り替えます").viewItemStack();
    ItemStack DropLogIcon = ViewableItemStack.create("ドロップログ", Material.CHEST, "ドロップログ表示を切り替えます").viewItemStack();
    ItemStack PvPModeIcon = ViewableItemStack.create("PvP設定", Material.IRON_SWORD, "PvP設定を切り替えます").viewItemStack();
    ItemStack CastModeIcon = ViewableItemStack.create("キャストモード", Material.END_CRYSTAL, "スキルの発動方法を切り替えます").viewItemStack();
    ItemStack StrafeModeIcon = ViewableItemStack.create("ストレイフモード", Material.FEATHER, "ストレイフの発動条件を切り替えます").viewItemStack();
    ItemStack ViewDigitIcon = ViewableItemStack.create("表示桁数", Material.FEATHER, "ステータスなどの数値の表示桁数を変更します").viewItemStack();

    private final PlayerData playerData;

    public PlayerSettingMenu(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    public PlayerData getPlayerData() {
        return playerData;
    }

    @Override
    public String getGUIDisplay() {
        return "§l設定メニュー";
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public void command(String[] args) {
        openGUI();
    }

    @Override
    public ItemStack[] getContent() {
        ItemStack[] content = new ItemStack[getSize()*9];
        content[0] = DamageLogIcon;
        content[1] = ExpLogIcon;
        content[2] = DropLogIcon;
        content[3] = PvPModeIcon;
        content[4] = CastModeIcon;
        content[5] = StrafeModeIcon;
        content[6] = ViewDigitIcon;
        return content;
    }

    public void onClick(ItemStack currentItem) {
        if (currentItem.equals(DamageLogIcon)) {
            damageLog();
        } else if (currentItem.equals(ExpLogIcon)) {
            expLog();
        } else if (currentItem.equals(DropLogIcon)) {
            dropLog();
        } else if (currentItem.equals(ViewDigitIcon)) {
            viewDigit();
        }
    }

    public void damageLog() {
        switch (getPlayerSetting().getDamageLog()) {
            case Disable -> getPlayerSetting().setDamageLog(DamageLog.DamageOnly);
            case DamageOnly -> getPlayerSetting().setDamageLog(DamageLog.Detail);
            case Detail -> getPlayerSetting().setDamageLog(DamageLog.Debug);
            case Debug -> getPlayerSetting().setDamageLog(DamageLog.Disable);
        }
    }

    public void expLog() {
        switch (getPlayerSetting().getExpLog()) {
            case Disable -> getPlayerSetting().setExpLog(ExpLog.Class);
            case Class -> getPlayerSetting().setExpLog(ExpLog.Player);
            case Player -> getPlayerSetting().setExpLog(ExpLog.Both);
            case Both -> getPlayerSetting().setExpLog(ExpLog.Disable);
        }
    }

    public void dropLog() {
        switch (getPlayerSetting().getDropLog()) {
            case Disable -> getPlayerSetting().setDropLog(DropLog.Item);
            case Item -> getPlayerSetting().setDropLog(DropLog.Rune);
            case Rune -> getPlayerSetting().setDropLog(DropLog.Both);
            case Both -> getPlayerSetting().setDropLog(DropLog.Disable);
        }
    }

    public void viewDigit() {
        int digit = getPlayerSetting().getViewDigit();
        if (digit < 10) {
            getPlayerSetting().setViewDigit(digit+1);
        } else {
            getPlayerSetting().setViewDigit(0);
        }
    }
}
