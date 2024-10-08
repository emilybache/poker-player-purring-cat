package org.leanpoker.player.presentation.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.leanpoker.player.domain.model.Card;

import java.util.List;

public class MessageParser {
    public List<Card> cardsFrom(String json) throws Exception {
        var result = stateFrom(json);
        return result.getCards();
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
