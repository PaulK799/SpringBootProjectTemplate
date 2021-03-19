package com.paulk.demo.dao;

import com.paulk.demo.domain.Entry;
import com.paulk.demo.repository.EntryRepository;
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

    @Autowired
    private EntryRepository entryRepository;

    /**
     * Add an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be added.
     * @return If true, {@link Entry} added successfully, else {@link Entry} already exists.
     */
    @Override
    public boolean add(Entry entry) {
        Optional<Entry> entryOptional = get(entry);
        if (!entryOptional.isPresent()) {
            entry = entryRepository.save(entry);
            if (entry != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be deleted.
     * @return If true, {@link Entry} removed successfully, else {@link Entry} doesn't exists.
     */
    @Override
    public boolean delete(Entry entry) {
        Optional<Entry> entryOptional = get(entry);
        if (entryOptional.isPresent()) {
            entryRepository.delete(entry);
            return true;
        }
        return false;
    }

    /**
     * Add an {@link Entry} to the data store.
     *
     * @param entry - The {@link Entry} to be added.
     * @return If true, {@link Entry} added successfully, else {@link Entry} already exists.
     */
    @Override
    public Optional<Entry> update(String key, Entry entry) {
        Optional<Entry> entryOptional = get(entry);
        if (entryOptional.isPresent()) {
            entry = entryRepository.save(entry);
            if (entry != null) {
                return Optional.of(entry);
            }
        }
        return Optional.empty();
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
