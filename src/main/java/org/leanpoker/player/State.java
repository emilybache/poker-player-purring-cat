package org.leanpoker.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.leanpoker.player.PlayerData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    @JsonProperty("tournament_id")
    public String tournamentId;

    @JsonProperty("game_id")
    public String gameId;
    @JsonProperty("round")
    public int round;
    @JsonProperty("bet_index")
    public int betIndex;
    @JsonProperty("small_blind")
    public int smallBlind;
    @JsonProperty("current_buy_in")
    public int currentBuyIn;
    public int pot;
    @JsonProperty("minimumRaise")

    public int minimumRaise;
    public int dealer;
    public int orbits;
    @JsonProperty("inAction")

    public int inAction;
    @JsonProperty("players")
    public List<PlayerData> players;

    @JsonProperty("in_action")
    public Integer in_action;

}

