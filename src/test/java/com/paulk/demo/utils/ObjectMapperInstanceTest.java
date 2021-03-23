package com.paulk.demo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test the {@link ObjectMapperInstance} utility class.
 */
public class ObjectMapperInstanceTest {

    /**
     * Validate the behavior of the {@link ObjectMapperInstance#isValidJson(String)} method.
     */
    @Test
    public void isValidJsonSuccess() {
        String validJSON = "{\"value\": \"John Lennonn\"}";
        Assertions.assertTrue(ObjectMapperInstance.INSTANCE.isValidJson(validJSON), "Validate the behaviour of the ObjectMapperInstance isValidJson method");
    }

    /**
     * Validate the behavior of the {@link ObjectMapperInstance#isValidJson(String)} method.
     */
    @Test
    public void isValidJsonInvalid() {
        String validJSON = "{ \"foo\" : \"bar\", \"foo\" : \"baz\" }";
        Assertions.assertFalse(ObjectMapperInstance.INSTANCE.isValidJson(validJSON), "Validate the behaviour of the ObjectMapperInstance isValidJson method");
    }
}
