package org.leanpoker.player;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.rmi.server.ExportException;
import java.util.List;

public class MessageParser {
    public List<Card> cardsFrom(String json) throws Exception {
        State result = stateFrom(json);
        var index = result.in_action;
        var player = result.players.get(index);
        var cards = player.cards;
        return cards.stream().map(cardData -> new Card(Rank.fromString(cardData.rank), Suit.HEARTS)).toList();


    }

    public State stateFrom(String stateString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(stateString, State.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
