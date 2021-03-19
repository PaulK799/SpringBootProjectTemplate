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
     * Adds an {@link Object} to the {@link StringBuilder}.
     *
     * @param builder      - The {@link StringBuilder} to be appended to.
     * @param field        - The {@link String} to be added.
     * @param object       - The {@link T} to be added.
     * @param addSeparator - If true, a separator of "," is added.
     */
    public static <T> void addFieldToBuilder(StringBuilder builder, String field, T object, boolean addSeparator) {

        boolean isString = object instanceof String;
        boolean isCollection = object instanceof Collection;

        builder.append(DOUBLE_QUOTE);
        builder.append(field);
        builder.append(DOUBLE_QUOTE);
        builder.append(COLON);

        if (isString) {
            builder.append(DOUBLE_QUOTE);
        }

        if (isCollection) {
            builder.append("[");

            int index = 0;
            Collection<T> collection = (Collection<T>) object;
            int size = collection.size() -1 ;
            for (T entry : collection) {
                builder.append(DOUBLE_QUOTE);
                builder.append(entry);
                builder.append(DOUBLE_QUOTE);

                if (index < size) {
                    builder.append(COMMA);
                }
                index++;
            }
            builder.append("]");
        } else {
            builder.append(object);
        }

        if (isString) {
            builder.append(DOUBLE_QUOTE);
        }

        if (addSeparator) {
            builder.append(COMMA);
        }
    }
}
