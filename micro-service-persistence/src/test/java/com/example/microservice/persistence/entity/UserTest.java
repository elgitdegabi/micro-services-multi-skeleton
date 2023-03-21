package com.example.microservice.persistence.entity;

import org.junit.jupiter.api.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

/**
 * Unit test cases for {@link User}
 */
class UserTest {

    /**
     * Scenario:
     * Executes getter & setter methods
     * Expectation:
     * Getter and setter methods should be executed
     */
    @Test
    void whenTestGetterAndSetterMethods() {
        assertPojoMethodsFor(User.class)
                .testing(Method.SETTER, Method.GETTER, Method.TO_STRING, Method.EQUALS, Method.HASH_CODE)
                .areWellImplemented();
    }
}
