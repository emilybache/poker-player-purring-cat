package org.leanpoker.player.domain;

import org.leanpoker.player.domain.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardScorer {
    public int scoreFor(Card card) {
        return card.rank().getValue();
    }

    public Card highest(List<Card> cards) {
        var sorted = new ArrayList<>(cards);
        sorted.sort((c1, c2) -> c2.rank().getValue() - c1.rank().getValue());
        return sorted.get(0);
    }
}
