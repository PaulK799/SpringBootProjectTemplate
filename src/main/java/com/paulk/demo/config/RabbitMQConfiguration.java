package com.paulk.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines a set of {@link Exchange} for the Entry API.
 */
@Configuration
public class RabbitMQConfiguration {
    /**
     * Creates an {@link TopicExchange} for the Add Entry interaction.
     *
     * @return The {@link TopicExchange} to be created.
     */
    @Bean
    public Exchange getEntryAddExchange() {
        return new TopicExchange("entryAddExchange");
    }

    /**
     * Creates a durable {@link Queue} for the Add Entry interaction.
     *
     * @return The {@link Queue} to be created.
     */
    @Bean
    public Queue getAddEntryQueue() {
        return new Queue("addEntryQueue", true);
    }

    /**
     * Creates a {@link Binding} between the Add {@link Queue} and {@link Exchange}.
     *
     * @return The {@link Binding} to be generated.
     */
    @Bean
    public Binding getAddEntryBinding() {
        TopicExchange exchange = (TopicExchange) getEntryAddExchange();
        Queue queue = getAddEntryQueue();
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("entry.add");
    }

    /**
     * Creates an {@link TopicExchange} for the Delete Entry interaction.
     *
     * @return The {@link TopicExchange} to be created.
     */
    @Bean
    public Exchange getEntryDeleteExchange() {
        return new TopicExchange("entryDeleteExchange");
    }

    /**
     * Creates a durable {@link Queue} for the Delete Entry interaction.
     *
     * @return The {@link Queue} to be created.
     */
    @Bean
    public Queue getDeleteEntryQueue() {
        return new Queue("deleteEntryQueue", true);
    }

    /**
     * Creates a {@link Binding} between the Add {@link Queue} and {@link Exchange}.
     *
     * @return The {@link Binding} to be generated.
     */
    @Bean
    public Binding getDeleteEntryBinding() {
        TopicExchange exchange = (TopicExchange) getEntryDeleteExchange();
        Queue queue = getDeleteEntryQueue();
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("entry.delete");
    }

    /**
     * Creates an {@link TopicExchange} for the Update Entry interaction.
     *
     * @return The {@link TopicExchange} to be created.
     */
    @Bean
    public Exchange getEntryUpdateExchange() {
        return new TopicExchange("entryUpdateExchange");
    }

    /**
     * Creates a durable {@link Queue} for the Update Entry interaction.
     *
     * @return The {@link Queue} to be created.
     */
    @Bean
    public Queue getUpdateEntryQueue() {
        return new Queue("updateEntryQueue", true);
    }

    /**
     * Creates a {@link Binding} between the Add {@link Queue} and {@link Exchange}.
     *
     * @return The {@link Binding} to be generated.
     */
    @Bean
    public Binding getUpdateEntryBinding() {
        TopicExchange exchange = (TopicExchange) getEntryUpdateExchange();
        Queue queue = getUpdateEntryQueue();
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("entry.update");
    }
}
