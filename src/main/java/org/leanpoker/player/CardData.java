package org.leanpoker.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardData {
    @JsonProperty
    public String rank;

    @JsonProperty
    public String suit;
}
