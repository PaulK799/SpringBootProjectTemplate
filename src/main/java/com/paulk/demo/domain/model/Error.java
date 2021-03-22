package com.paulk.demo.domain.model;

import com.paulk.demo.utils.StringBuilderUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * A POJO for representing an {@link Error}.
 */
public class Error implements Serializable {
    private static final long serialVersionUID = 3L;

    private String code;
    private String description;

    /**
     * Get the code {@link String} for the {@link Error}.
     *
     * @return The {@link String} code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code {@link String} for the {@link Error}.
     *
     * @param code - The {@link String} to be set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the description {@link String} for the {@link Error}.
     *
     * @return The {@link String} description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description {@link String} for the {@link Error}.
     *
     * @param description - The {@link String} to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Implements object comparison for a {@link Error}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link Error}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Error that = (Error) obj;
        return Objects.equals(this.code, that.code) &&
                Objects.equals(this.description, that.description);
    }

    /**
     * Returns the hashCode for the {@link Error} based on the 'code' and 'description'.
     *
     * @return The hashcode for the {@link Error}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.code, this.description);
    }


    /**
     * Implementation of toString for an {@link Error}.
     *
     * @return The {@link String} representation for an {@link Error}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "code", this.code, true);
        StringBuilderUtils.addFieldToBuilder(builder, "description", this.description, false);
        builder.append("}");
        return builder.toString();
    }
}
