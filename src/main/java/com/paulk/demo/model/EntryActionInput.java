package com.paulk.demo.model;

import com.paulk.demo.utils.StringBuilderUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * A POJO for interacting with an {@link Entry}.
 */
public class EntryActionInput implements Serializable {
    private String key;
    private Entry entry;

    /**
     * Private Constructor for {@link EntryActionInput}.
     */
    private EntryActionInput() {
        // Unable to instantiate directly. Using builder pattern.
    }

    /**
     * A builder class for {@link EntryActionInput}.
     */
    public static class EntryActionInputBuilder {
        private String key;
        private Entry entry;

        /**
         * Adds key to the {@link EntryActionInput}.
         *
         * @param key - The key of the {@link EntryActionInputBuilder} being processed.
         * @return The {@link EntryActionInputBuilder}.
         */
        public EntryActionInputBuilder withKey(String key) {
            this.key = key;
            return this;
        }

        /**
         * Adds an firstName to the {@link EntryActionInputBuilder}.
         *
         * @param entry - The {@link Entry} of the {@link EntryActionInputBuilder} being processed.
         * @return The {@link EntryActionInputBuilder}.
         */
        public EntryActionInputBuilder withEntry(Entry entry) {
            this.entry = entry;
            return this;
        }

        /**
         * Build a {@link EntryActionInput} using {@link EntryActionInputBuilder}.
         *
         * @return The {@link EntryActionInput}.
         */
        public EntryActionInput build() {
            EntryActionInput entryActionInput = new EntryActionInput();
            entryActionInput.key = this.key;
            entryActionInput.entry = this.entry;
            return entryActionInput;
        }
    }


    /**
     * Get the key {@link String} for the {@link EntryActionInput}.
     *
     * @return The {@link String} key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the key {@link Entry} for the {@link EntryActionInput}.
     *
     * @return The {@link Entry} key.
     */
    public Entry getEntry() {
        return entry;
    }

    /**
     * Implements object comparison
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link EntryActionInput}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EntryActionInput that = (EntryActionInput) obj;
        return Objects.equals(this.key, that.key) &&
                Objects.equals(this.entry, that.entry);
    }

    /**
     * Returns the hashCode for the {@link EntryActionInput}.
     *
     * @return The hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, entry);
    }

    /**
     * Implementation of toString for an {@link Entry}.
     *
     * @return The {@link String} representation for an {@link Entry}.
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "key", this.key, true);
        StringBuilderUtils.addFieldToBuilder(builder, "entry", this.entry, false);
        builder.append("}");
        return builder.toString();
    }
}
