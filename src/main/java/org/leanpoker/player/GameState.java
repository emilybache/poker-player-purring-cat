package org.leanpoker.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GameState {
    @JsonProperty("tournament_id")
    private String tournament_id;

    @JsonProperty("game_id")
    private String game_id;

    @JsonProperty("round")
    private int round;

    @JsonProperty("bet_index")
    private int bet_index;

    @JsonProperty("small_blind")
    private int small_blind;

    @JsonProperty("current_buy_in")
    private int current_buy_in;

    @JsonProperty("pot")
    private int pot;

    @JsonProperty("minimum_raise")
    private int minimum_raise;

    @JsonProperty("dealer")
    private int dealer;

    @JsonProperty("orbits")
    private int orbits;

    @JsonProperty("in_action")
    private int in_action;

    @JsonProperty("players")
    private List<Player> players;

    @JsonProperty("community_cards")
    private List<Card> community_cards;

    // Getters and setters
    public String getTournamentId() {
        return tournament_id;
    }

    public void setTournamentId(String tournament_id) {
        this.tournament_id = tournament_id;
    }

    public String getGameId() {
        return game_id;
    }

    public void setGameId(String game_id) {
        this.game_id = game_id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getBetIndex() {
        return bet_index;
    }

    public void setBetIndex(int bet_index) {
        this.bet_index = bet_index;
    }

    public int getSmallBlind() {
        return small_blind;
    }

    public void setSmallBlind(int small_blind) {
        this.small_blind = small_blind;
    }

    public int getCurrentBuyIn() {
        return current_buy_in;
    }

    public void setCurrentBuyIn(int current_buy_in) {
        this.current_buy_in = current_buy_in;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public int getMinimumRaise() {
        return minimum_raise;
    }

    public void setMinimumRaise(int minimum_raise) {
        this.minimum_raise = minimum_raise;
    }

    public int getDealer() {
        return dealer;
    }

    public void setDealer(int dealer) {
        this.dealer = dealer;
    }

    public int getOrbits() {
        return orbits;
    }

    public void setOrbits(int orbits) {
        this.orbits = orbits;
    }

    public int getInAction() {
        return in_action;
    }

    public void setInAction(int in_action) {
        this.in_action = in_action;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getCommunityCards() {
        return community_cards;
    }

    public void setCommunityCards(List<Card> community_cards) {
        this.community_cards = community_cards;
    }
}
