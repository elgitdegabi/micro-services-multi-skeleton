package com.example.microservice.persistence.dto;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

/**
 * Unit test class for {@link OrderMessage}
 */
class OrderMessageTest {

    /**
     * Scenario:
     * Executes getter & setter methods
     * Expectation:
     * Getter and setter methods should be executed
     */
    @Test
    void whenTestGetterAndSetterMethods() {
        assertPojoMethodsFor(OrderMessage.class)
                .testing(Method.SETTER, Method.GETTER, Method.TO_STRING)
                .areWellImplemented();
    }
}
