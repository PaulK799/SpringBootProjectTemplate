package com.paulk.demo.model;

import com.paulk.demo.utils.StringBuilderUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * A POJO for interacting with an {@link Entry}.
 */
public class UserActionInput implements Serializable {
    private User user;

    /**
     * Private Constructor for {@link UserActionInput}.
     */
    private UserActionInput() {
        // Unable to instantiate directly. Using builder pattern.
    }

    /**
     * A builder class for {@link UserActionInput}.
     */
    public static class UserActionInputBuilder {
        private User user;

        /**
         * Adds an user to the {@link UserActionInputBuilder}.
         *
         * @param user - The {@link User} of the {@link UserActionInputBuilder} being processed.
         * @return The {@link UserActionInputBuilder}.
         */
        public UserActionInputBuilder withEntry(User user) {
            this.user = user;
            return this;
        }

        /**
         * Build a {@link UserActionInput} using {@link UserActionInputBuilder}.
         *
         * @return The {@link UserActionInput}.
         */
        public UserActionInput build() {
            UserActionInput entryActionInput = new UserActionInput();
            entryActionInput.user = this.user;
            return entryActionInput;
        }
    }

    /**
     * Get the key {@link User} for the {@link UserActionInput}.
     *
     * @return The {@link User} key.
     */
    public User getUser() {
        return user;
    }

    /**
     * Implements object comparison
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link UserActionInput}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserActionInput that = (UserActionInput) obj;
        return Objects.equals(this.user, that.user);
    }

    /**
     * Returns the hashCode for the {@link UserActionInput}.
     *
     * @return The hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(user);
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
        StringBuilderUtils.addFieldToBuilder(builder, "user", this.user, false);
        builder.append("}");
        return builder.toString();
    }
}
