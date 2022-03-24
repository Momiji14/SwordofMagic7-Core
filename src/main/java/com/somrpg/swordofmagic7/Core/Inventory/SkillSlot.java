package com.somrpg.swordofmagic7.Core.Inventory;

import java.util.List;

public interface SkillSlot {

    void SkillSlotUse(int slot);

    SkillHolder getSkillSlot(int slot);

    void viewTop();

    void viewBottom();

    void ScrollUp();

    void ScrollDown();

    List<String> toDataString();
    void fromDataString(List<String> data);
}
