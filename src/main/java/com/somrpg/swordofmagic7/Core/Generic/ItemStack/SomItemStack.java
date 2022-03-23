package com.somrpg.swordofmagic7.Core.Generic.ItemStack;

import com.somrpg.swordofmagic7.Core.DataBase.DataBase;
import com.somrpg.swordofmagic7.Core.DataBase.SomItemDataLoader;
import com.somrpg.swordofmagic7.Core.Equipment.EquipmentItem;
import com.somrpg.swordofmagic7.Core.Item.RuneItem;
import com.somrpg.swordofmagic7.Core.SomCore;
import org.bukkit.Material;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;
import java.util.*;

public class SomItemStack extends ViewableItemStack {

    private final String Id;
    private String uuid = String.valueOf(UUID.randomUUID());
    private ItemOwner itemOwner = new ItemOwner();
    private File file;

    public SomItemStack(@NonNull String Id, @NonNull String display, @NonNull Material material, @NonNull List<String> lore) {
        super(display, material, lore);
        this.Id = Id;
    }

    public SomItemStack(@NonNull String display, @NonNull Material material, @NonNull String lore) {
        super(display, material, List.of(lore.split("\n")));
        this.Id = "ID未設定";
    }
    public SomItemStack(@NonNull String display, @NonNull Material material) {
        super(display, material, new ArrayList<>());
        this.Id = "ID未設定";
    }

    public String getId() {
        return Id;
    }

    public void setUUID(@NonNull String uuid) {
        this.uuid = uuid;
    }

    public String getUUID() {
        return uuid;
    }

    public void setItemOwner(@NonNull ItemOwner itemOwner) {
        this.itemOwner = itemOwner;
    }

    public ItemOwner getItemOwner() {
        return itemOwner;
    }

    public void setFile(@NonNull File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String toDataString() {
        StringBuilder data = new StringBuilder("Id:" + getId());
        if (this instanceof RuneItem item) {
            data.append(",Level:").append(item.getLevel()).append(",Quality:").append(item.getQuality());
        } else if (this instanceof EquipmentItem item) {
            data.append(",Plus:").append(item.getPlus());
            int i = 0;
            for (String uuid : item.getRune()) {
                data.append(",Rune").append(i).append(":").append(uuid);
                i++;
            }
        }
        data.append(",Owner:").append(getItemOwner().getOwner()).append(",UUID:").append(getUUID());
        return data.toString();
    }

    public static SomItemStack fromDataString(@NonNull String data) {
        try {
            String[] split = data.split(",");
            Map<String, String> mapData = new HashMap<>();
            for (String str : split) {
                String[] split2 = str.split(":");
                mapData.put(split2[0], split2[1]);
            }
            String id = mapData.get("Id");
            String owner = mapData.get("Owner");
            String uuid = mapData.get("UUID");
            SomItemStack item = SomItemDataLoader.getItem(id);
            item.setItemOwner(new ItemOwner(owner));
            item.setUUID(uuid);
            if (item instanceof RuneItem runeItem) {
                int level = Integer.parseInt(mapData.get("Level"));
                double quality = Double.parseDouble(mapData.get("Quality"));
                runeItem.setLevel(level);
                runeItem.setQuality(quality);
            } else if (item instanceof EquipmentItem equipmentItem) {
                int plus = Integer.parseInt(mapData.get("Plus"));
                equipmentItem.setPlus(plus);
                int i = 0;
                while (mapData.containsKey("Rune" + i)) {
                    String runeUUID = mapData.get("Rune" + i);
                    equipmentItem.getRune().add(runeUUID);
                    i++;
                }
            }
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            SomCore.log("SomItemStack fromDataStringError -> " + data);
            return DataBase.ErrorItemStack;
        }
    }
}
