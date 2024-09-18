package org.leanpoker.player;

import com.fasterxml.jackson.databind.JsonNode;

public class Player {

    static final String VERSION = "v.1.1";

    public static int betRequest(GameState state) {
        var cards = state.getCards();
        return 0;
    }

    public static void showdown(JsonNode game) {
    }
}
