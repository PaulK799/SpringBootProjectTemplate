package com.paulk.demo.domain.model;

import com.paulk.demo.utils.StringBuilderUtils;

import java.io.Serializable;
import java.util.Objects;

public class EntryResponse implements Serializable {
    private static final long serialVersionUID = 2L;

    private Entry entry;
    private Error error;

    /**
     * Default Constructor for {@link EntryResponse}.
     */
    public EntryResponse() {
        this.entry = null;
        this.error = null;
    }

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

    /**
     * Get the {@link Error} for the {@link EntryResponse}.
     *
     * @return The {@link Error}.
     */
    public Error getError() {
        return error;
    }

    /**
     * Set the {@link Error} for the {@link EntryResponse}.
     *
     * @param error - The {@link Error} to be set.
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     * Implements object comparison for a {@link EntryResponse}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link EntryResponse}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        EntryResponse that = (EntryResponse) obj;
        return Objects.equals(this.entry, that.entry) &&
                Objects.equals(this.error, that.error);
    }

    /**
     * Returns the hashCode for the {@link EntryResponse} based on the 'entry' and 'error'.
     *
     * @return The hashcode for the {@link EntryResponse}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.entry, this.error);
    }


    /**
     * Implementation of toString for an {@link EntryResponse}.
     *
     * @return The {@link String} representation for an {@link EntryResponse}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "entry", this.entry, true);
        StringBuilderUtils.addFieldToBuilder(builder, "error", this.error, false);
        builder.append("}");
        return builder.toString();
    }
}
