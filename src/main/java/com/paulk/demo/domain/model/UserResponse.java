package com.paulk.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.paulk.demo.domain.model.dto.User;
import com.paulk.demo.utils.StringBuilderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A domain model for representing a {@link User} or {@link Error}.
 */
@JsonInclude(Include.NON_EMPTY)
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 8L;

    protected User user;
    protected Error error;

    /**
     * Default Constructor for {@link UserResponse}.
     */
    public UserResponse() {
        this.user = null;
        this.error = null;
    }

    /**
     * Get the {@link User} for the {@link UserResponse}.
     *
     * @return The {@link User}.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the {@link User} for the {@link UserResponse}.
     *
     * @param user - The {@link User} to be set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the {@link Error} for the {@link UserResponse}.
     *
     * @return The {@link Error}.
     */
    public Error getError() {
        return error;
    }

    /**
     * Set the {@link Error} for the {@link UserResponse}.
     *
     * @param error - The {@link Error} to be set.
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     * Implements object comparison for a {@link UserResponse}.
     *
     * @param obj - The {@link Object} being parsed.
     * @return A boolean indicating if equals to {@link UserResponse}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UserResponse that = (UserResponse) obj;
        return Objects.equals(this.user, that.user) &&
                Objects.equals(this.error, that.error);
    }

    /**
     * Returns the hashCode for the {@link UserResponse} based on the 'user' and 'error'.
     *
     * @return The hashcode for the {@link UserResponse}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.user, this.error);
    }


    /**
     * Implementation of toString for an {@link UserResponse}.
     *
     * @return The {@link String} representation for an {@link UserResponse}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        StringBuilderUtils.addFieldToBuilder(builder, "user", this.user, true);
        StringBuilderUtils.addFieldToBuilder(builder, "error", this.error, false);
        builder.append("}");
        return builder.toString();
    }

    /**
     * Generates an {@link Error} for the {@link UserResponse}.
     *
     * @param code        - The error code of type {@link String}.
     * @param description - The error description of type {@link String}.
     * @param httpStatus  - The {@link HttpStatus} to be set.
     * @return A {@link ResponseEntity} of type {@link UserResponse}.
     */
    public static ResponseEntity<UserResponse> generateUserResponseError(String code, String description, HttpStatus httpStatus) {
        UserResponse userResponse = new UserResponse();
        Error error = new Error();
        error.setCode(code);
        error.setDescription(description);
        userResponse.setError(error);
        return new ResponseEntity<>(userResponse, httpStatus);
    }
}
