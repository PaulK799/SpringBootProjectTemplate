package com.paulk.demo.utils;

import java.util.HashSet;
import java.util.Set;

public class SetComparisonUtils {

    /**
     * Default Constructor for {@link SetComparisonUtils}.
     */
    private SetComparisonUtils() {
        // Private access to prevent instantiation.
    }

    /**
     * Returns the remaining differences between two {@link Set} of type {@link T}.
     *
     * @param setOne - The {@link Set} to be compared with.
     * @param setTwo - The {@link Set} to be compared to.
     * @param <T>    - A generic type for comparison.
     * @return A {@link Set} containing the differences between the two sets.
     */
    public static <T> Set<T> getDifference(final Set<T> setOne, final Set<T> setTwo) {
        Set<T> result = new HashSet<>(setOne);
        result.removeIf(setTwo::contains);
        return result;
    }
}
