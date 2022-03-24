package com.somrpg.swordofmagic7.Core.Entity.Pet;

import com.somrpg.swordofmagic7.Core.Generic.ItemStack.SomItemStack;
import org.bukkit.Material;

import java.util.List;

public class PetItem extends SomItemStack {

    private final PetEntityContainer petEntity;

    public PetItem(String Id, String display, Material material, List<String> lore) {
        super(Id, display, material, lore);
        petEntity = new PetEntityContainer(this);
    }

    public PetEntityContainer getPetEntity() {
        return petEntity;
    }
}
