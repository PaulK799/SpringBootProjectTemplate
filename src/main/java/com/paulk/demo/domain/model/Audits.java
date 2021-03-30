package com.paulk.demo.domain.model;

import com.paulk.demo.utils.StringBuilderUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A domain model for wrapping multiple {@link Audit}.
 */
public class Audits implements Serializable {
    private static final long serialVersionUID = 6L;

    private List<Audit> auditList;


    /**
     * Returns next {@link Integer} auditid.
     *
     * @return The next audit id as an {@link Integer}.
     */
    public Integer getNextAuditId() {
        return auditList.stream()
                .filter(audit -> audit.getAuditId() != null)
                .max(Comparator.comparing(Audit::getAuditId))
                .map(Audit::getAuditId)
                .orElse(0) + 1;
    }

    /**
     * Returns next {@link Integer} auditid.
     *
     * @param audits - The {@link Audits} to be processed.
     * @param action - The action {@link String} to be set.
     * @return The next audit id as an {@link Integer}.
     */
    public Audit createAudit(Audits audits, String action) {
        Audit audit = new Audit();
        // Action
        audit.setAction(action);
        // Timestamp
        LocalDateTime modifiedDateTime = LocalDateTime.now(ZoneId.of(ZoneOffset.UTC.toString()));
        audit.setTimestamp(modifiedDateTime);
        // Audit Id
        Integer nextAuditId = audits.getNextAuditId();
        audit.setAuditId(nextAuditId);
        return audit;
    }


    /**
     * Constructor for the {@link Audits}.
     */
    public Audits() {
        this.auditList = new ArrayList<>();
    }

    /**
     * Get a list of {@link Audit} for the {@link Audits}.
     *
     * @return A {@link List} of {@link Audit}.
     */
    public List<Audit> getAuditList() {
        if (auditList == null) {
            auditList = new ArrayList<>();
        }
        return auditList;
    }

    /**
     * Implements object comparison for a {@link Audits}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link Audits}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Audits that = (Audits) obj;
        return Objects.equals(this.auditList, that.auditList);
    }

    /**
     * Returns the hashCode for the {@link Audit} based on the 'value'.
     *
     * @return The hashcode for the {@link Audit}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.auditList);
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
        StringBuilderUtils.addFieldToBuilder(builder, "auditList", this.auditList, false);
        builder.append("}");
        return builder.toString();
    }
}
