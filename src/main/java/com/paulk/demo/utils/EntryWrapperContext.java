package com.paulk.demo.utils;

import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.domain.model.EntryResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * A {@link RequestScope} bean for to allow for access to a  {@link Entry}.
 */
@Component
@RequestScope
public class EntryWrapperContext {

    private Entry entry;

    /**
     * Get the {@link Entry} for the {@link EntryResponse}.
     *
     * @return The {@link Entry}.
     */
    public Entry getEntry() {
        return entry;
    }

    /**
     * Set the {@link Entry} for the {@link EntryResponse}.
     *
     * @param entry - The {@link Entry} to be set.
     */
    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
