package org.leanpoker.player.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.leanpoker.player.domain.model.Player;
import org.leanpoker.player.presentation.parser.GameState;
import org.leanpoker.player.presentation.parser.MessageParser;

import java.util.Map;

@Controller()
public class PlayerController {

    ObjectMapper mapper = new ObjectMapper();

    @Get(produces = MediaType.TEXT_PLAIN)
    public String doGet() {
        return "Java player is running";
    }

    @Post(produces = MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String doPost(@Body Map<String, String> body) throws JsonProcessingException {
        String action = body.get("action");
        String gameState = body.get("game_state");
        System.out.println("action: " + action);
        System.out.println("gameState: " + gameState);
        if ("bet_request".equals(action)) {
            GameState state = new MessageParser().stateFrom(gameState);
            return String.valueOf(Player.betRequest(state));
        }
        if ("showdown".equals(action)) {
            Player.showdown(mapper.readTree(gameState));
        }
        if ("version".equals(action)) {
            return Player.VERSION;
        }
        return "";
    }

}
