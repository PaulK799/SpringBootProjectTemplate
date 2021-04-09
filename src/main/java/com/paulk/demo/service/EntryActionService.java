package com.paulk.demo.service;

import com.paulk.demo.dao.DataStoreOperations;
import com.paulk.demo.dao.EntryDataStoreOperationsService;
import com.paulk.demo.model.EntryActionInput;
import com.paulk.demo.model.EntriesResponse;
import com.paulk.demo.model.Entry;
import com.paulk.demo.model.EntryOperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * A {@link Service} for interacting with the {@link EntryDataStoreOperationsService}.
 */
@CacheConfig(cacheNames = {"entries"})
@Service
public class EntryActionService {

    @Autowired
    protected DataStoreOperations<String, Entry> entryDataStoreOperationsService;

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#add(Entry)} operation.
     *
     * @param entryActionInput - The {@link EntryActionInput} to be processed.
     * @return An {@link EntryOperationResponse} containing a {@link EntryOperationResponse#isSuccessfulOperation()}. If true, {@link Entry} added successfully. Otherwise false if failed to save.
     */
    @CacheEvict(value = "entries", key = "#entryActionInput.entry.value")
    public EntryOperationResponse addEntry(EntryActionInput entryActionInput) {
        return entryDataStoreOperationsService.add(entryActionInput.getEntry());
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#update(String, Entry)} operation.
     *
     * @param entryActionInput - The {@link EntryActionInput} to be processed.
     * @return A {@link EntryOperationResponse} containing an {@link Entry}.
     */
    @CacheEvict(value = "entries", key = "#entryActionInput.key")
    public EntryOperationResponse updateEntry(EntryActionInput entryActionInput) {
        return entryDataStoreOperationsService.update(entryActionInput.getKey(), entryActionInput.getEntry());
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#delete(Entry)} operation.
     *
     * @param entryActionInput - The {@link EntryActionInput} to be processed.
     * @return An {@link EntryOperationResponse} containing a {@link EntryOperationResponse#isSuccessfulOperation()}. If true, {@link Entry} deleted successfully. Otherwise false if failed to delete.
     */
    @CacheEvict(value = "entries", key = "#entryActionInput.entry.value")
    public EntryOperationResponse deleteEntry(EntryActionInput entryActionInput) {
        return entryDataStoreOperationsService.delete(entryActionInput.getEntry());
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#get(Entry)} operation.
     *
     * @param entryActionInput - The {@link EntryActionInput} to be processed.
     * @return A {@link EntryOperationResponse} containing the {@link Entry}.
     */
    @Cacheable(value = "entries", key = "#entryActionInput.entry.value")
    public EntryOperationResponse getEntry(EntryActionInput entryActionInput) {
        return entryDataStoreOperationsService.get(entryActionInput.getEntry());
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#getAll()} operation.
     *
     * @return The {@link EntriesResponse} to be processed.
     */
    public EntriesResponse getAllEntries() {
        return entryDataStoreOperationsService.getAll();
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#getAll()} operation.
     *
     * @param pageNumber - The {@link Integer} page number for the response.
     * @param pageSize   - The {@link Integer} page size for the response.
     * @return The {@link EntriesResponse} to be processed.
     */
    public EntriesResponse getAllEntries(Integer pageNumber, Integer pageSize) {
        return entryDataStoreOperationsService.getAll(pageNumber, pageSize);
    }
}
