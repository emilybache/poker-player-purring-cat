package org.leanpoker.player.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Rank {
    TWO(1, "2", 2),
    THREE(2, "3", 3),
    FOUR(2, "4", 4),
    FIVE(3, "5", 5),
    SIX(3, "6", 6),
    SEVEN(4, "7", 7),
    EIGHT(4, "8", 8),
    NINE(5, "9", 9),
    TEN(5, "10", 10),
    JACK(6, "J", 11),
    QUEEN(7, "Q", 12),
    KING(8, "K", 13),
    ACE(10, "A", 14);

    private final int value;
    private final String jsonName;
    private final int order;

    Rank(int value, String jsonName, int order) {
        this.value = value;
        this.jsonName = jsonName;
        this.order = order;
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

    public int getOrder() {
        return order;
    }
}
