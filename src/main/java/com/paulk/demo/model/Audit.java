package com.paulk.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.paulk.demo.utils.StringBuilderUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A domain model for representing an {@link Audit}.
 */
public class Audit implements Serializable {
    private static final long serialVersionUID = 5L;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;

    private Integer auditId;
    private String action;

    /**
     * Get the created date time {@link LocalDateTime}.
     *
     * @return The {@link LocalDateTime} representing the created date time for the {@link Audit}.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Set the {@link LocalDateTime}.
     *
     * @param timestamp - The {@link LocalDateTime} to be set.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get the auditid for the {@link Audit}.
     *
     * @return The {@link Integer} representing the audit id for the {@link Audit}.
     */
    public Integer getAuditId() {
        return auditId;
    }

    /**
     * Set the audit id of type {@link Integer} for the {@link Audit}.
     *
     * @param auditId - The {@link Integer} to be set.
     */
    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    /**
     * Get the action for the {@link String}.
     *
     * @return The {@link String} representing the action for the {@link Audit}.
     */
    public String getAction() {
        return action;
    }

    /**
     * Set the action of type {@link String} for the {@link Audit}.
     *
     * @param action - The {@link String} to be set.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Implements object comparison for a {@link Audit}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link Audit}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Audit that = (Audit) obj;
        return Objects.equals(this.timestamp, that.timestamp) &&
                Objects.equals(this.auditId, that.auditId)
                && Objects.equals(this.action, that.action);
    }

    /**
     * Returns the hashCode for the {@link Audit} based on the 'value'.
     *
     * @return The hashcode for the {@link Audit}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.timestamp, this.auditId, this.action);
    }

    /**
     * Implementation of toString for an {@link Audit}.
     *
     * @return The {@link String} representation for an {@link Audit}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "timestamp", this.timestamp, true);
        StringBuilderUtils.addFieldToBuilder(builder, "auditId", this.auditId, true);
        StringBuilderUtils.addFieldToBuilder(builder, "action", this.action, false);
        builder.append("}");
        return builder.toString();
    }
}
