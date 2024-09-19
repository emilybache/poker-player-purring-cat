package org.leanpoker.player;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.leanpoker.player.domain.CardScorer;
import org.leanpoker.player.domain.model.Card;
import org.leanpoker.player.domain.model.Rank;
import org.leanpoker.player.domain.model.Suit;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerScorerTest {

    /**
     *
     * A = 10 points.
     * K = 8 points.
     * Q = 7 points.
     * J = 6 points.
     * 10 to 2 = 1/2 of card value. (e.g. a 6 would be worth 3 points)
     *
     **/
    @MethodSource("cardRanks")
    @ParameterizedTest
    void cardPoints(Card card, int expectedScore) {
        assertEquals(expectedScore, new CardScorer().scoreFor(card));
    }

    private static Stream<Arguments> cardRanks() {
        return Stream.of(
            Arguments.of(new Card(Rank.ACE, Suit.SPADES), 10),
            Arguments.of(new Card(Rank.KING, Suit.SPADES), 8),
            Arguments.of(new Card(Rank.TEN, Suit.SPADES), 5),
            Arguments.of(new Card(Rank.NINE, Suit.SPADES), 5)
        );
    }

    @Test
    public void highestCard() {
        var cards = List.of(new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.KING, Suit.SPADES));
        var highest = new CardScorer().highest(cards);
        assertEquals(new Card(Rank.KING, Suit.SPADES), highest);
    }

    @Test
    public void pair(){
        var cards = List.of(new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.SIX, Suit.SPADES));
        var pairScore = new CardScorer().pair(cards);

        assertEquals(6, pairScore);
    }

    @Test
    public void pairReturnsZeroWhenNoPair(){
        var cards = List.of(new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.SEVEN, Suit.SPADES));
        var pairScore = new CardScorer().pair(cards);

        assertEquals(0, pairScore);
    }
}
