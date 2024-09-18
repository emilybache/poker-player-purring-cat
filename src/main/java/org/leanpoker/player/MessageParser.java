package org.leanpoker.player;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.rmi.server.ExportException;
import java.util.List;

public class MessageParser {
    public List<Card> cardsFrom(String json) throws Exception {
        var result = stateFrom(json);
        var index = result.getInAction();
        var player = result.getPlayers().get(index);
        var cards = player.getCards();
        return cards.stream().map(cardData -> new Card(Rank.fromString(cardData.rank), Suit.fromString(cardData.suit))).toList();


    }

    public GameState stateFrom(String stateString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(stateString, GameState.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
