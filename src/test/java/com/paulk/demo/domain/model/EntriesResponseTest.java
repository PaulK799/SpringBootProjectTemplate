package com.paulk.demo.domain.model;

import com.paulk.demo.constants.ErrorCodes;
import com.paulk.demo.model.EntriesResponse;
import com.paulk.demo.model.Entry;
import com.paulk.demo.model.Error;
import com.paulk.demo.utils.ObjectMapperInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the {@link EntriesResponse} domain model.
 */
public class EntriesResponseTest {

    private Entry entryJohnLennon;
    private Error error;
    private EntriesResponse successResponse;
    private EntriesResponse altSuccessResponse;
    private EntriesResponse errorResponse;

    @BeforeEach
    public void setup() {
        entryJohnLennon = new Entry("John Lennon");
        error = new Error();
        error.setCode(ErrorCodes.ALREADY_EXISTS);
        error.setDescription(ErrorCodes.ALREADY_EXISTS_DESCRIPTION);

        successResponse = new EntriesResponse();
        successResponse.getEntries().add(entryJohnLennon);

        altSuccessResponse = new EntriesResponse();
        successResponse.getEntries().add(entryJohnLennon);

        errorResponse = new EntriesResponse();
        errorResponse.setError(error);
    }

    /**
     * Validates the comparison of two {@link EntriesResponse} objects.
     */
    @Test
    public void entryResponseEqualsSuccess() {
        // Setup
        Assertions.assertEquals(successResponse, successResponse, "Assert EntryResponse equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntriesResponse} objects.
     */
    @Test
    public void entryResponseEqualsDifferentObjectsSuccess() {
        // Setup
        Assertions.assertNotEquals(successResponse, altSuccessResponse, "Assert EntryResponse equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntriesResponse} objects.
     */
    @Test
    public void entryEqualsInvalid() {
        Assertions.assertNotEquals(successResponse, errorResponse, "Assert EntryResponse equals invalid.");
    }

    /**
     * Validates the comparison of two {@link EntriesResponse} objects.
     */
    @Test
    public void entryEqualsNullSuccess() {
        Assertions.assertFalse(successResponse.equals(null), "Assert EntryResponse equals null successfully.");
    }

    /**
     * Validates the comparison of two {@link EntriesResponse} objects.
     */
    @Test
    public void entryCompareHashCodeSuccess() {
        Assertions.assertNotEquals(successResponse.hashCode(), altSuccessResponse.hashCode(), "Assert EntryResponse equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntriesResponse} objects.
     */
    @Test
    public void entryCompareHashCodeInvalid() {
        Assertions.assertNotEquals(successResponse.hashCode(), errorResponse.hashCode(), "Assert EntryResponse equals invalid.");
    }

    /**
     * Validates the comparison of two {@link EntriesResponse} objects.
     */
    @Test
    public void entryGetValueSuccess() {
        Assertions.assertEquals("John Lennon", successResponse.getEntries().stream().findFirst().get().getValue(), "Assert Entry getValue EntryResponse successfully.");
    }

    /**
     * Validate the toString method constructs JSON serializable version of {@link EntriesResponse}.
     */
    @Test
    public void toStringSuccess() {
        Assertions.assertTrue(ObjectMapperInstance.INSTANCE.isValidJson(successResponse.toString()),
                "Assert toString method constructs valid json in the correct format.");
    }
}
