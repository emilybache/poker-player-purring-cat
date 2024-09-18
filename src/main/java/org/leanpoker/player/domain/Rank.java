package org.leanpoker.player.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Rank {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A");

    private final int value;
    private final String jsonName;

    Rank(int value, String jsonName) {
        this.value = value;
        this.jsonName = jsonName;
    }

    @JsonCreator
    public static Rank fromString(String name) {
        for (Rank rank : Rank.values()) {
            if (rank.jsonName.equalsIgnoreCase(name)) {
                return rank;
            }
        }
        throw new RuntimeException("Invalid Rank enum " + name);
    }

    public int getValue() {
        return value;
    }
}
