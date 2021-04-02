package com.paulk.demo.controller;

import com.paulk.demo.config.DemoApplicationConfig;
import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.domain.model.EntriesResponse;
import com.paulk.demo.domain.model.Entry;
import com.paulk.demo.domain.model.EntryOperationResponse;
import com.paulk.demo.service.EntryActionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test the {@link EntriesController}.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EntriesControllerTest {

    @Mock
    private EntryActionService actionService;

    @Mock
    private DemoApplicationConfig demoApplicationConfig;

    @InjectMocks
    private EntriesController entriesController;

    private MockMvc mockMvc;
    private EntryActionInput entryActionInput;
    private Entry entry;

    @BeforeEach
    public void setupMocks() {
        // Sets the default view for the test.
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(entriesController)
                .build();

        entry = new Entry("testValue", "1234");
        entryActionInput = new EntryActionInput.EntryActionInputBuilder()
                .withKey("test")
                .withEntry(entry)
                .build();

        Mockito.when(demoApplicationConfig.getDefaultPageNumber()).thenReturn(0);
        Mockito.when(demoApplicationConfig.getDefaultPageNumber()).thenReturn(10);
    }

    /**
     * Validates the {@link EntriesController} for the Add {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void addEntrySuccess() throws Exception {
        EntryOperationResponse operationResponse = new EntryOperationResponse();
        operationResponse.setSuccessfulOperation(true);

        Mockito.when(actionService.addEntry(any(EntryActionInput.class))).thenReturn(operationResponse);

        this.mockMvc.perform(post("/entries/entry")
                .content(entryActionInput.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    /**
     * Validates the {@link EntriesController} for the Add {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void addEntryInvalid() throws Exception {
        EntryOperationResponse operationResponse = new EntryOperationResponse();
        Mockito.when(actionService.addEntry(any(EntryActionInput.class))).thenReturn(operationResponse);

        this.mockMvc.perform(post("/entries/entry")
                .content(entryActionInput.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Validates the {@link EntriesController} for the Add {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void addEntryBadRequest() throws Exception {
        this.mockMvc.perform(post("/entries/entry")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /**
     * Validates the {@link EntriesController} for the Delete {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void deleteEntrySuccess() throws Exception {

        EntryOperationResponse operationResponse = new EntryOperationResponse();
        operationResponse.setSuccessfulOperation(true);
        Mockito.when(actionService.deleteEntry(any(EntryActionInput.class))).thenReturn(operationResponse);

        this.mockMvc.perform(delete("/entries/entry")
                .content(entryActionInput.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Validates the {@link EntriesController} for the Delete {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void deleteEntryInvalid() throws Exception {
        EntryOperationResponse operationResponse = new EntryOperationResponse();
        Mockito.when(actionService.deleteEntry(any(EntryActionInput.class))).thenReturn(operationResponse);

        this.mockMvc.perform(delete("/entries/entry")
                .content(entryActionInput.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Validates the {@link EntriesController} for the Update {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void deleteEntryBadRequest() throws Exception {
        this.mockMvc.perform(delete("/entries/entry")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /**
     * Validates the {@link EntriesController} for the Update {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void updateEntrySuccess() throws Exception {

        EntryOperationResponse operationResponse = new EntryOperationResponse();
        operationResponse.setEntry(entry);
        operationResponse.setSuccessfulOperation(true);
        Mockito.when(actionService.updateEntry(any(EntryActionInput.class))).thenReturn(operationResponse);

        this.mockMvc.perform(put("/entries/entry")
                .content(entryActionInput.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Validates the {@link EntriesController} for the Update {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void updateEntryInvalid() throws Exception {
        EntryOperationResponse operationResponse = new EntryOperationResponse();
        Mockito.when(actionService.updateEntry(any(EntryActionInput.class))).thenReturn(operationResponse);

        this.mockMvc.perform(put("/entries/entry")
                .content(entryActionInput.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Validates the {@link EntriesController} for the Get {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void updateEntryBadRequest() throws Exception {
        this.mockMvc.perform(put("/entries/entry")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    /**
     * Validates the {@link EntriesController} for the Get {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void getEntrySuccess() throws Exception {
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        entryActionResponse.setSuccessfulOperation(true);
        entryActionResponse.setEntry(entry);
        Mockito.when(actionService.getEntry(any(EntryActionInput.class))).thenReturn(entryActionResponse);

        this.mockMvc.perform(get("/entries/entry/{value}/id/{id}", "test", "1234")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Validates the {@link EntriesController} for the Get {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void getEntryInvalid() throws Exception {
        EntryOperationResponse entryActionResponse = new EntryOperationResponse();
        Mockito.when(actionService.getEntry(any(EntryActionInput.class))).thenReturn(entryActionResponse);

        this.mockMvc.perform(get("/entries/entry/{value}/id/{id}", "test", "1234")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Validates the {@link EntriesController} for the Get All {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void getAllEntrySuccess() throws Exception {
        EntriesResponse entriesResponse = new EntriesResponse();
        entriesResponse.getEntries().add(entry);
        Mockito.when(actionService.getAllEntries()).thenReturn(entriesResponse);

        this.mockMvc.perform(get("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Validates the {@link EntriesController} for the Get All {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void getAllEntryInvalid() throws Exception {
        EntriesResponse entriesResponse = new EntriesResponse();
        Mockito.when(actionService.getAllEntries()).thenReturn(entriesResponse);

        this.mockMvc.perform(get("/entries")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
