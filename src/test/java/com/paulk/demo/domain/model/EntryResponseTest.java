package com.paulk.demo.domain.model;

import com.paulk.demo.constants.ErrorCodes;
import com.paulk.demo.model.Entry;
import com.paulk.demo.model.EntryResponse;
import com.paulk.demo.model.Error;
import com.paulk.demo.utils.ObjectMapperInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the {@link EntryResponse} domain model.
 */
public class EntryResponseTest {

    private Entry entryJohnLennon;
    private Error error;
    private EntryResponse successResponse;
    private EntryResponse altSuccessResponse;
    private EntryResponse errorResponse;

    @BeforeEach
    public void setup() {
        entryJohnLennon = new Entry("John Lennon");
        error = new Error();
        error.setCode(ErrorCodes.ALREADY_EXISTS);
        error.setDescription(ErrorCodes.ALREADY_EXISTS_DESCRIPTION);

        successResponse = new EntryResponse();
        successResponse.setEntry(entryJohnLennon);

        altSuccessResponse = new EntryResponse();
        altSuccessResponse.setEntry(entryJohnLennon);

        errorResponse = new EntryResponse();
        errorResponse.setError(error);
    }

    /**
     * Validates the comparison of two {@link EntryResponse} objects.
     */
    @Test
    public void entryResponseEqualsSuccess() {
        // Setup
        Assertions.assertEquals(successResponse, successResponse, "Assert EntryResponse equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryResponse} objects.
     */
    @Test
    public void entryResponseEqualsDifferentObjectsSuccess() {
        // Setup
        Assertions.assertEquals(successResponse, altSuccessResponse, "Assert EntryResponse equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryResponse} objects.
     */
    @Test
    public void entryEqualsInvalid() {
        Assertions.assertNotEquals(successResponse, errorResponse, "Assert EntryResponse equals invalid.");
    }

    /**
     * Validates the comparison of two {@link EntryResponse} objects.
     */
    @Test
    public void entryEqualsNullSuccess() {
        Assertions.assertFalse(successResponse.equals(null), "Assert EntryResponse equals null successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryResponse} objects.
     */
    @Test
    public void entryCompareHashCodeSuccess() {
        Assertions.assertEquals(successResponse.hashCode(), altSuccessResponse.hashCode(), "Assert EntryResponse equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryResponse} objects.
     */
    @Test
    public void entryCompareHashCodeInvalid() {
        Assertions.assertNotEquals(successResponse.hashCode(), errorResponse.hashCode(), "Assert EntryResponse equals invalid.");
    }

    /**
     * Validates the comparison of two {@link EntryResponse} objects.
     */
    @Test
    public void entryGetValueSuccess() {
        Assertions.assertEquals("John Lennon", successResponse.getEntry().getValue(), "Assert Entry getValue EntryResponse successfully.");
    }

    /**
     * Validate the toString method constructs JSON serializable version of {@link EntryResponse}.
     */
    @Test
    public void toStringSuccess() {
        Assertions.assertTrue(ObjectMapperInstance.INSTANCE.isValidJson(successResponse.toString()),
                "Assert toString method constructs valid json in the correct format.");
    }
}
