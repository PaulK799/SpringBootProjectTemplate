package com.paulk.demo.domain.model.dto;

import com.paulk.demo.utils.StringBuilderUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "role")
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 10L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    /**
     * Get a name {@link String} for the {@link Role}.
     *
     * @return The {@link String} representing the name for the {@link Role}.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Implements object comparison for a {@link Role}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link Role}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Role that = (Role) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name);
    }

    /**
     * Returns the hashCode for the {@link Role} based on the 'name' and 'id'.
     *
     * @return The hashcode for the {@link Role}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.id);
    }

    /**
     * Implementation of toString for an {@link Role}.
     *
     * @return The {@link String} representation for an {@link Role}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "id", this.id, true);
        StringBuilderUtils.addFieldToBuilder(builder, "name", this.name, false);
        builder.append("}");
        return builder.toString();
    }
}
