package com.somrpg.swordofmagic7.Core.Production;

import java.util.ArrayList;
import java.util.List;

public class CheckReturn {
    private final List<String> requirements = new ArrayList<>();
    private boolean isOK = true;

    public List<String> getRequirements() {
        return requirements;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public boolean isOK() {
        return isOK;
    }
}
