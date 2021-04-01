package com.paulk.demo.dao;

import com.paulk.demo.constants.AuditActionCodes;
import com.paulk.demo.domain.model.Audit;
import com.paulk.demo.domain.model.Audits;
import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.domain.model.EntryOperationResponse;
import com.paulk.demo.repository.EntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A {@link Component} for performing {@link CrudRepository} operations via {@link EntryRepository} using an {@link Entry} object.
 */
@Component
public class EntryDataStoreOperationsService implements DataStoreOperations<String, Entry> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntryDataStoreOperationsService.class);

    @Autowired
    private EntryRepository entryRepository;

    /**
     * Add an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be added.
     * @return If true, {@link Entry} added successfully, else {@link Entry} already exists.
     */
    @Override
    public EntryOperationResponse add(Entry entry) {
        LOGGER.debug("Start Debugging (Add Entry) ---- ");
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        EntryOperationResponse getOperationResponse = get(entry);
        if (!getOperationResponse.isSuccessfulOperation()) {
            try {
                Audits audits = Optional.ofNullable(entry.getAudits())
                        .orElseGet(Audits::new);
                Audit audit = audits.createAudit(audits, AuditActionCodes.ADD.getCode());
                audits.getAuditList().add(audit);
                entry.setAudits(audits);

                entryRepository.save(entry);
                entryActionResponse.setSuccessfulOperation(true);
            } catch (IllegalArgumentException exception) {
                LOGGER.error("Error saving Entry during the Add Operation.");
            }
        }
        LOGGER.debug("End Debugging (Add Entry) ---- ");
        return entryActionResponse;
    }

    /**
     * Remove an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be deleted.
     * @return If true, {@link Entry} removed successfully, else {@link Entry} doesn't exists.
     */
    @Override
    public EntryOperationResponse delete(Entry entry) {
        LOGGER.debug("Start Debugging (Delete Entry) ---- ");
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        EntryOperationResponse getOperationResponse = get(entry);
        if (getOperationResponse.isSuccessfulOperation() && getOperationResponse.getEntry().getId().equals(entry.getId())) {
            Entry retrievedEntry = getOperationResponse.getEntry();
            // Generate Audit
            Audits audits = Optional.ofNullable(retrievedEntry.getAudits())
                    .orElseGet(Audits::new);
            Audit audit = audits.createAudit(audits, AuditActionCodes.DELETE.getCode());
            audits.getAuditList().add(audit);
            retrievedEntry.setAudits(audits);
            entryActionResponse.setEntry(retrievedEntry);
            try {
                entryRepository.delete(retrievedEntry);
                entryActionResponse.setSuccessfulOperation(true);
            } catch (IllegalArgumentException exception) {
                LOGGER.error("Error deleting Entry during the Delete Operation.");
            }
        }
        LOGGER.debug("End Debugging (Delete Entry) ---- ");
        return entryActionResponse;
    }

    /**
     * Add an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be added.
     * @return If true, {@link Entry} added successfully, else {@link Entry} already exists.
     */
    @Override
    public EntryOperationResponse update(String key, Entry entry) {
        LOGGER.debug("Start Debugging (Update Entry) ---- ");
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        Entry searchEntry = new Entry(key, entry.getId());
        EntryOperationResponse getOperationResponse = get(searchEntry);
        if (getOperationResponse.isSuccessfulOperation() && getOperationResponse.getEntry().getId().equals(entry.getId())) {
            Entry retrievedEntry = getOperationResponse.getEntry();
            try {
                entryRepository.delete(retrievedEntry);
            } catch (IllegalArgumentException exception) {
                LOGGER.error("Error deleting Entry during the Update Operation.");
                return entryActionResponse;
            }

            // Update the Value
            retrievedEntry.setValue(entry.getValue());

            // Add Audit for Update Operation
            Audits audits = Optional.ofNullable(retrievedEntry.getAudits())
                    .orElseGet(Audits::new);
            Audit audit = audits.createAudit(audits, AuditActionCodes.UPDATE.getCode());
            audits.getAuditList().add(audit);
            retrievedEntry.setAudits(audits);

            try {
                entryRepository.save(retrievedEntry);
                entryActionResponse.setEntry(retrievedEntry);
                entryActionResponse.setSuccessfulOperation(true);
            } catch (IllegalArgumentException exception) {
                LOGGER.error("Error saving Entry during the Update Operation.");
            }
        }
        LOGGER.debug("End Debugging (Update Entry) ---- ");
        return entryActionResponse;
    }

    /**
     * Remove an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be retrieved.
     * @return An {@link Optional} element of type {@link Entry}.
     */
    @Override
    public EntryOperationResponse get(Entry entry) {
        LOGGER.debug("Start Debugging (Find Entry) ---- ");
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        Optional<String> valueOptional = Optional.ofNullable(entry)
                .map(Entry::getValue);
        if (valueOptional.isPresent()) {
            Optional<Entry> entryOpt = entryRepository.findById(valueOptional.get());
            if (entryOpt.isPresent()) {
                entryActionResponse.setEntry(entryOpt.get());
                entryActionResponse.setSuccessfulOperation(true);
            }
        }
        return entryActionResponse;
    }

    /**
     * Get a {@link Collection} of all {@link Entry} in the data store.
     *
     * @return A {@link Collection} of all {@link Entry}.
     */
    @Override
    public Set<Entry> getAll() {
        LOGGER.debug("Start Debugging (Find All Entry) ---- ");
        Set<Entry> entries = new HashSet<>();
        Iterable<Entry> entryIterable = entryRepository.findAll();
        for (Entry entry : entryIterable) {
            entries.add(entry);
        }
        LOGGER.debug("End Debugging (Find All Entry) ---- ");
        return entries;
    }
}
