package com.paulk.demo.dao;

import com.paulk.demo.domain.Entry;

import java.util.Collection;
import java.util.Optional;

/**
 * An interface of {@link DataStoreOperations} for the application.
 *
 * @param <K> - The key of the {@link V} being processed.
 * @param <V> - The {@link V} to be stored in the data store.
 */
public interface DataStoreOperations<K, V> {

    /**
     * Add an {@link V} to the data store.
     *
     * @param entry - The {@link V} to be added.
     * @return If true, {@link V} added successfully, else {@link V} already exists.
     */
    public boolean add(Entry entry);

    /**
     * Remove an {@link V} to the data store.
     *
     * @param entry - The {@link V} to be deleted.
     * @return If true, {@link V} removed successfully, else {@link V} doesn't exists.
     */
    public boolean delete(V entry);

    /**
     * Update an existing {@link V} in the data store.
     *
     * @param key   - The {@link V} to be updated based the {@link K}.
     * @param entry - The {@link V} to be updated.
     * @return If true, {@link V} removed successfully, else {@link V} doesn't exists.
     */
    public boolean update(K key, V entry);

    /**
     * Remove an {@link V} to the data store.
     *
     * @param entry - The {@link V} to be retrieved.
     * @return An {@link Optional} element of type {@link V}.
     */
    public Optional<V> get(V entry);

    /**
     * Get a {@link Collection} of all {@link V} in the data store.
     *
     * @return A
     */
    public Collection<V> getAll();
}