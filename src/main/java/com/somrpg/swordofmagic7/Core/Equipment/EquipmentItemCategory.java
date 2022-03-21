package com.somrpg.swordofmagic7.Core.Equipment;

import com.somrpg.swordofmagic7.Core.Equipment.SomEquipmentSlot;
import org.bukkit.Material;

public enum EquipmentItemCategory {
    Blade("刃剣", "ブレード", Material.STONE_SWORD, SomEquipmentSlot.MainHand),
    Mace("鈍器", "メイス", Material.STONE_SHOVEL, SomEquipmentSlot.MainHand),
    Rod("法杖", "ロッド", Material.STONE_HOE, SomEquipmentSlot.MainHand),
    ActGun("法銃", "アクトガン", Material.GOLDEN_HOE, SomEquipmentSlot.MainHand),
    Shield("盾", "シールド", Material.IRON_HORSE_ARMOR, SomEquipmentSlot.OffHand),
    Baton("指揮杖", "バトン", Material.BLAZE_ROD, SomEquipmentSlot.MainHand),
    Armor("鎧", "アーマー", Material.IRON_CHESTPLATE, SomEquipmentSlot.Armor),
    Trinket("武器装飾", "トリンケット", Material.MUSIC_DISC_13, SomEquipmentSlot.OffHand),
    ;

    private final String display;
    private final String display2;
    private final SomEquipmentSlot equipmentSlot;
    private final Material material;

    EquipmentItemCategory(String display, String display2, Material material, SomEquipmentSlot equipmentSlot) {
        this.display = display;
        this.display2 = display2;
        this.equipmentSlot = equipmentSlot;
        this.material = material;
    }

    public String getDisplay() {
        return display;
    }

    public String getDisplay2() {
        return display2;
    }

    public Material getMaterial() {
        return material;
    }

    public SomEquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
}
