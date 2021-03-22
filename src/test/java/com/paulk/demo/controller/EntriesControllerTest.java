package com.paulk.demo.controller;

import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.domain.model.Entry;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        entry = new Entry("testValue");
        entryActionInput = new EntryActionInput.EntryActionInputBuilder()
                .withKey("test")
                .withEntry(entry)
                .build();
    }

    /**
     * Validates the {@link EntriesController} for the Add {@link Entry} endpoint.
     *
     * @throws Exception default exception handling.
     */
    @Test
    public void addEntrySuccess() throws Exception {

        Mockito.when(actionService.addEntry(any(EntryActionInput.class))).thenReturn(true);

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

        Mockito.when(actionService.addEntry(any(EntryActionInput.class))).thenReturn(false);

        this.mockMvc.perform(post("/entries/entry")
                .content(entryActionInput.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
