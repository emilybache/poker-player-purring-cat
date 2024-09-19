package org.leanpoker.player.domain;

import org.leanpoker.player.domain.model.Card;
import org.leanpoker.player.domain.model.Rank;
import org.leanpoker.player.domain.model.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardScorer {
    public int scoreFor(Card card) {
        return card.rank().getValue();
    }

    public Card highest(List<Card> cards) {
        var sorted = new ArrayList<>(cards);
        sorted.sort((c1, c2) -> c2.rank().getValue() - c1.rank().getValue());
        return sorted.get(0);
    }

    public int highestScore(List<Card> cards) {
        var highest = highest(cards);
        return highest.rank().getValue() >= 10 ? highest.rank().getValue() : 0;
    }

    public int overallScore(List<Card> cards) {
        int resultScore = 0;

        var highest = highest(cards);
        int highestScore = highest.rank().getValue();
        var suitedBonus = suitedBonus(cards);
        // WARNING: ASSUMING TWO CARDS ONLY
        var gapMalus = gapMalus(cards.get(0), cards.get(1));
        int pairScore = pair(cards);
        if (havePair(pairScore)) {
            resultScore = highestScore * 2;
        } else {
            resultScore = highestScore;
        }
        return resultScore + suitedBonus + gapMalus;
    }

    private boolean havePair(int pairScore) {
        return pairScore > 0;
    }

    public int pair(List<Card> cards) {
        Map<Rank, Long> numberOfCardsByRank = cards.stream()
            .collect(Collectors.groupingBy(Card::rank, Collectors.counting()));

        Map<Rank, Integer> sumOfValueByRank = cards.stream()
            .collect(Collectors.groupingBy(Card::rank, Collectors.summingInt(card -> card.rank().getValue())));

        List<Card> cardsByRank = cards.stream().filter(c -> c.rank() == Rank.TWO).toList();

        int result = cards.stream()
            .filter(card -> numberOfCardsByRank.get(card.rank()) == 2) // exactly 2 times
            .mapToInt(card -> card.rank().getValue())
            .sum();

        if (result < 5 && result > 0) {
            return 5;
        }
        return result;
    }

    public boolean findPairFor(Card card, List<Card> communityCards) {
        Map<Rank, Long> numberOfCardsByRank = communityCards.stream()
            .collect(Collectors.groupingBy(Card::rank, Collectors.counting()));

        return numberOfCardsByRank.containsKey(card.rank());
    }

    public int countPairs(List<Card> ourCards, List<Card> communityCards) {
        int pairCount = 0;
        for (Card card : ourCards) {
            if (findPairFor(card, communityCards)) {
                pairCount++;
            }
        }
        return pairCount;
    }

    public int suitedBonus(List<Card> cards) {
        Map<Suit, Long> numberOfCardsBySuit = cards.stream()
                .collect(Collectors.groupingBy(Card::suit, Collectors.counting()));
        var result = cards.stream()
                .filter(card -> numberOfCardsBySuit.get(card.suit()) >= 2) // same suit
                .count();
        return result > 0 ? 2 : 0;
    }


    public int gapMalus(Card card1, Card card2) {
        int difference = Math.abs(card1.rank().getOrder() - card2.rank().getOrder());
        if (difference == 0) {
            return 0;
        }
        return -1* (difference -1);
    }
}
