package com.example.microservice.persistence.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases for {@link ExecutionType}
 */
class ExecutionTypeTest {

    /**
     * Scenario:
     * Executes enum values test
     * Expectation:
     * Enum values should be retrieved
     */
    @ParameterizedTest(name = "value: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String value, final ExecutionType expected) {
        Assertions.assertEquals(expected, ExecutionType.valueOf(value));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(ExecutionType.INSERT.name(), ExecutionType.INSERT),
                arguments(ExecutionType.UPDATE.name(), ExecutionType.UPDATE),
                arguments(ExecutionType.DELETE.name(), ExecutionType.DELETE)
        );
    }
}
