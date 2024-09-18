package org.leanpoker.player.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.leanpoker.player.presentation.parser.GameState;

public class Player {

    public static final String VERSION = "v.1.1";

    public static int betRequest(GameState state) {
        var cards = state.getCards();
        return 0;
    }

    public static void showdown(JsonNode game) {
    }
}
