package com.paulk.demo.controller;

import com.paulk.demo.constants.ErrorCodes;
import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.domain.model.EntryResponse;
import com.paulk.demo.domain.model.Error;
import com.paulk.demo.service.EntryActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Defines a {@link Controller}  which defines a set of CRUD Operations.
 * Defines the interactions with {@link Entry} objects in the data store defined.
 */
@Controller
public class EntriesController {

    @Autowired
    protected EntryActionService entryActionService;

    /**
     * Add an {@link Entry}.
     *
     * @param actionInput - The {@link EntryActionInput} defining the {@link RequestBody}.
     * @param model       - The {@link Model} to be processed.
     * @return A {@link ResponseEntity} containing an {@link EntryResponse}. If successful an {@link Entry} returned, otherwise an {@link Error}.
     */
    @PostMapping("/entries/entry")
    public ResponseEntity<EntryResponse> addEntry(@RequestBody EntryActionInput actionInput, Model model) {
        EntryResponse entryResponse = new EntryResponse();
        model.addAttribute("entryResponse", entryResponse);
        boolean isSuccess = entryActionService.addEntry(actionInput);

        if (isSuccess) {
            // Add the added Entry
            entryResponse.setEntry(actionInput.getEntry());
            return new ResponseEntity<>(entryResponse, HttpStatus.CREATED);
        } else {
            // Add in error to indicate Entry could not be added successfully.
            Error error = new Error();
            error.setCode(ErrorCodes.ALREADY_EXISTS);
            error.setDescription(ErrorCodes.ALREADY_EXISTS_DESCRIPTION);
            entryResponse.setError(error);
            return new ResponseEntity<>(entryResponse, HttpStatus.NOT_FOUND);
        }
    }
}
