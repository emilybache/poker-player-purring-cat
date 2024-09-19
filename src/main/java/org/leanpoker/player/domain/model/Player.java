package org.leanpoker.player.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.leanpoker.player.domain.BiddingStrategy;
import org.leanpoker.player.domain.CardScorer;
import org.leanpoker.player.domain.PostFlopBiddingStrategy;
import org.leanpoker.player.presentation.parser.GameState;

public class Player {

    public static final String VERSION = "v.2.0";

    public static int betRequest(GameState state) {
        var cards = state.getCards();
        var scorer = new CardScorer();

        var communityCards = state.getCommunityCards();
        if (communityCards.isEmpty()) {
            BiddingStrategy strategy = new BiddingStrategy(scorer, state.getCurrentBuyIn());
            return strategy.decideBid(cards, state.getOurMoney());
        } else {
            PostFlopBiddingStrategy flopStrategy = new PostFlopBiddingStrategy(scorer);
            return flopStrategy.decideBid(cards, communityCards, state.getOurMoney(), state.getCurrentBuyIn());
        }
    }

    public static void showdown(JsonNode game) {
    }
}
