package com.paulk.demo.controller;

import com.paulk.demo.config.SecurityConfig;
import com.paulk.demo.constants.ErrorCodes;
import com.paulk.demo.dao.UserOperationService;
import com.paulk.demo.domain.input.UserActionInput;
import com.paulk.demo.domain.model.EntryResponse;
import com.paulk.demo.domain.model.Error;
import com.paulk.demo.domain.model.UserOperationResponse;
import com.paulk.demo.domain.model.UserResponse;
import com.paulk.demo.domain.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Defines a {@link Controller}  which defines a set of CRUD Operations.
 * Defines the interactions with {@link User} objects in the data store defined.
 */
@Controller
public class AuthenticationController {
    private static final String USER_RESPONSE_ATTRIBUTE = "userResponse";

    @Autowired
    protected UserOperationService userOperationService;

    @Autowired
    protected SecurityConfig securityConfig;

    /**
     * Exception handler for {@link HttpMessageNotReadableException} when the {@link RequestBody} cannot be read.
     *
     * @return a {@link EntryResponse} containing a human readable {@link Error}.
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<UserResponse> handleException() {
        return UserResponse.generateUserResponseError(ErrorCodes.NOT_VALID_FORMAT, ErrorCodes.NOT_VALID_FORMAT_DESCRIPTION, HttpStatus.BAD_REQUEST);
    }

    /**
     * A {@link PostMapping} endpoint for creating a {@link User} for the given {@link UserActionInput}.
     *
     * @param actionInput - The {@link UserActionInput} being processed.
     * @param model       - The {@link Model} being processed.
     * @return A {@link ResponseEntity} containing a {@link UserResponse}.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/authentication/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserActionInput actionInput, Model model) {
        User user = actionInput.getUser();
        if (user != null) {
            // Encode the Password to ensure raw plain text information is not shown to the user.
            user.setPassword(securityConfig.getPasswordEncoder().encode(user.getPassword()));
            UserOperationResponse userOperationResponse = userOperationService.createUser(user);
            // If successful
            if (userOperationResponse.isSuccessfulOperation()) {
                // Build UserResponse
                UserResponse userResponse = new UserResponse();
                model.addAttribute(USER_RESPONSE_ATTRIBUTE, userResponse);
                userResponse.setUser(userOperationResponse.getUser());
                // Obfuscate the password from response
                userResponse.getUser().setPassword("*********************");
                return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
            } else {
                // Error scenario
                return UserResponse.generateUserResponseError(ErrorCodes.ALREADY_EXISTS, ErrorCodes.ALREADY_EXISTS_DESCRIPTION, HttpStatus.NOT_FOUND);
            }
        }
        // Badly formed request.
        return UserResponse.generateUserResponseError(ErrorCodes.NOT_VALID_FORMAT, ErrorCodes.NOT_VALID_FORMAT_DESCRIPTION, HttpStatus.BAD_REQUEST);
    }
}
