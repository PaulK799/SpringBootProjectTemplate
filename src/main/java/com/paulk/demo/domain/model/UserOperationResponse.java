package com.paulk.demo.domain.model;

import com.paulk.demo.utils.StringBuilderUtils;

import java.util.Objects;

/**
 * A wrapper for {@link EntryResponse} for processing operations.
 */
public class UserOperationResponse extends UserResponse {
    private boolean successfulOperation;

    public UserOperationResponse() {
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
     * Implements object comparison for a {@link UserOperationResponse}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link UserOperationResponse}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UserOperationResponse that = (UserOperationResponse) obj;
        return Objects.equals(this.user, that.user) &&
                Objects.equals(this.error, that.error) &&
                Objects.equals(this.successfulOperation, that.successfulOperation);
    }

    /**
     * Returns the hashCode for the {@link UserOperationResponse} based on the 'user' and 'error'.
     *
     * @return The hashcode for the {@link UserOperationResponse}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.user, this.error, this.successfulOperation);
    }


    /**
     * Implementation of toString for an {@link UserOperationResponse}.
     *
     * @return The {@link String} representation for an {@link UserOperationResponse}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "user", this.user, true);
        StringBuilderUtils.addFieldToBuilder(builder, "error", this.error, true);
        StringBuilderUtils.addFieldToBuilder(builder, "successfulOperation", this.successfulOperation, false);
        builder.append("}");
        return builder.toString();
    }
}
