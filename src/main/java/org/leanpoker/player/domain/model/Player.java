package org.leanpoker.player.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.leanpoker.player.domain.BiddingStrategy;
import org.leanpoker.player.domain.CardScorer;
import org.leanpoker.player.presentation.parser.GameState;

public class Player {

    public static final String VERSION = "v.1.1";

    public static int betRequest(GameState state) {
        var cards = state.getCards();
        var scorer = new CardScorer();
        var highest = scorer.highest(cards);

        BiddingStrategy strategy = new BiddingStrategy(state.getCurrentBuyIn());
        return strategy.bidForHighestCard(highest);
    }

    public static void showdown(JsonNode game) {
    }
}
