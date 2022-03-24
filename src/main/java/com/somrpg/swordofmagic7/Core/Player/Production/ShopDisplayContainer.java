package com.somrpg.swordofmagic7.Core.Player.Production;

import com.somrpg.swordofmagic7.Core.Player.Interface.PlayerData;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomCraftDisplayContainer;
import com.somrpg.swordofmagic7.Core.Player.Production.Base.SomCraftItem;

public class ShopDisplayContainer extends SomCraftDisplayContainer implements ShopDisplay {

    public ShopDisplayContainer(PlayerData playerData) {
        super(playerData);
    }

    public CheckReturn isBuyAble(ShopItemContainer shopItem, CheckReturn checkReturn) {
        int finalPrice = shopItem.getPrice()*getAmount();
        if (getPlayerData().getMel() >= finalPrice) {
            checkReturn.getRequirements().add("§7・§e" + finalPrice + "メル §b✔");
        } else {
            checkReturn.getRequirements().add("§7・§e" + finalPrice + "メル §c✖");
            checkReturn.setOK(false);
        }
        return checkReturn;
    }

    public void buy(ShopItemContainer shopItem, CheckReturn checkReturn) {
        if (checkReturn.isOK()) {
            getPlayerData().removeMel(shopItem.getPrice()*getAmount());
            craft(shopItem, checkReturn);
        }
    }

    @Override
    public void onClickProduct(SomCraftItem craftItem) {
        CheckReturn checkReturn = new CheckReturn();
        if (craftItem instanceof ShopItemContainer shopItem) {
            if (isCraftAble(craftItem, checkReturn).isOK()) {
                if (isBuyAble(shopItem, checkReturn).isOK()) {
                    buy(shopItem, checkReturn);
                }
            }
        }
    }

    @Override
    public String getChar() {
        return "購入";
    }
}
