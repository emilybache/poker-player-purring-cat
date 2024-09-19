package org.leanpoker.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.leanpoker.player.domain.model.Card;
import org.leanpoker.player.domain.model.Rank;
import org.leanpoker.player.domain.model.Suit;
import org.leanpoker.player.presentation.parser.GameState;
import org.leanpoker.player.presentation.parser.MessageParser;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    public static final String STATE = """
        {
            "tournament_id":"550d1d68cd7bd10003000003",    \s
            "game_id":"550da1cb2d909006e90004b1",
            "round":0,                                     \s
            "bet_index":0,                                 \s
            "small_blind": 10,                             \s
            "current_buy_in": 320,                         \s
            "pot": 400,                                    \s
            "minimum_raise": 240,                          \s
            "dealer": 1,                                   \s
            "orbits": 7,                                   \s
            "in_action": 1,                                \s
            "players": [                                   \s
                {
                    "id": 0,                               \s
                    "name": "Albert",                      \s
                    "status": "active",                    \s
                    "version": "Default random player",    \s
                    "stack": 1010,                         \s
                    "bet": 320                             \s
                },
                {
                    "id": 1,                               \s
                    "name": "Bob",
                    "status": "active",
                    "version": "Default random player",
                    "stack": 1590,
                    "bet": 80,
                    "hole_cards": [                        \s
                        {
                            "rank": "6",                   \s
                            "suit": "hearts"               \s
                        },
                        {
                            "rank": "K",
                            "suit": "spades"
                        }
                    ]
                },
                {
                    "id": 2,
                    "name": "Chuck",
                    "status": "out",
                    "version": "Default random player",
                    "stack": 0,
                    "bet": 0
                }
            ],
            "community_cards": [                           \s
                {
                    "rank": "4",
                    "suit": "spades"
                },
                {
                    "rank": "A",
                    "suit": "hearts"
                },
                {
                    "rank": "6",
                    "suit": "clubs"
                }
            ]
        }
        """;

    @Test
    void parseState() {
        GameState state = new MessageParser().stateFrom(STATE);
        assertThat(state).isNotNull();
        assertThat(state.getTournamentId()).isEqualTo("550d1d68cd7bd10003000003");
    }

    @Test
    void getOurMoney() {
        GameState state = new MessageParser().stateFrom(STATE);
        assertThat(state.getOurMoney()).isEqualTo(1590);
    }


    @Test
    void testParseWhatAreMyCards() throws Exception{

        var result = new MessageParser().cardsFrom(STATE);

        assertEquals(List.of(new Card(Rank.SIX, Suit.HEARTS), new Card(Rank.KING, Suit.SPADES)), result);
    }

    @Test
    void RankFromString() {
        var result = Rank.fromString("6");
        assertEquals(Rank.SIX, result);
    }


    @ParameterizedTest
    @ValueSource(strings = {"6", "7", "K", "J"})
    void testRanksFromStringDoesNotBlowUp(String rank) {
        Rank.fromString(rank);
    }

    @MethodSource("exampleParams")
    @ParameterizedTest
    void exampleTest(long timeSent, long now, long expectedDelay) {
        System.out.println(timeSent + " " + now + " " + expectedDelay);
        assertThat(timeSent).isEqualTo(1);
    }

    private static Stream<Arguments> exampleParams() {
        return Stream.of(
            Arguments.of(1, 2, 3)
        );
    }

//    @Test
//    void testWithApprovalTests() {
//        Approvals.verify("Hello World");
//    }
//
//    @Test
//    void inline() {
//        var expected = """
//            1,
//            2,
//            3,
//            4
//            """;
//        Approvals.verify("1,\n2,\n3,\n4", new Options().inline(expected));
//    }
}
