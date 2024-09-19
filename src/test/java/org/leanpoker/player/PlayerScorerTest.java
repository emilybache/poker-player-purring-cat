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

import static org.assertj.core.api.Assertions.assertThat;
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
    public void highestCardScoreWithBadCards() {
        var cards = List.of(new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.KING, Suit.SPADES));
        var highestScore = new CardScorer().highestScore(cards);
        assertEquals(0, highestScore);
    }

    @Test
    public void highestCardScoreWithGoodCards() {
        var cards = List.of(new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.ACE, Suit.SPADES));
        var highestScore = new CardScorer().highestScore(cards);
        assertEquals(10, highestScore);
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

    @Test
    public void pairOfTwosIsFive(){
        var cards = List.of(new Card(Rank.TWO, Suit.HEARTS), new Card(Rank.TWO, Suit.SPADES));
        var pairScore = new CardScorer().pair(cards);

        assertEquals(5, pairScore);
    }

    @Test
    public void pairOfThreesIsSomething(){
        var cards = List.of(new Card(Rank.THREE, Suit.HEARTS), new Card(Rank.THREE, Suit.SPADES));
        var pairScore = new CardScorer().pair(cards);

        assertEquals(5, pairScore);
    }
    @Test
    public void suitedCardsAddTwo(){
        var cards = List.of(new Card(Rank.THREE, Suit.HEARTS), new Card(Rank.FOUR, Suit.HEARTS));
        var bonus = new CardScorer().suitedBonus(cards);

        assertEquals(2, bonus);
    }
    @Test
    public void suitedCardsNoBonus(){
        var cards = List.of(new Card(Rank.THREE, Suit.SPADES), new Card(Rank.FOUR, Suit.HEARTS));
        var bonus = new CardScorer().suitedBonus(cards);

        assertEquals(0, bonus);
    }

    @Test
    void suitedWithAceIsTwelve() {
        var cards = List.of(new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.FOUR, Suit.HEARTS));

        var score = new CardScorer().overallScore(cards);

        assertThat(score).isEqualTo(3);
    }


    @Test
    void suitedWithKingIsTwelve() {
        var cards = List.of(new Card(Rank.KING, Suit.HEARTS), new Card(Rank.QUEEN, Suit.HEARTS));

        var score = new CardScorer().overallScore(cards);

        assertThat(score).isEqualTo(10);
    }

    @Test
    void suitedWithQueenIsTwelve() {
        var cards = List.of(new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.JACK, Suit.HEARTS));

        var score = new CardScorer().overallScore(cards);

        assertThat(score).isEqualTo(9);
    }

    @Test
    void twoTens() {
        var cards = List.of(new Card(Rank.TEN, Suit.SPADES), new Card(Rank.TEN, Suit.HEARTS));

        var score = new CardScorer().overallScore(cards);

        assertThat(score).isEqualTo(10);
    }

    @Test
    void gapMalusForBigGap() {
        var cards = List.of(new Card(Rank.TWO, Suit.SPADES), new Card(Rank.TEN, Suit.HEARTS));

        var score = new CardScorer().gapMalus(cards.get(0), cards.get(1));

        assertThat(score).isEqualTo(-7);
    }
    @Test
    void gapMalusForAceAndFour() {
        var cards = List.of(new Card(Rank.ACE, Suit.HEARTS), new Card(Rank.FOUR, Suit.HEARTS));

        var score = new CardScorer().gapMalus(cards.get(0), cards.get(1));

        assertThat(score).isEqualTo(-9);
    }

}
