package com.paulk.demo.controller;

import com.paulk.demo.comparator.EntryComparator;
import com.paulk.demo.config.DemoApplicationConfig;
import com.paulk.demo.constants.ErrorCodes;
import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.domain.model.EntriesResponse;
import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.domain.model.EntryOperationResponse;
import com.paulk.demo.domain.model.EntryResponse;
import com.paulk.demo.domain.model.Error;
import com.paulk.demo.service.EntryActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * Defines a {@link Controller}  which defines a set of CRUD Operations.
 * Defines the interactions with {@link Entry} objects in the data store defined.
 */
@Controller
public class EntriesController {
    private static final String ENTRY_RESPONSE_ATTRIBUTE = "entityResponse";
    private static final String STRING_EMPTY = "";

    @Autowired
    protected EntryActionService entryActionService;

    @Autowired
    protected DemoApplicationConfig demoApplicationConfig;


    /**
     * Exception handler for {@link HttpMessageNotReadableException} when the {@link RequestBody} cannot be read.
     *
     * @return a {@link EntryResponse} containing a human readable {@link Error}.
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<EntryResponse> handleException() {
        return EntryResponse.generateEntryResponseError(ErrorCodes.NOT_VALID_FORMAT, ErrorCodes.NOT_VALID_FORMAT_DESCRIPTION, HttpStatus.BAD_REQUEST);
    }

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
        model.addAttribute(ENTRY_RESPONSE_ATTRIBUTE, entryResponse);

        Optional<Entry> entryOptional = Optional.ofNullable(actionInput)
                .map(EntryActionInput::getEntry);
        if (entryOptional.isPresent()) {
            EntryOperationResponse operationResponse = entryActionService.addEntry(actionInput);

            if (operationResponse.isSuccessfulOperation()) {
                // Add the added Entry
                entryResponse.setEntry(actionInput.getEntry());
                return new ResponseEntity<>(entryResponse, HttpStatus.CREATED);
            } else {
                // Add in error to indicate Entry could not be added successfully.
                return EntryResponse.generateEntryResponseError(ErrorCodes.ALREADY_EXISTS, ErrorCodes.ALREADY_EXISTS_DESCRIPTION, HttpStatus.NOT_FOUND);
            }
        } else {
            // Entry not submitted in a valid format.
            return EntryResponse.generateEntryResponseError(ErrorCodes.NOT_VALID_FORMAT, ErrorCodes.NOT_VALID_FORMAT_DESCRIPTION, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete an {@link Entry}.
     *
     * @param actionInput - The {@link EntryActionInput} defining the {@link RequestBody}.
     * @param model       - The {@link Model} to be processed.
     * @return A {@link ResponseEntity} containing an {@link EntryResponse}. If successful an {@link Entry} returned, otherwise an {@link Error}.
     */
    @DeleteMapping("/entries/entry")
    public ResponseEntity<EntryResponse> deleteEntry(@RequestBody EntryActionInput actionInput, Model model) {
        EntryResponse entryResponse = new EntryResponse();
        model.addAttribute(ENTRY_RESPONSE_ATTRIBUTE, entryResponse);

        Optional<Entry> entryOptional = Optional.ofNullable(actionInput)
                .map(EntryActionInput::getEntry);
        if (entryOptional.isPresent()) {
            EntryOperationResponse operationResponse = entryActionService.deleteEntry(actionInput);

            if (operationResponse.isSuccessfulOperation()) {
                // Delete the Entry
                entryResponse.setEntry(operationResponse.getEntry());
                return new ResponseEntity<>(entryResponse, HttpStatus.OK);
            } else {
                // Add in error to indicate Entry could not be added successfully.
                return EntryResponse.generateEntryResponseError(ErrorCodes.NOT_FOUND, ErrorCodes.NOT_FOUND_DESCRIPTION, HttpStatus.NOT_FOUND);
            }
        } else {
            // Entry not submitted in a valid format.
            return EntryResponse.generateEntryResponseError(ErrorCodes.NOT_VALID_FORMAT, ErrorCodes.NOT_VALID_FORMAT_DESCRIPTION, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update an {@link Entry}.
     *
     * @param actionInput - The {@link EntryActionInput} defining the {@link RequestBody}.
     * @param model       - The {@link Model} to be processed.
     * @return A {@link ResponseEntity} containing an {@link EntryResponse}. If successful an {@link Entry} returned, otherwise an {@link Error}.
     */
    @PutMapping("/entries/entry")
    public ResponseEntity<EntryResponse> updateEntry(@RequestBody EntryActionInput actionInput, Model model) {
        // Check Conditions.
        Optional<Entry> entryOptional = Optional.ofNullable(actionInput)
                .map(EntryActionInput::getEntry);
        Optional<String> keyOptional = Optional.ofNullable(actionInput)
                .map(EntryActionInput::getKey);

        if (entryOptional.isPresent() && keyOptional.isPresent()) {
            EntryResponse entryResponse = new EntryResponse();
            model.addAttribute(ENTRY_RESPONSE_ATTRIBUTE, entryResponse);
            EntryOperationResponse operationResponse = entryActionService.updateEntry(actionInput);

            if (operationResponse.isSuccessfulOperation()) {
                // Add the added Entry
                entryResponse.setEntry(operationResponse.getEntry());
                return new ResponseEntity<>(entryResponse, HttpStatus.OK);
            } else {
                // Add in error to indicate Entry could not be added successfully.
                return EntryResponse.generateEntryResponseError(ErrorCodes.NOT_FOUND, ErrorCodes.NOT_FOUND_DESCRIPTION, HttpStatus.NOT_FOUND);
            }
        } else {
            // Entry not submitted in a valid format.
            return EntryResponse.generateEntryResponseError(ErrorCodes.NOT_VALID_FORMAT, ErrorCodes.NOT_VALID_FORMAT_DESCRIPTION, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get a specific {@link Entry} given the Value and id.
     *
     * @param value - The {@link String} value to be processed.
     * @param id    - The {@link String} id to be processed.
     * @param model - The {@link Model} for processing.
     * @return A {@link ResponseEntity} containing an {@link EntryResponse}. If successful an {@link Entry} returned, otherwise an {@link Error}.
     */
    @GetMapping("/entries/entry/{value}/id/{id}")
    public ResponseEntity<EntryResponse> getEntry(@PathVariable String value, @PathVariable String id, Model model) {
        if (value != null && !value.equals(STRING_EMPTY) && id != null && !id.equals(STRING_EMPTY)) {
            // Setup
            EntryResponse entryResponse = new EntryResponse();
            model.addAttribute(ENTRY_RESPONSE_ATTRIBUTE, entryResponse);
            Entry entry = new Entry(value, id);
            EntryActionInput actionInput = new EntryActionInput.EntryActionInputBuilder()
                    .withEntry(entry)
                    .build();

            // Perform Action
            EntryOperationResponse operationResponse = entryActionService.getEntry(actionInput);
            if (operationResponse.isSuccessfulOperation()) {
                entryResponse.setEntry(operationResponse.getEntry());
                return new ResponseEntity<>(entryResponse, HttpStatus.OK);
            } else {
                return EntryResponse.generateEntryResponseError(ErrorCodes.NOT_FOUND, ErrorCodes.NOT_FOUND_DESCRIPTION, HttpStatus.NOT_FOUND);
            }

        } else {
            return EntryResponse.generateEntryResponseError(ErrorCodes.NOT_VALID_FORMAT, ErrorCodes.NOT_VALID_FORMAT_DESCRIPTION, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Gets all {@link Entry}.
     *
     * @param model - The {@link Model} for processing.
     * @return A {@link ResponseEntity} containing an {@link EntriesResponse}. If successful an {@link Entry} returned, otherwise an {@link Error}.
     */
    @GetMapping("/entries")
    public ResponseEntity<EntriesResponse> getEntriesPaging(@RequestParam(required = false) Integer pageNumber,
                                                            @RequestParam(required = false) Integer pageSize, Model model) {
        // Setup
        EntriesResponse entriesResponse = new EntriesResponse();
        model.addAttribute(ENTRY_RESPONSE_ATTRIBUTE, entriesResponse);
        List<Entry> entries = entriesResponse.getEntries();
        if (pageNumber == null && pageSize == null) {
            entriesResponse = entryActionService.getAllEntries();
        } else {
            // Default Page Number
            if (pageNumber == null) {
                pageNumber = demoApplicationConfig.getDefaultPageNumber();
            }

            // Default pageSize.
            if (pageSize == null) {
                pageSize = demoApplicationConfig.getDefaultPageSize();
            }

            entriesResponse = entryActionService.getAllEntries(pageNumber, pageSize);
        }

        // Sort with Comparator.
        entries.sort(new EntryComparator());

        if (!entriesResponse.getEntries().isEmpty()) {
            return new ResponseEntity<>(entriesResponse, HttpStatus.OK);
        } else {
            return EntriesResponse.generateEntryResponseError(ErrorCodes.NOT_FOUND, ErrorCodes.NOT_FOUND_DESCRIPTION, HttpStatus.NOT_FOUND);
        }
    }
}
