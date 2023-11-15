package com.fredsonchaves07.domain.events;

import com.fredsonchaves07.domain.entity.Customer;
import com.fredsonchaves07.domain.entity.Product;
import com.fredsonchaves07.domain.events.customer.CustomerCreatedEvent;
import com.fredsonchaves07.domain.events.customer.handler.Log1CustomerCreatedHandler;
import com.fredsonchaves07.domain.events.customer.handler.Log2CustomerCreatedHandler;
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

    @Test
    public void shouldNotifyAllEvent() {
        EventDispatcher<ProductCreatedEvent> eventDispatcher = new EventDispatcher<>();
        EventHandler<ProductCreatedEvent> eventHandler = new SendEmailWhenProductIsCreatedHandler();
        eventDispatcher.register("ProductCreatedEvent", eventHandler);
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(new Product("1", "Product 1", 100));
        eventDispatcher.notify(productCreatedEvent);
        assertNotNull(productCreatedEvent.dataTimeOcurred());
    }

    @Test
    public void shouldNotifyCustomerEvent() {
        EventDispatcher<CustomerCreatedEvent> eventDispatcher = new EventDispatcher<>();
        EventHandler<CustomerCreatedEvent> eventHandler1 = new Log1CustomerCreatedHandler();
        EventHandler<CustomerCreatedEvent> eventHandler2 = new Log2CustomerCreatedHandler();
        eventDispatcher.register("CustomerCreatedEvent", eventHandler1);
        eventDispatcher.register("CustomerCreatedEvent", eventHandler2);
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent(new Customer("1", "Customer 1"));
        eventDispatcher.notify(customerCreatedEvent);
        assertNotNull(customerCreatedEvent.dataTimeOcurred());
    }
}
