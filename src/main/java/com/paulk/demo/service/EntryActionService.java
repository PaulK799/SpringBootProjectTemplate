package com.paulk.demo.service;

import com.paulk.demo.dao.EntryDataStoreOperationsService;
import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.domain.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * A {@link Service} for interacting with the {@link EntryDataStoreOperationsService}.
 */
@Service
public class EntryActionService {

    @Autowired
    protected EntryDataStoreOperationsService entryDataStoreOperationsService;

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#add(Entry)} operation.
     *
     * @param entryActionInput - The {@link EntryActionInput} to be processed.
     * @return If true, {@link Entry} added successfully. Otherwise false if failed to save.
     */
    public boolean addEntry(EntryActionInput entryActionInput) {
        return entryDataStoreOperationsService.add(entryActionInput.getEntry());
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#update(String, Entry)} operation.
     *
     * @param entryActionInput - The {@link EntryActionInput} to be processed.
     * @return A {@link Optional} of type {@link Entry}.
     */
    public Optional<Entry> updateEntry(EntryActionInput entryActionInput) {
        return entryDataStoreOperationsService.update(entryActionInput.getKey(), entryActionInput.getEntry());
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#delete(Entry)} operation.
     *
     * @param entryActionInput - The {@link EntryActionInput} to be processed.
     * @return If true, {@link Entry} deleted successfully. Otherwise false if failed to delete.
     */
    public boolean deleteEntry(EntryActionInput entryActionInput) {
        return entryDataStoreOperationsService.delete(entryActionInput.getEntry());
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#get(Entry)} operation.
     *
     * @param entryActionInput - The {@link EntryActionInput} to be processed.
     * @return A {@link Optional} of type {@link Entry}.
     */
    public Optional<Entry> getEntry(EntryActionInput entryActionInput) {
        return entryDataStoreOperationsService.get(entryActionInput.getEntry());
    }

    /**
     * Method for performing the {@link EntryDataStoreOperationsService#getAll()} operation.
     *
     * @return A {@link Set} of all {@link Entry} in the data store.
     */
    public Set<Entry> getAllEntries() {
        return entryDataStoreOperationsService.getAll();
    }
}
