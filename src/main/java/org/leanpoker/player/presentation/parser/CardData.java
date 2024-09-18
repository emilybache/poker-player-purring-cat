package org.leanpoker.player.presentation.parser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardData {
    @JsonProperty
    public String rank;

    @JsonProperty
    public String suit;
}
