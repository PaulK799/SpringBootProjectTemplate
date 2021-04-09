package com.paulk.demo.domain.model;

import com.paulk.demo.model.Entry;
import com.paulk.demo.utils.ObjectMapperInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the {@link Entry} domain model.
 */
public class EntryTest {

    private Entry entryJohnLennon;
    private Entry entryJohnLennonLookAlike;
    private Entry entryPaulMcCartney;

    /**
     * Setup {@link EntryTest} for each test.
     */
    @BeforeEach
    public void setup() {
        entryJohnLennon = new Entry("John Lennon");
        entryJohnLennonLookAlike = new Entry("John Lennon");

        // Default Constructor used.
        entryPaulMcCartney = new Entry();
        entryPaulMcCartney.setValue("Paul McCartney");
    }

    /**
     * Validates the comparison of two {@link Entry} objects.
     */
    @Test
    public void entryEqualsSuccess() {
        // Setup
        Assertions.assertEquals(entryJohnLennon, entryJohnLennon, "Assert Entry equals successfully.");
    }

    /**
     * Validates the comparison of two {@link Entry} objects.
     */
    @Test
    public void entryEqualsDifferentObjectsSuccess() {
        // Setup
        Assertions.assertNotEquals(entryJohnLennon, entryJohnLennonLookAlike, "Assert Entry equals successfully.");
    }

    /**
     * Validates the comparison of two {@link Entry} objects.
     */
    @Test
    public void entryEqualsInvalid() {
        Assertions.assertNotEquals(entryJohnLennon, entryPaulMcCartney, "Assert Entry equals invalid.");
    }

    /**
     * Validates the comparison of two {@link Entry} objects.
     */
    @Test
    public void entryEqualsNullSuccess() {
        Assertions.assertFalse(entryJohnLennon.equals(null), "Assert Entry equals null successfully.");
    }

    /**
     * Validates the comparison of two {@link Entry} objects.
     */
    @Test
    public void entryCompareHashCodeSuccess() {
        Assertions.assertNotEquals(entryJohnLennon.hashCode(), entryJohnLennonLookAlike.hashCode(), "Assert Entry equals successfully.");
    }

    /**
     * Validates the comparison of two {@link Entry} objects.
     */
    @Test
    public void entryCompareHashCodeInvalid() {
        Assertions.assertNotEquals(entryJohnLennon.hashCode(), entryPaulMcCartney.hashCode(), "Assert Entry equals invalid.");
    }

    /**
     * Validates the comparison of two {@link Entry} objects.
     */
    @Test
    public void entryGetValueSuccess() {
        Assertions.assertEquals("Paul McCartney", entryPaulMcCartney.getValue(), "Assert Entry getValue equals successfully.");
    }

    /**
     * Validate the toString method constructs JSON serializable version of {@link Entry}.
     */
    @Test
    public void toStringSuccess() {
        Assertions.assertTrue(ObjectMapperInstance.INSTANCE.isValidJson(entryJohnLennon.toString()),
                "Assert toString method constructs valid json in the correct format.");
    }
}
