package org.leanpoker.player.domain;

import org.leanpoker.player.domain.model.Card;
import org.leanpoker.player.domain.model.Rank;

import java.util.ArrayList;
import java.util.HashMap;
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
}
