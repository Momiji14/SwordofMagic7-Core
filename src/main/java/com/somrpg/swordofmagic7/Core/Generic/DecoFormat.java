package com.somrpg.swordofmagic7.Core.Generic;

public interface DecoFormat {
    static String ScaleDigit(double value, int digit) {
        return String.format("%." + digit + "f", value);
    }

    static String ScaleDigit(double value) {
        return ScaleDigit(value, 0);
    }
}
