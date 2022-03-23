package com.somrpg.swordofmagic7.Core.Inventory;

import com.somrpg.swordofmagic7.Core.Item.BaseItem;
import com.somrpg.swordofmagic7.Core.Pet.PetItem;

public interface PetInventory extends SomInventory {
    void addContent(PetItem petItem);
}
