package com.fredsonchaves07.domain.events;

import com.fredsonchaves07.domain.events.product.ProductCreatedEvent;
import com.fredsonchaves07.domain.events.product.handler.SendEmailWhenProductIsCreatedHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventDispatcherTest {

    @Test
    public void shouldRegisterAnEventHandler() {
        EventDispatcher<ProductCreatedEvent> eventDispatcher = new EventDispatcher<>();
        EventHandler<ProductCreatedEvent> eventHandler = new SendEmailWhenProductIsCreatedHandler();
        eventDispatcher.register("ProductCreatedEvent", eventHandler);
        assertFalse(eventDispatcher.getEventHandlers().isEmpty());
        assertTrue(eventDispatcher.getEventHandlers().contains("ProductCreatedEvent"));
        assertEquals(1, eventDispatcher.getEventHandlers().size());
    }

    @Test
    public void shouldUnregisterEventHandler() {
        EventDispatcher<ProductCreatedEvent> eventDispatcher = new EventDispatcher<>();
        EventHandler<ProductCreatedEvent> eventHandler = new SendEmailWhenProductIsCreatedHandler();
        eventDispatcher.register("ProductCreatedEvent", eventHandler);
        eventDispatcher.unregister("ProductCreatedEvent");
        assertTrue(eventDispatcher.getEventHandlers().isEmpty());
        assertFalse(eventDispatcher.getEventHandlers().contains("ProductCreatedEvent"));
        assertEquals(0, eventDispatcher.getEventHandlers().size());
    }
}
