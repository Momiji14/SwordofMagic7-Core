package com.somrpg.swordofmagic7.Core.Pet;

import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntity;

public class PetEntity extends BaseEntity implements PetEntityInterface {

    private final PetItem petItem;

    PetEntity(PetItem petItem) {
        this.petItem = petItem;
    }

    @Override
    public PetItem getPetItem() {
        return petItem;
    }
}
