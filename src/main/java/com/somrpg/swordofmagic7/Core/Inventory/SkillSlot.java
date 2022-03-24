package com.somrpg.swordofmagic7.Core.Inventory;

import java.util.List;

public interface SkillSlot {

    SkillHolder getSkillSlot(int slot);

    void viewTop();

    void viewBottom();

    List<String> toDataString();
    void fromDataString(List<String> data);
}
