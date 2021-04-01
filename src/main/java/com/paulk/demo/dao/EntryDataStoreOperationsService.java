package com.paulk.demo.dao;

import com.paulk.demo.constants.AuditActionCodes;
import com.paulk.demo.domain.model.Audit;
import com.paulk.demo.domain.model.Audits;
import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.domain.model.EntryActionResponse;
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
    public EntryActionResponse add(Entry entry) {
        EntryActionResponse entryActionResponse = new EntryActionResponse();
        Optional<Entry> entryOptional = get(entry);
        if (!entryOptional.isPresent()) {
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
        return entryActionResponse;
    }

    /**
     * Remove an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be deleted.
     * @return If true, {@link Entry} removed successfully, else {@link Entry} doesn't exists.
     */
    @Override
    public EntryActionResponse delete(Entry entry) {
        EntryActionResponse entryActionResponse = new EntryActionResponse();
        Optional<Entry> entryOptional = get(entry);
        if (entryOptional.isPresent() && entryOptional.get().getId().equals(entry.getId())) {
            Entry retrievedEntry = entryOptional.get();
            // Generate Audit
            Audits audits = Optional.ofNullable(retrievedEntry.getAudits())
                    .orElseGet(Audits::new);
            Audit audit = audits.createAudit(audits, AuditActionCodes.DELETE.getCode());
            audits.getAuditList().add(audit);
            retrievedEntry.setAudits(audits);
            entryActionResponse.setEntry(retrievedEntry);
            try {
                entryRepository.delete(entryOptional.get());
                entryActionResponse.setSuccessfulOperation(true);
            } catch (IllegalArgumentException exception) {
                LOGGER.error("Error deleting Entry during the Delete Operation.");
            }
        }
        return entryActionResponse;
    }

    /**
     * Add an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be added.
     * @return If true, {@link Entry} added successfully, else {@link Entry} already exists.
     */
    @Override
    public EntryActionResponse update(String key, Entry entry) {
        EntryActionResponse entryActionResponse = new EntryActionResponse();
        Entry searchEntry = new Entry(key, entry.getId());
        Optional<Entry> retrievedEntryOpt = get(searchEntry);
        if (retrievedEntryOpt.isPresent() && retrievedEntryOpt.get().getId().equals(entry.getId())) {
            Entry retrievedEntry = retrievedEntryOpt.get();
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
        return entryActionResponse;
    }

    /**
     * Remove an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be retrieved.
     * @return An {@link Optional} element of type {@link Entry}.
     */
    @Override
    public Optional<Entry> get(Entry entry) {
        Optional<String> valueOptional = Optional.ofNullable(entry)
                .map(Entry::getValue);
        return valueOptional.flatMap(s -> entryRepository.findById(s));
    }

    /**
     * Get a {@link Collection} of all {@link Entry} in the data store.
     *
     * @return A {@link Collection} of all {@link Entry}.
     */
    @Override
    public Set<Entry> getAll() {
        Set<Entry> entries = new HashSet<>();
        Iterable<Entry> entryIterable = entryRepository.findAll();
        for (Entry entry : entryIterable) {
            entries.add(entry);
        }
        return entries;
    }
}
