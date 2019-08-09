package com.springboot.bet.enumeration;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Enumeration for Bet Type
 */
@Getter
public enum BetType {
    WIN(1, "WIN"),
    PLACE(2, "PLACE"),
    TRIFECTA(3, "TRIFECTA"),
    DOUBLE(4, "DOUBLE"),
    QUADDIE(5, "QUADDIE");

    private String code;

    private int id;

    BetType(int id, String betTypeCode) {
        this.id = id;
        this.code = betTypeCode;
    }

    public static BetType getTypeById(int id) {
        for (BetType type : BetType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public static boolean getIsValidTypes(String code) {
        for (BetType type : BetType.values()) {
            if (type.getCode().equals(code))
                return true;
        }
        return false;
    }
}
