package org.leanpoker.player.presentation.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardData {
    @JsonProperty
    public String rank;

    @JsonProperty
    public String suit;
}
