package com.paulk.demo.constants;

import com.paulk.demo.domain.model.Error;

/**
 * A class of static constant {@link ErrorCodes} for the {@link Error}.
 */
public class ErrorCodes {

    public static final String ALREADY_EXISTS = "1";
    public static final String ALREADY_EXISTS_DESCRIPTION = "Entry already exists.";

    public static final String NOT_FOUND = "2";
    public static final String NOT_FOUND_DESCRIPTION = "Entry could not be found.";

    /**
     * Private constructor for the {@link ErrorCodes} class.
     */
    private ErrorCodes() {
        // Stop initialization of class
    }
}
