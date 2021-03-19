package com.paulk.demo.utils;

import com.paulk.demo.domain.Entry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test the {@link StringBuilderUtils} class
 */
public class StringBuilderUtilsTest {

    private StringBuilder builder;

    @BeforeEach
    public void setup() {
        builder = new StringBuilder();
    }

    @Test
    public void buildFromStringNoSeparatorSuccess() {
        StringBuilderUtils.addFieldToBuilder(builder, "test", "test", false);
        Assertions.assertEquals("\"test\":\"test\"", builder.toString());
    }

    @Test
    public void buildFromStringAddSeparatorSuccess() {
        StringBuilderUtils.addFieldToBuilder(builder, "test", "test", true);
        Assertions.assertEquals("\"test\":\"test\",", builder.toString());
    }

    @Test
    public void buildFromListNoSeparatorSuccess() {
        // Setup Test
        List<String>  entries = new ArrayList<>();
        entries.add("First");
        entries.add("Second");
        entries.add("Third");

        StringBuilderUtils.addFieldToBuilder(builder, "test", entries, false);
        Assertions.assertEquals("\"test\":\"[First, Second, Third]\"", builder.toString());
    }

    @Test
    public void buildFromListAddSeparatorSuccess() {
        // Setup Test
        List<String>  entries = new ArrayList<>();
        entries.add("First");
        entries.add("Second");
        entries.add("Third");

        StringBuilderUtils.addFieldToBuilder(builder, "test", entries, true);
        Assertions.assertEquals("\"test\":\"[First, Second, Third]\",", builder.toString());
    }
}
