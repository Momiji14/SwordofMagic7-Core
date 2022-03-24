package com.somrpg.swordofmagic7.Core.Entity.Pet;

import com.somrpg.swordofmagic7.Core.Entity.BaseEntityContainer;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PetEntityContainer extends BaseEntityContainer implements PetEntityInterface {

    private final PetItem petItem;

    PetEntityContainer(@NonNull PetItem petItem) {
        this.petItem = petItem;
    }

    @Override
    public PetItem getPetItem() {
        return petItem;
    }
}
