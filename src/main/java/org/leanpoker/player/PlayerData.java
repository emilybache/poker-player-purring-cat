package org.leanpoker.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PlayerData {

    public int id;
    public String name;
    public String status;
    public String version;
    public int stack;
    public int bet;
    @JsonProperty("hole_cards")
    public List<CardData> cards;
}
