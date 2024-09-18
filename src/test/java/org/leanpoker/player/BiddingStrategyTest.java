package org.leanpoker.player;

import org.junit.jupiter.api.Test;
import org.leanpoker.player.domain.BiddingStrategy;
import org.leanpoker.player.domain.model.Card;
import org.leanpoker.player.domain.model.Rank;
import org.leanpoker.player.domain.model.Suit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BiddingStrategyTest {

    @Test
    public void bidOnTenOrMore() {
        var hightestCard = new Card(Rank.ACE, Suit.SPADES);
        var bid = new BiddingStrategy(5).bidForHighestCard(hightestCard);
        var amount = 10;
        assertEquals(amount, bid);
    }

    @Test
    public void useBuyInIfPresent() {
        var hightestCard = new Card(Rank.ACE, Suit.SPADES);
        var bid = new BiddingStrategy(20).bidForHighestCard(hightestCard);
        assertEquals(20, bid);
    }
}
