package com.paulk.demo.domain.model;

import com.paulk.demo.utils.StringBuilderUtils;

import java.util.Objects;

/**
 * A wrapper for {@link EntryResponse} for processing operations.
 */
public class EntryActionResponse extends EntryResponse {
    private boolean successfulOperation;

    public EntryActionResponse() {
        super();
        successfulOperation = false;
    }

    /**
     * Get the successfulOperation primitive.
     *
     * @return If true, the requested operation was successful.
     */
    public boolean isSuccessfulOperation() {
        return successfulOperation;
    }

    /**
     * Set the successfulOperation primitive.
     *
     * @param successfulOperation - Indicator to determine if an operation was successful.
     */
    public void setSuccessfulOperation(boolean successfulOperation) {
        this.successfulOperation = successfulOperation;
    }

    /**
     * Implements object comparison for a {@link EntryActionResponse}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link EntryActionResponse}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        EntryActionResponse that = (EntryActionResponse) obj;
        return Objects.equals(this.entry, that.entry) &&
                Objects.equals(this.error, that.error) &&
                Objects.equals(this.successfulOperation, that.successfulOperation);
    }

    /**
     * Returns the hashCode for the {@link EntryActionResponse} based on the 'entry' and 'error'.
     *
     * @return The hashcode for the {@link EntryActionResponse}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.entry, this.error, this.successfulOperation);
    }


    /**
     * Implementation of toString for an {@link EntryActionResponse}.
     *
     * @return The {@link String} representation for an {@link EntryActionResponse}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "entry", this.entry, true);
        StringBuilderUtils.addFieldToBuilder(builder, "error", this.error, true);
        StringBuilderUtils.addFieldToBuilder(builder, "successfulOperation", this.successfulOperation, false);
        builder.append("}");
        return builder.toString();
    }
}
