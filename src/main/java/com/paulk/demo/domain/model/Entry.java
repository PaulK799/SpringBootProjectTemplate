package com.paulk.demo.domain.model;

import com.paulk.demo.DemoApplication;
import com.paulk.demo.utils.StringBuilderUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Objects;

/**
 * A POJO for representing information to be stored to a data store by the {@link DemoApplication}.
 */
@RedisHash("entries")
public class Entry implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    protected String value;

    /**
     * Default constructor for {@link Entry}.
     */
    public Entry() {
        this.value = null;
    }

    /**
     * Constructor for {@link Entry}.
     *
     * @param value - The {@link String} value to be set for the {@link Entry}.
     */
    public Entry(String value) {
        this.value = value;
    }

    /**
     * Gets the {@link String} value for the {@link Entry}.
     *
     * @return The {@link String} value returned.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the {@link String} value for the {@link Entry}.
     *
     * @param value - The {@link String} to be set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Implements object comparison for a {@link Entry}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link Entry}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Entry that = (Entry) obj;
        return Objects.equals(this.value, that.value);
    }

    /**
     * Returns the hashCode for the {@link Entry} based on the 'value'.
     *
     * @return The hashcode for the {@link Entry}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.value);
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
        StringBuilderUtils.addFieldToBuilder(builder,"value", this.value, false);
        builder.append("}");
        return builder.toString();
    }
}
