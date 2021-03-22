package com.paulk.demo.utils;

import java.util.Collection;

/**
 * A helper utility method for constructing a {@link StringBuilder} entry to be used by toString() to represent each field in the {@link Object} provided.
 */
public class StringBuilderUtils {
    private static final String DOUBLE_QUOTE = "\"";
    private static final String COLON = ":";
    private static final String COMMA = ",";

    /**
     * Private constructor for {@link StringBuilderUtils}.
     */
    private StringBuilderUtils() {
        // Private Constructor
    }

    /**
     * Adds an {@link Object} to the {@link StringBuilder}.
     *
     * @param builder      - The {@link StringBuilder} to be appended to.
     * @param field        - The {@link String} to be added.
     * @param object       - The {@link String} to be added.
     * @param addSeparator - If true, a separator of "," is added.
     */
    public static void addFieldToBuilder(StringBuilder builder, String field, String object, boolean addSeparator) {
        builder.append(DOUBLE_QUOTE);
        builder.append(field);
        builder.append(DOUBLE_QUOTE);
        builder.append(COLON);
        builder.append(DOUBLE_QUOTE);
        builder.append(object);
        builder.append(DOUBLE_QUOTE);

        if (addSeparator) {
            builder.append(COMMA);
        }
    }

    /**
     * Adds an {@link Object} to the {@link StringBuilder}.
     *
     * @param builder      - The {@link StringBuilder} to be appended to.
     * @param field        - The {@link String} to be added.
     * @param object       - The {@link Collection} to be added.
     * @param addSeparator - If true, a separator of "," is added.
     */
    public static <T> void addFieldToBuilder(StringBuilder builder, String field, Collection<T> object, boolean addSeparator) {

        builder.append(DOUBLE_QUOTE);
        builder.append(field);
        builder.append(DOUBLE_QUOTE);
        builder.append(COLON);
        builder.append("[");
        addFieldCollection(builder, object);
        builder.append("]");

        if (addSeparator) {
            builder.append(COMMA);
        }
    }

    /**
     * Adds an {@link Object} to the {@link StringBuilder}.
     *
     * @param builder      - The {@link StringBuilder} to be appended to.
     * @param field        - The {@link String} to be added.
     * @param object       - The {@link T} to be added.
     * @param addSeparator - If true, a separator of "," is added.
     */
    public static <T> void addFieldToBuilder(StringBuilder builder, String field, T object, boolean addSeparator) {
        builder.append(DOUBLE_QUOTE);
        builder.append(field);
        builder.append(DOUBLE_QUOTE);
        builder.append(COLON);
        builder.append(object);

        if (addSeparator) {
            builder.append(COMMA);
        }
    }

    /**
     * Process the value of a {@link Collection} when its a {@link T}.
     *
     * @param builder - The {@link StringBuilder} being processed.
     * @param object  - The {@link T} being processed.
     */
    public static <T> void addFieldCollection(StringBuilder builder, Collection<T> object) {
        int index = 0;
        int size = object.size() - 1;
        for (T value : object) {
            if (value instanceof String) {
                builder.append(DOUBLE_QUOTE);
                builder.append(value);
                builder.append(DOUBLE_QUOTE);
            } else {
                builder.append(value);
            }

            if (index < size) {
                builder.append(COMMA);
            }
            index++;
        }
    }
}
