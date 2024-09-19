package org.leanpoker.player.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.leanpoker.player.domain.BiddingStrategy;
import org.leanpoker.player.domain.CardScorer;
import org.leanpoker.player.presentation.parser.GameState;

public class Player {

    public static final String VERSION = "v.1.6";

    public static int betRequest(GameState state) {
        var cards = state.getCards();
        var scorer = new CardScorer();

        int pairScore = scorer.pair(cards);
        var highest = scorer.highest(cards);
        int highestCardScore = scorer.highestScore(cards);

        BiddingStrategy strategy = new BiddingStrategy(scorer, state.getCurrentBuyIn());
        return strategy.decideBid(cards);
    }

    public static void showdown(JsonNode game) {
    }
}
