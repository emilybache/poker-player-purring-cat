package org.leanpoker.player.domain;

import org.leanpoker.player.domain.model.Card;

public class BiddingStrategy {
    public int bidForHighestCard(Card highestCard) {
        if (highestCard.rank().getValue() >=10) {
            return 10;
        }
        return 0;
    }
}
