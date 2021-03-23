package com.paulk.demo.dao;

import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.repository.EntryRepository;
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
 * Test the {@link EntryDataStoreOperationsService} class.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EntryDataStoreOperationsServiceTest {

    @Mock
    EntryRepository entryRepository;

    @InjectMocks
    EntryDataStoreOperationsService entryDataStoreOperationsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#add(Entry)} when no {@link Entry} exists.
     */
    @Test
    public void addEntrySuccess() {
        // 1. Setup test data.
        Entry entry = new Entry("testValue");
        // 2. Setup mocks.
        Mockito.when(entryRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(entryRepository.save(Mockito.any())).thenReturn(entry);
        // 3. Perform action
        boolean operationSuccessful = entryDataStoreOperationsService.add(entry);
        // 4. Assert results.
        Assertions.assertTrue(operationSuccessful, "Assert add operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#add(Entry)} when no {@link Entry} exists.
     */
    @Test
    public void addEntrySaveFailure() {
        // 1. Setup test data.
        Entry entry = new Entry("testValue");
        // 2. Setup mocks.
        Mockito.when(entryRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(entryRepository.save(Mockito.any())).thenReturn(null);
        // 3. Perform action
        boolean operationSuccessful = entryDataStoreOperationsService.add(entry);
        // 4. Assert results.
        Assertions.assertFalse(operationSuccessful, "Assert add operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#add(Entry)} when an {@link Entry} already exists.
     */
    @Test
    public void addEntryDuplicateExists() {
        // 1. Setup test data.
        Entry entry = new Entry("testValue");
        // 2. Setup mocks.
        Mockito.when(entryRepository.findById(Mockito.any())).thenReturn(Optional.of(entry));
        // 3. Perform action
        boolean operationSuccessful = entryDataStoreOperationsService.add(entry);
        // 4. Assert results.
        Assertions.assertFalse(operationSuccessful, "Assert add operation behavior is correct when a duplicate already exists.");
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#delete(Entry)} when {@link Entry} exists.
     */
    @Test
    public void deleteEntrySuccess() {
        // 1. Setup test data.
        Entry entry = new Entry("testValue");
        // 2. Setup mocks.
        Mockito.when(entryRepository.findById(Mockito.any())).thenReturn(Optional.of(entry));
        // 3. Perform action
        boolean operationSuccessful = entryDataStoreOperationsService.delete(entry);
        // 4. Assert results.
        Assertions.assertTrue(operationSuccessful, "Assert delete operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#delete(Entry)} when no {@link Entry} exists.
     */
    @Test
    public void deleteEntryNoneExists() {
        // 1. Setup test data.
        Entry entry = new Entry("testValue");
        // 2. Setup mocks.
        Mockito.when(entryRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        // 3. Perform action
        boolean operationSuccessful = entryDataStoreOperationsService.delete(entry);
        // 4. Assert results.
        Assertions.assertFalse(operationSuccessful, "Assert delete operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#update(String, Entry)} when {@link Entry} exists.
     */
    @Test
    public void updateEntrySuccess() {
        // 1. Setup test data.
        Entry entry = new Entry("testValue","1234");
        Entry updatedEntry = new Entry("updatedValue", "1234");
        // 2. Setup mocks.
        Mockito.when(entryRepository.findById(Mockito.any())).thenReturn(Optional.of(entry));
        Mockito.when(entryRepository.save(Mockito.any())).thenReturn(updatedEntry);
        // 3. Perform action
        Optional<Entry> actualEntry = entryDataStoreOperationsService.update(entry.getValue(), updatedEntry);
        // 4. Assert results.
        Assertions.assertEquals(updatedEntry, actualEntry.get() , "Assert update operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#delete(Entry)} when key for{@link Entry} does not exists.
     */
    @Test
    public void updateEntryNoKeyExistsSuccess() {
        // 1. Setup test data.
        Entry entry = new Entry("testValue");
        Entry updatedEntry = new Entry("updatedValue");
        // 2. Setup mocks.
        Mockito.when(entryRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        // 3. Perform action
        Optional<Entry> actualEntry = entryDataStoreOperationsService.update(entry.getValue(), updatedEntry);
        // 4. Assert results.
        Assertions.assertEquals(Optional.empty(), actualEntry , "Assert update operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#update(String, Entry)} when {@link Entry} exists but save fails.
     */
    @Test
    public void updateEntrySaveFails() {
        // 1. Setup test data.
        Entry entry = new Entry("testValue");
        Entry updatedEntry = new Entry("updatedValue");
        // 2. Setup mocks.
        Mockito.when(entryRepository.findById(Mockito.any())).thenReturn(Optional.of(entry));
        Mockito.when(entryRepository.save(Mockito.any())).thenReturn(null);
        // 3. Perform action
        Optional<Entry> actualEntry = entryDataStoreOperationsService.update(entry.getValue(), updatedEntry);
        // 4. Assert results.
        Assertions.assertEquals(Optional.empty(), actualEntry , "Assert update operation behavior is correct.");
    }

    /**
     * Validate the behaviour of {@link EntryDataStoreOperationsService#update(String, Entry)} when {@link Entry} exists.
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
        Mockito.when(entryRepository.findAll()).thenReturn(entries);
        // 3. Perform action
        Set<Entry> actualEntries = entryDataStoreOperationsService.getAll();
        // 4. Assert results.
        Set<Entry> remainingEntries = SetComparisonUtils.getDifference(actualEntries, entries);
        Assertions.assertEquals(0, remainingEntries.size(), "Assert getAll operation behavior is correct.");
    }
}
