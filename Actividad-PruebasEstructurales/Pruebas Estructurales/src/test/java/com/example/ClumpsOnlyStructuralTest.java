package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class ClumpsOnlyStructuralTest {
    @ParameterizedTest
    @MethodSource("generator")
    void testClumps(int[] nums, int expectedNoOfClumps) {
        assertThat(Clumps.countClumps(nums))
        .isEqualTo(expectedNoOfClumps);
    }
    static Stream<Arguments> generator() {
    return Stream.of(
    of(new int[]{}, 0), // vacío
    of(null, 0), // null
    of(new int[]{1,2,2,2,1}, 1), // 1 grupo
    of(new int[]{1}, 0), // 1 elemento
    // completa
    of(new int[]{2,2}, 1)
    );
    }
}

