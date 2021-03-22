package com.paulk.demo.domain.model;

import com.paulk.demo.constants.ErrorCodes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the {@link Error} domain model.
 */
public class ErrorTest {

    private Error error;
    private Error altError;
    private Error differentError;

    @BeforeEach
    public void setup() {
        error = new Error();
        error.setCode(ErrorCodes.ALREADY_EXISTS);
        error.setDescription(ErrorCodes.ALREADY_EXISTS_DESCRIPTION);

        altError = new Error();
        altError.setCode(ErrorCodes.ALREADY_EXISTS);
        altError.setDescription(ErrorCodes.ALREADY_EXISTS_DESCRIPTION);

        differentError = new Error();
        differentError.setCode(ErrorCodes.NOT_FOUND);
        differentError.setDescription(ErrorCodes.NOT_FOUND_DESCRIPTION);
    }

    /**
     * Validates the comparison of two {@link Error} objects.
     */
    @Test
    public void errorEqualsSuccess() {
        // Setup
        Assertions.assertEquals(error, error, "Assert Error equals successfully.");
    }

    /**
     * Validates the comparison of two {@link Error} objects.
     */
    @Test
    public void errorEqualsDifferentObjectsSuccess() {
        // Setup
        Assertions.assertEquals(error, altError, "Assert Error equals successfully.");
    }

    /**
     * Validates the comparison of two {@link Error} objects.
     */
    @Test
    public void errorEqualsInvalid() {
        Assertions.assertNotEquals(error, differentError, "Assert Error equals invalid.");
    }

    /**
     * Validates the comparison of two {@link Error} objects.
     */
    @Test
    public void errorEqualsNullSuccess() {
        Assertions.assertFalse(error.equals(null), "Assert Error equals null successfully.");
    }

    /**
     * Validates the comparison of two {@link Error} objects.
     */
    @Test
    public void errorCompareHashCodeSuccess() {
        Assertions.assertEquals(error.hashCode(), altError.hashCode(), "Assert Error equals successfully.");
    }

    /**
     * Validates the comparison of two {@link Error} objects.
     */
    @Test
    public void errorCompareHashCodeInvalid() {
        Assertions.assertNotEquals(error.hashCode(), differentError.hashCode(), "Assert Error equals invalid.");
    }

    /**
     * Validates the comparison of two {@link Error} objects.
     */
    @Test
    public void errorGetCodeSuccess() {
        Assertions.assertEquals("1", error.getCode(), "Assert Error successfully.");
    }

    /**
     * Validate the toString method constructs JSON serializable version of {@link Error}.
     */
    @Test
    public void toStringSuccess() {
        Assertions.assertEquals("{\"code\":\"1\",\"description\":\"Entry already exists.\"}",
                error.toString(), "Assert toString method constructs Entry in the correct format.");
    }
}
