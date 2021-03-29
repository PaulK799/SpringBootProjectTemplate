package com.paulk.demo.service;

import com.paulk.demo.domain.input.EntryActionInput;
import com.paulk.demo.utils.ObjectMapperInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * A {@link Service} for implementing {@link RabbitListener} receivers.
 */
@Service
public class EntryConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntryConsumerService.class);

    @Autowired
    protected EntryActionService entryActionService;

    /**
     * A {@link RabbitListener} which processes {@link EntryActionInput} received on the Update {@link Queue}.
     *
     * @param actionInput - The {@link EntryActionInput} received.
     */
    @RabbitListener(queues = "addEntryQueue")
    public void receivedAddEntry(LinkedHashMap actionInput) {
        LOGGER.info("Received Add '{}'", actionInput);
        try {
            EntryActionInput convertedActionInput = ObjectMapperInstance.INSTANCE.getObjectMapper().convertValue(actionInput, EntryActionInput.class);
            entryActionService.addEntry(convertedActionInput);
        } catch (IllegalArgumentException exception) {
            LOGGER.error("Error processing Delete Entry. Unable to convert to EntryActionInput.");
        }
    }


    /**
     * A {@link RabbitListener} which processes {@link EntryActionInput} received on the Delete {@link Queue}.
     *
     * @param actionInput - The {@link EntryActionInput} received.
     */
    @RabbitListener(queues = "deleteEntryQueue")
    public void receivedDeleteEntry(LinkedHashMap actionInput) {
        LOGGER.info("Received Delete '{}'", actionInput);
        try {
            EntryActionInput convertedActionInput = ObjectMapperInstance.INSTANCE.getObjectMapper().convertValue(actionInput, EntryActionInput.class);
            entryActionService.deleteEntry(convertedActionInput);
        } catch (IllegalArgumentException exception) {
            LOGGER.error("Error processing Delete Entry. Unable to convert to EntryActionInput.");
        }
    }

    /**
     * A {@link RabbitListener} which processes {@link EntryActionInput} received on the Update {@link Queue}.
     *
     * @param actionInput - The {@link EntryActionInput} received.
     */
    @RabbitListener(queues = "updateEntryQueue")
    public void receivedUpdateEntry(LinkedHashMap actionInput) {
        LOGGER.info("Received Update '{}'", actionInput);
        try {
            EntryActionInput convertedActionInput = ObjectMapperInstance.INSTANCE.getObjectMapper().convertValue(actionInput, EntryActionInput.class);
            entryActionService.updateEntry(convertedActionInput);
        } catch (IllegalArgumentException exception) {
            LOGGER.error("Error processing Update Entry. Unable to convert to EntryActionInput.");
        }
    }
}
