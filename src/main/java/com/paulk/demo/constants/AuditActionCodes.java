package com.paulk.demo.constants;

/**
 * An constants enum for the {@link AuditActionCodes}.
 */
public enum AuditActionCodes {
    ADD("Add"),
    UPDATE("Update"),
    DELETE("Delete");

    private final String code;

    /**
     * Constructor for the {@link AuditActionCodes}.
     *
     * @param code - The {@link String} for the {@link AuditActionCodes}
     */
    AuditActionCodes(String code) {
        this.code = code;
    }

    /**
     * Returns the prefix for the {@link AuditActionCodes}.
     *
     * @return prefix for the {@link AuditActionCodes}.
     */
    public String getCode() {
        return code;
    }

}
