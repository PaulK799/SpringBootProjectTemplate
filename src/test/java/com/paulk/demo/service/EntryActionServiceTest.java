package com.paulk.demo.service;

import com.paulk.demo.dao.EntryDataStoreOperationsService;
import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.domain.model.EntriesResponse;
import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.domain.model.EntryOperationResponse;
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

import java.util.ArrayList;
import java.util.List;

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
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        entryActionResponse.setSuccessfulOperation(true);
        Mockito.when(entryDataStoreOperationsService.add(Mockito.any())).thenReturn(entryActionResponse);
        // 2. Perform action
        EntryOperationResponse actualEntryActionResponse = entryActionService.addEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertTrue(actualEntryActionResponse.isSuccessfulOperation(), "Assert add operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#addEntry(EntryActionInput)} when an {@link Entry} exists.
     */
    @Test
    public void addEntryFailure() {
        // 1. Setup mocks.
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        Mockito.when(entryDataStoreOperationsService.add(Mockito.any())).thenReturn(entryActionResponse);
        // 2. Perform action
        EntryOperationResponse actualEntryActionResponse = entryActionService.addEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertFalse(actualEntryActionResponse.isSuccessfulOperation(), "Assert add operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#deleteEntry(EntryActionInput)} when an {@link Entry} doesn't exist.
     */
    @Test
    public void deleteEntryFailure() {
        // 1. Setup mocks.
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        Mockito.when(entryDataStoreOperationsService.delete(Mockito.any())).thenReturn(entryActionResponse);
        // 2. Perform action
        EntryOperationResponse actualEntryActionResponse = entryActionService.deleteEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertFalse(actualEntryActionResponse.isSuccessfulOperation(), "Assert add operation behavior is correct when a duplicate already exists.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#deleteEntry(EntryActionInput)} when {@link Entry} exists.
     */
    @Test
    public void deleteEntrySuccess() {
        // 1. Setup mocks.
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        entryActionResponse.setSuccessfulOperation(true);
        Mockito.when(entryDataStoreOperationsService.delete(Mockito.any())).thenReturn(entryActionResponse);
        // 2. Perform action
        EntryOperationResponse actualEntryActionResponse = entryActionService.deleteEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertTrue(actualEntryActionResponse.isSuccessfulOperation(), "Assert delete operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#updateEntry(EntryActionInput)} when {@link Entry} exists.
     */
    @Test
    public void updateEntrySuccess() {
        // 1. Setup mocks.
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        entryActionResponse.setEntry(entry);
        entryActionResponse.setSuccessfulOperation(true);
        Mockito.when(entryDataStoreOperationsService.update(Mockito.any(), Mockito.any())).thenReturn(entryActionResponse);
        // 2. Perform action
        EntryOperationResponse actualEntryActionResponse = entryActionService.updateEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertEquals(entry, actualEntryActionResponse.getEntry(), "Assert update operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#updateEntry(EntryActionInput)} when key for{@link Entry} does not exists.
     */
    @Test
    public void updateEntryNoKeyExistsSuccess() {
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.update(Mockito.any(), Mockito.any())).thenReturn(entryActionResponse);
        // 2. Perform action
        EntryOperationResponse actualEntryActionResponse = entryActionService.updateEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertNull(actualEntryActionResponse.getEntry(), "Assert update operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#getEntry(EntryActionInput)} when {@link Entry} exists.
     */
    @Test
    public void getEntrySuccess() {
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        entryActionResponse.setEntry(entry);
        entryActionResponse.setSuccessfulOperation(true);
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.get(Mockito.any())).thenReturn(entryActionResponse);
        // 2. Perform action
        EntryOperationResponse actualOperationResponse = entryActionService.getEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertEquals(entry, actualOperationResponse.getEntry(), "Assert get operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#getEntry(EntryActionInput)} when no {@link Entry} exists.
     */
    @Test
    public void getEntryFailure() {
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        // 1. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.get(Mockito.any())).thenReturn(entryActionResponse);
        // 2. Perform action
        EntryOperationResponse actualOperationResponse = entryActionService.getEntry(entryActionInput);
        // 3. Assert results.
        Assertions.assertNull(actualOperationResponse.getEntry(), "Assert get operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryActionService#getAllEntries()}when multiple {@link Entry} exists.
     */
    @Test
    public void getAllSuccess() {
        // 1. Setup test data.
        // 1. Setup test data.
        EntriesResponse expectedEntriesResponse = new EntriesResponse();
        List<Entry> expectedEntries = new ArrayList<>();
        expectedEntries.add(new Entry("A"));
        expectedEntries.add(new Entry("B"));
        expectedEntries.add(new Entry("C"));
        expectedEntries.add(new Entry("a"));
        expectedEntries.add(new Entry("b"));
        expectedEntries.add(new Entry("c"));
        expectedEntriesResponse.getEntries().addAll(expectedEntries);

        // 2. Setup mocks.
        Mockito.when(entryDataStoreOperationsService.getAll()).thenReturn(expectedEntriesResponse);
        // 3. Perform action
        EntriesResponse actualEntriesResponse = entryDataStoreOperationsService.getAll();
        // 4. Assert results.
        Assertions.assertEquals(expectedEntries.size(), actualEntriesResponse.getEntries().size(), "Assert getAll operation behavior is correct.");
    }
}
