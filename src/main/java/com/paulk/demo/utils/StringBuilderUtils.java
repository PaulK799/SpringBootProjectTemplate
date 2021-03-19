package com.paulk.demo.utils;

/**
 * A helper utility method for constructing a {@link StringBuilder} entry to be used by toString() to represent each field in the {@link Object} provided.
 */
public class StringBuilderUtils {
    private static final String DOUBLE_QUOTE = "\"";
    private static final String COLON = ":";
    private static final String COMMA = ",";

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
        builder.append(DOUBLE_QUOTE);
        builder.append(object);
        builder.append(DOUBLE_QUOTE);

        if (addSeparator) {
            builder.append(COMMA);
        }
    }
}
