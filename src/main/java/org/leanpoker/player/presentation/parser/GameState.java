package org.leanpoker.player.presentation.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.leanpoker.player.domain.model.Card;
import org.leanpoker.player.domain.model.Rank;
import org.leanpoker.player.domain.model.Suit;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    @JsonProperty("big_blind")
    private int big_blind;

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
    private List<PlayerData> players;

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

    public List<PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerData> players) {
        this.players = players;
    }

    public List<Card> getCommunityCards() {
        return community_cards;
    }

    public void setCommunityCards(List<Card> community_cards) {
        this.community_cards = community_cards;
    }

    public List<Card> getCards() {
        var index = getInAction();
        var player = getPlayers().get(index);
        var cards = player.getCards();
        return cards.stream().map(cardData -> new Card(Rank.fromString(cardData.rank), Suit.fromString(cardData.suit))).toList();
    }

    public int getOurMoney() {
        var index = getInAction();
        var player = getPlayers().get(index);
        return player.getStack();
    }
}
