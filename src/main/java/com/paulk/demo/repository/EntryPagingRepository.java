package com.paulk.demo.repository;

import com.paulk.demo.domain.model.Entry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * A {@link PagingAndSortingRepository} for the {@link Entry}.
 */
@Repository
public interface EntryPagingRepository extends PagingAndSortingRepository<Entry, String> {
}
