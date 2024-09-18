package org.leanpoker.player;

import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"apple", "banana", "orange"})
    void testWithStringParameter(String fruit) {
        assertTrue(fruit.length() > 0);
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
