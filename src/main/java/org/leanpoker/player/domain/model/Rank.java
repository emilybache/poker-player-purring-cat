package org.leanpoker.player.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Rank {
    TWO(1, "2"),
    THREE(2, "3"),
    FOUR(2, "4"),
    FIVE(3, "5"),
    SIX(3, "6"),
    SEVEN(4, "7"),
    EIGHT(4, "8"),
    NINE(5, "9"),
    TEN(5, "10"),
    JACK(6, "J"),
    QUEEN(7, "Q"),
    KING(8, "K"),
    ACE(10, "A");

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
