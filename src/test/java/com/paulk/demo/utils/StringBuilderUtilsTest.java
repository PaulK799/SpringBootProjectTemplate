package com.paulk.demo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Assertions.assertEquals("\"test\":[\"First\",\"Second\",\"Third\"]", builder.toString());
    }

    @Test
    public void buildFromListAddSeparatorSuccess() {
        // Setup Test
        List<String>  entries = new ArrayList<>();
        entries.add("First");
        entries.add("Second");
        entries.add("Third");

        StringBuilderUtils.addFieldToBuilder(builder, "test", entries, true);
        Assertions.assertEquals("\"test\":[\"First\",\"Second\",\"Third\"],", builder.toString());
    }

    @Test
    public void buildFromListIntegerAddSeparatorSuccess() {
        // Setup Test
        List<Integer>  entries = new ArrayList<>();
        entries.add(2);
        entries.add(1);
        entries.add(3);

        StringBuilderUtils.addFieldToBuilder(builder, "test", entries, true);
        Assertions.assertEquals("\"test\":[2,1,3],", builder.toString());
    }

    @Test
    public void buildFromSetIntegerAddSeparatorSuccess() {
        // Setup Test
        Set<Integer> entries = new HashSet<>();
        entries.add(2);
        entries.add(1);
        entries.add(3);

        StringBuilderUtils.addFieldToBuilder(builder, "test", entries, true);
        Assertions.assertEquals("\"test\":[1,2,3],", builder.toString());
    }

    @Test
    public void buildFromListBooleanAddSeparatorSuccess() {
        // Setup Test
        List<Boolean>  entries = new ArrayList<>();
        entries.add(true);
        entries.add(false);
        entries.add(true);

        StringBuilderUtils.addFieldToBuilder(builder, "test", entries, true);
        Assertions.assertEquals("\"test\":[true,false,true],", builder.toString());
    }
}
