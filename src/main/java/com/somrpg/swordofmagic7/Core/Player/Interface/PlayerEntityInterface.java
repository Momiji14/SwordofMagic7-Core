package com.somrpg.swordofmagic7.Core.Player.Interface;

import com.somrpg.swordofmagic7.Core.Effect.EffectData;
import com.somrpg.swordofmagic7.Core.Generic.Entity.BaseEntityInterface;
import com.somrpg.swordofmagic7.Core.Player.PlayerEntity;

import java.util.Collection;

public interface PlayerEntityInterface extends BaseEntityInterface, PlayerStatusCalculation {

    default PlayerEntity getPlayerEntity() {
        return getPlayerData().getPlayerEntity();
    }

    @Override
    default void setHealth(double health) {
        getPlayerEntity().setHealth(health);
    }

    @Override
    default void addHealth(double health) {
        getPlayerEntity().addHealth(health);
    }

    @Override
    default double getHealth() {
        return getPlayerEntity().getHealth();
    }

    @Override
    default void setMana(double mana) {
        getPlayerEntity().setMana(mana);
    }

    @Override
    default void addMana(double mana) {
        getPlayerEntity().addMana(mana);
    }

    @Override
    default double getMana() {
        return getPlayerEntity().getMana();
    }

    @Override
    default Collection<EffectData> getEffectSet() {
        return getPlayerEntity().getEffectSet();
    }

    default void setLevel(int level) {
        getPlayerEntity().setLevel(level);
    }

    default int getLevel() {
        return getPlayerEntity().getLevel();
    }

    default void setExp(int exp) {
        getPlayerEntity().setExp(exp);
    }

    default int getExp() {
        return getPlayerEntity().getExp();
    }
}
