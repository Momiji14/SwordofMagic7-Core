package com.somrpg.swordofmagic7.Core.Player.Inventory;

import java.util.List;

public interface SkillSlot {

    void setSelectSlot(int slot);

    int getSelectSlot();

    void setSkillSlot(int slot, SkillHolder skillHolder);

    void SkillSlotUse(int slot);

    SkillHolder getSkillSlot(int slot);

    void viewTop();

    void viewBottom();

    void ScrollUp();

    void ScrollDown();

    List<String> toDataString();
    void fromDataString(List<String> data);
}
