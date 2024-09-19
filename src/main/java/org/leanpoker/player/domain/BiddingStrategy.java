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

    public int decideBid(Card highest, int highestCardScore, int pairScore, List<Card> cards) {
        int highestScore = bidForHighestCard(highest);

        int score = Math.max(pairScore, highestScore);
        int bonus = scorer.suitedBonus(cards);
        return score + bonus;
    }

    public int bidForHighestCard(Card highestCard) {
        if (highestCard.rank().getValue() >= 10) {
            return Math.max(buyIn, 10);
        }
        return 0;
    }
}
