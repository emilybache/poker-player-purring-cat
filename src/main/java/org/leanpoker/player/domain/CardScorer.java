package org.leanpoker.player.domain;

import org.leanpoker.player.domain.model.Card;
import org.leanpoker.player.domain.model.Rank;

import java.util.ArrayList;
import java.util.HashMap;
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

    public int pair(List<Card> cards) {
        var mutable = new ArrayList<>(cards);
        var frequencies =new HashMap<Rank, Integer>();
        for(var card:cards) {
            if (frequencies.containsKey(card.rank())) {
                int existing =frequencies.get(card.rank());
                frequencies.put(card.rank(), existing +1);
            }
            else {
                frequencies.put(card.rank(), 1);
            }
        }
        if (frequencies.containsValue((2))) {
            return cards.get(0).rank().getValue() + cards.get(1).rank().getValue();
        }

        return 0;
    }
}
