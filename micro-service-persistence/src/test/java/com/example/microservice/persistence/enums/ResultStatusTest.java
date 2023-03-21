package com.example.microservice.persistence.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases for {@link ResultStatus}
 */
class ResultStatusTest {

    /**
     * Scenario:
     * Executes enum values test
     * Expectation:
     * Enum values should be retrieved
     */
    @ParameterizedTest(name = "value: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String value, final ResultStatus expected) {
        Assertions.assertEquals(expected, ResultStatus.valueOf(value));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(ResultStatus.SUCCESS.name(), ResultStatus.SUCCESS),
                arguments(ResultStatus.ERROR.name(), ResultStatus.ERROR),
                arguments(ResultStatus.UNDEFINED.name(), ResultStatus.UNDEFINED)
        );
    }
}
