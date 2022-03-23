package com.somrpg.swordofmagic7.Core.Menu;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.ViewableItemStack;
import com.somrpg.swordofmagic7.Core.Player.Enum.*;
import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface SettingMenu extends BaseMenu {
    ItemStack DamageLogIcon = ViewableItemStack.create("ダメージログ", Material.RED_DYE, "ダメージログ表示を切り替えます").viewItemStack();
    ItemStack ExpLogIcon = ViewableItemStack.create("経験値ログ", Material.EXPERIENCE_BOTTLE, "経験値ログ表示を切り替えます").viewItemStack();
    ItemStack DropLogIcon = ViewableItemStack.create("ドロップログ", Material.CHEST, "ドロップログ表示を切り替えます").viewItemStack();
    ItemStack PvPModeIcon = ViewableItemStack.create("PvP設定", Material.IRON_SWORD, "PvP設定を切り替えます").viewItemStack();
    ItemStack CastModeIcon = ViewableItemStack.create("キャストモード", Material.END_CRYSTAL, "スキルの発動方法を切り替えます").viewItemStack();
    ItemStack StrafeModeIcon = ViewableItemStack.create("ストレイフモード", Material.FEATHER, "ストレイフの発動条件を切り替えます").viewItemStack();
    ItemStack ViewDigitIcon = ViewableItemStack.create("表示桁数", Material.COMMAND_BLOCK, "ステータスなどの数値の表示桁数を変更します").viewItemStack();

    @Override
    default String getGUIDisplay() {
        return "§l設定メニュー";
    }

    @Override
    default int getSize() {
        return 1;
    }

    @Override
    default ItemStack[] getContent() {
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

    @Override
    default void onClick(Inventory clickedInv, ItemStack clickedItem, int slot) {
        if (clickedItem.equals(DamageLogIcon)) {
            damageLog();
        } else if (clickedItem.equals(ExpLogIcon)) {
            expLog();
        } else if (clickedItem.equals(DropLogIcon)) {
            dropLog();
        } else if (clickedItem.equals(PvPModeIcon)) {
            pvpMode();
        } else if (clickedItem.equals(CastModeIcon)) {
            castMode();
        } else if (clickedItem.equals(StrafeModeIcon)) {
            strafeMode();
        } else if (clickedItem.equals(ViewDigitIcon)) {
            viewDigit();
        }
    }

    default void damageLog() {
        switch (getPlayerData().getPlayerSetting().getDamageLog()) {
            case Disable -> getPlayerData().setDamageLog(DamageLog.DamageOnly);
            case DamageOnly -> getPlayerData().setDamageLog(DamageLog.Detail);
            case Detail -> getPlayerData().setDamageLog(DamageLog.Debug);
            case Debug -> getPlayerData().setDamageLog(DamageLog.Disable);
        }
    }

    default void expLog() {
        switch (getPlayerData().getExpLog()) {
            case Disable -> getPlayerData().setExpLog(ExpLog.Class);
            case Class -> getPlayerData().setExpLog(ExpLog.Player);
            case Player -> getPlayerData().setExpLog(ExpLog.Both);
            case Both -> getPlayerData().setExpLog(ExpLog.Disable);
        }
    }

    default void dropLog() {
        switch (getPlayerData().getDropLog()) {
            case Disable -> getPlayerData().setDropLog(DropLog.Item);
            case Item -> getPlayerData().setDropLog(DropLog.Rune);
            case Rune -> getPlayerData().setDropLog(DropLog.Both);
            case Both -> getPlayerData().setDropLog(DropLog.Disable);
        }
    }

    default void pvpMode() {
        switch (getPlayerData().getPlayerPvPMode()) {
            case Hostile -> getPlayerData().setPlayerPvPMode(PlayerPvPMode.Friendly);
            case Friendly -> getPlayerData().setPlayerPvPMode(PlayerPvPMode.Hostile);
        }
    }

    default void castMode() {
        switch (getPlayerData().getPlayerCastMode()) {
            case Hold -> getPlayerData().setPlayerCastMode(PlayerCastMode.Renewed);
            case Renewed -> getPlayerData().setPlayerCastMode(PlayerCastMode.Legacy);
            case Legacy -> getPlayerData().setPlayerCastMode(PlayerCastMode.Hold);
        }
    }

    default void strafeMode() {
        switch (getPlayerData().getPlayerStrafeMode()) {
            case DoubleJump -> getPlayerData().setPlayerStrafMode(PlayerStrafeMode.AirDash);
            case AirDash -> getPlayerData().setPlayerStrafMode(PlayerStrafeMode.Both);
            case Both -> getPlayerData().setPlayerStrafMode(PlayerStrafeMode.DoubleJump);
        }
    }

    default void viewDigit() {
        int digit = getPlayerData().getViewDigit();
        if (digit < 10) {
            getPlayerData().setViewDigit(digit+1);
        } else {
            getPlayerData().setViewDigit(0);
        }
    }
}
