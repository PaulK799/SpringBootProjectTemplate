package com.paulk.demo.domain.model;

import com.paulk.demo.utils.StringBuilderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class EntriesResponse implements Serializable {
    private static final long serialVersionUID = 4L;

    private Collection<Entry> entries;
    private Error error;

    /**
     * Default Constructor for {@link EntriesResponse}.
     */
    public EntriesResponse() {
        this.entries = new ArrayList<>();
        this.error = null;
    }

    /**
     * Get the {@link Entry} for the {@link EntriesResponse}.
     *
     * @return The {@link Entry}.
     */
    public Collection<Entry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

    /**
     * Get the {@link Error} for the {@link EntriesResponse}.
     *
     * @return The {@link Error}.
     */
    public Error getError() {
        return error;
    }

    /**
     * Set the {@link Error} for the {@link EntriesResponse}.
     *
     * @param error - The {@link Error} to be set.
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     * Implements object comparison for a {@link EntriesResponse}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link EntriesResponse}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        EntriesResponse that = (EntriesResponse) obj;
        return Objects.equals(this.entries, that.entries) &&
                Objects.equals(this.error, that.error);
    }

    /**
     * Returns the hashCode for the {@link EntriesResponse} based on the 'entries' and 'error'.
     *
     * @return The hashcode for the {@link EntriesResponse}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.entries, this.error);
    }


    /**
     * Implementation of toString for an {@link EntriesResponse}.
     *
     * @return The {@link String} representation for an {@link EntriesResponse}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "entry", this.entries, true);
        StringBuilderUtils.addFieldToBuilder(builder, "error", this.error, false);
        builder.append("}");
        return builder.toString();
    }

    /**
     * Generates an {@link Error} for the {@link EntriesResponse}.
     *
     * @param code        - The error code of type {@link String}.
     * @param description - The error description of type {@link String}.
     * @param httpStatus  - The {@link HttpStatus} to be set.
     * @return A {@link ResponseEntity} of type {@link EntriesResponse}.
     */
    public static ResponseEntity<EntriesResponse> generateEntryResponseError(String code, String description, HttpStatus httpStatus) {
        EntriesResponse entryResponse = new EntriesResponse();
        Error error = new Error();
        error.setCode(code);
        error.setDescription(description);
        entryResponse.setError(error);
        return new ResponseEntity<>(entryResponse, httpStatus);
    }
}