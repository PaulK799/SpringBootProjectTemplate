package com.paulk.demo.repository;

import com.paulk.demo.domain.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A {@link CrudRepository} for the {@link Entry}.
 */
@Repository
public interface EntryRepository extends CrudRepository<Entry, String> {
}
