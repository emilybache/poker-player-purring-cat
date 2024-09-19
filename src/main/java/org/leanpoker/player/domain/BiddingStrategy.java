package org.leanpoker.player.domain;

import org.leanpoker.player.domain.model.Card;

import java.util.List;

public class BiddingStrategy {
    private final CardScorer scorer;
    private final int buyIn;

    public BiddingStrategy(CardScorer scorer, int buyIn) {
        this.scorer = scorer;
        this.buyIn = buyIn;
    }

    public int decideBid(List<Card> cards, int ourMoney) {
        int overallScore = scorer.overallScore(cards);
        if (overallScore > 12) {
            return ourMoney;
        } if (overallScore > 10) {
            return Math.max(buyIn, 10);
        } else {
            return 0;
        }
    }

    public int bidForHighestCard(Card highestCard) {
        if (highestCard.rank().getValue() >= 10) {
            return Math.max(buyIn, 10);
        }
        return 0;
    }
}
