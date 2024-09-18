package org.leanpoker.player.domain;

import org.leanpoker.player.domain.model.Card;

public class BiddingStrategy {
    private final int buyIn;

    public BiddingStrategy(int buyIn) {
        this.buyIn = buyIn;
    }

    public int bidForHighestCard(Card highestCard) {
        if (highestCard.rank().getValue() >= 10) {
            return Math.max(buyIn, 10);
        }
        return 0;
    }
}
