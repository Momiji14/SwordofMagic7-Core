package com.somrpg.swordofmagic7.Core.Generic.Effect;

public enum EffectRank {
    Normal,
    High,
    Impossible,
    ;

    public boolean isNormal() {
        return this == Normal;
    }

    public boolean isHigh() {
        return this == High;
    }

    public boolean isImpossible() {
        return this == Impossible;
    }
}
