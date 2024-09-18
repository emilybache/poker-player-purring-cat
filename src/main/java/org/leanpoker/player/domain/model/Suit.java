package org.leanpoker.player.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Suit {
    SPADES("spades"), CLUBS("clubs"), DIAMONDS("diamonds"), HEARTS("hearts");

    public String jsonName;

    Suit(String jsonName) {
        this.jsonName = jsonName;
    }


    @JsonCreator
    public static Suit fromString(String name) {
        for (Suit suit : Suit.values()) {
            if (suit.jsonName.equalsIgnoreCase(name)) {
                return suit;
            }
        }
        throw new RuntimeException("Invalid Suit enum " + name);
    }

}
