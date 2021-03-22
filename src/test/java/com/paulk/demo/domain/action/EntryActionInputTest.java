package com.paulk.demo.domain.action;

import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.domain.model.Entry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the {@link Entry} domain model.
 */
public class EntryActionInputTest {

    private EntryActionInput entryActionInput;
    private EntryActionInput altEntryActionInput;
    private EntryActionInput altEntrySameKeyActionInput;
    private Entry entryJohnLennon;

    /**
     * Setup {@link EntryActionInputTest} for each test.
     */
    @BeforeEach
    public void setup() {
        entryJohnLennon = new Entry("John Lennon");
        entryActionInput = new EntryActionInput.EntryActionInputBuilder()
                .withKey("main")
                .withEntry(entryJohnLennon)
                .build();

        altEntryActionInput = new EntryActionInput.EntryActionInputBuilder()
                .withKey("main")
                .withEntry(entryJohnLennon)
                .build();

        altEntrySameKeyActionInput = new EntryActionInput.EntryActionInputBuilder()
                .withKey("alternative")
                .withEntry(entryJohnLennon)
                .build();
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryEqualsSuccess() {
        Assertions.assertEquals(entryActionInput, entryActionInput, "Assert EntryActionInput equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryEqualsDifferentObjectsSuccess() {
        Assertions.assertEquals(entryActionInput, altEntryActionInput, "Assert EntryActionInput equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryEqualsInvalid() {
        Assertions.assertNotEquals(entryActionInput, altEntrySameKeyActionInput, "Assert EntryActionInput equals invalid.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryEqualsNullSuccess() {
        Assertions.assertFalse(entryActionInput.equals(null), "Assert EntryActionInput equals null successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryKeyEqualsSuccess() {
        Assertions.assertEquals(entryActionInput.getKey(), entryActionInput.getKey(), "Assert EntryActionInput getKey equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryKeyEqualsDifferentObjectsSuccess() {
        Assertions.assertEquals(entryActionInput.getKey(), altEntryActionInput.getKey(), "Assert EntryActionInput equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryKeyEqualsInvalid() {
        Assertions.assertNotEquals(entryActionInput.getKey(), altEntrySameKeyActionInput.getKey(), "Assert EntryActionInput equals invalid.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryKeyEqualsNullSuccess() {
        Assertions.assertFalse(entryActionInput.getKey().equals(null), "Assert EntryActionInput equals null successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryCompareHashCodeSuccess() {
        Assertions.assertEquals(entryActionInput.hashCode(), altEntryActionInput.hashCode(), "Assert EntryActionInput equals successfully.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entryCompareHashCodeInvalid() {
        Assertions.assertNotEquals(entryActionInput.hashCode(), altEntrySameKeyActionInput.hashCode(), "Assert EntryActionInput equals invalid.");
    }

    /**
     * Validates the comparison of two {@link EntryActionInput} objects.
     */
    @Test
    public void entrygetValueSuccess() {
        Assertions.assertEquals("John Lennon", entryActionInput.getEntry().getValue(), "Assert EntryActionInput getValue equals successfully.");
    }

    /**
     * Validate the toString method constructs JSON serializable version of {@link EntryActionInput}.
     */
    @Test
    public void toStringSuccess() {
        Assertions.assertEquals("{\"key\":\"main\",\"entry\":{\"value\":\"John Lennon\"}}",
                entryActionInput.toString(), "Assert toString method constructs Entry in the correct format.");
    }
}
