package com.paulk.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The singleton pattern {@link ObjectMapperInstance}.
 */
public enum ObjectMapperInstance {
    INSTANCE;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor for {@link ObjectMapperInstance}.
     */
    private ObjectMapperInstance() {
        // Prevent direct access.
        mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
    }

    /**
     * Get the {@link ObjectMapper}.
     *
     * @return The {@link ObjectMapper} for the {@link ObjectMapperInstance}.
     */
    public ObjectMapper getObjectMapper() {
        return mapper;
    }

    /**
     * Validates the {@link String} to determine if valid JSON.
     *
     * @param isJson - The {@link String} to be validated.
     * @return If true, valid json, otherwise invalid.
     */
    public boolean isValidJson(String isJson) {
        try {
            mapper.readTree(isJson);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
