package com.paulk.demo.service;

import com.paulk.demo.dao.EntryDataStoreOperationsService;
import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.utils.SetComparisonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Test the {@link EntryActionService} class.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EntryActionServiceTest {

    @Mock
    EntryDataStoreOperationsService entryDataStoreOperationsService;

    @InjectMocks
    EntryActionService entryActionService;

    private EntryActionInput entryActionInput;
    private Entry entry;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        entry = new Entry("testValue");
        entryActionInput = new EntryActionInput.EntryActionInputBuilder()
                .withKey("test")
                .withEntry(entry)
                .build();
    }

    /**
     * Validate the behaviour of {@link EntryActionService#addEntry(EntryActionInput)} when no {@link Entry} exists.
     */
    @Test
    public void addEntrySuccess() {
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.add(Mockito.any())).thenReturn(true);
        // 2. Perform action
        boolean operationSuccessful = entryActionService.addEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertTrue(operationSuccessful, "Assert add operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#addEntry(EntryActionInput)} when an {@link Entry} exists.
     */
    @Test
    public void addEntryFailure() {
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.add(Mockito.any())).thenReturn(false);
        // 2. Perform action
        boolean operationSuccessful = entryActionService.addEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertFalse(operationSuccessful, "Assert add operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#deleteEntry(EntryActionInput)} when an {@link Entry} doesn't exist.
     */
    @Test
    public void deleteEntryFailure() {
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.delete(Mockito.any())).thenReturn(false);
        // 2. Perform action
        boolean operationSuccessful = entryActionService.deleteEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertFalse(operationSuccessful, "Assert add operation behavior is correct when a duplicate already exists.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#deleteEntry(EntryActionInput)} when {@link Entry} exists.
     */
    @Test
    public void deleteEntrySuccess() {
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.delete(Mockito.any())).thenReturn(true);
        // 2. Perform action
        boolean operationSuccessful = entryActionService.deleteEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertTrue(operationSuccessful, "Assert delete operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#updateEntry(EntryActionInput)} when {@link Entry} exists.
     */
    @Test
    public void updateEntrySuccess() {
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.update(Mockito.any(), Mockito.any())).thenReturn(Optional.of(entry));
        // 2. Perform action
        Optional<Entry> actualEntry = entryActionService.updateEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertEquals(entry, actualEntry.get() , "Assert update operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#updateEntry(EntryActionInput)} when key for{@link Entry} does not exists.
     */
    @Test
    public void updateEntryNoKeyExistsSuccess() {
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.update(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        // 2. Perform action
        Optional<Entry> actualEntry = entryActionService.updateEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertEquals(Optional.empty(), actualEntry , "Assert update operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#getEntry(EntryActionInput)} when {@link Entry} exists.
     */
    @Test
    public void getEntrySuccess() {
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.get(Mockito.any())).thenReturn(Optional.of(entry));
        // 2. Perform action
        Optional<Entry> actualEntries = entryActionService.getEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertEquals(Optional.of(entry), actualEntries , "Assert get operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#getEntry(EntryActionInput)} when no {@link Entry} exists.
     */
    @Test
    public void getEntryFailure() {
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.get(Mockito.any())).thenReturn(Optional.empty());
        // 2. Perform action
        Optional<Entry> actualEntries = entryActionService.getEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertEquals(Optional.empty(), actualEntries , "Assert get operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#getAllEntries()}when multiple {@link Entry} exists.
     */
    @Test
    public void getAllSuccess() {
        // 1. Setup test data.
        Set<Entry> entries = new HashSet<>();
        entries.add(new Entry("A"));
        entries.add(new Entry("B"));
        entries.add(new Entry("C"));
        entries.add(new Entry("a"));
        entries.add(new Entry("b"));
        entries.add(new Entry("c"));

        // 2. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.getAll()).thenReturn(entries);
        // 3. Perform action
        Set<Entry> actualEntries = entryActionService.getAllEntries();
        // 4. Assert results.
        Set<Entry> remainingEntries = SetComparisonUtils.getDifference(actualEntries, entries);
        Assertions.assertEquals(0, remainingEntries.size(), "Assert getAllEntries operation behavior is correct.");
    }
}
