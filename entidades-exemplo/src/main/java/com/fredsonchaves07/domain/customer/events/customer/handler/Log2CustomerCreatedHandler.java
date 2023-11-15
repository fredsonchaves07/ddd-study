package com.fredsonchaves07.domain.customer.events.customer.handler;

import com.fredsonchaves07.domain.Event;
import com.fredsonchaves07.domain.EventHandler;
import com.fredsonchaves07.domain.customer.events.customer.CustomerCreatedEvent;

public class Log2CustomerCreatedHandler implements EventHandler<CustomerCreatedEvent> {

    @Override
    public void handler(Event event) {
        System.out.println("Segundo log do evento CustomerCreated " + event.eventData());
    }
}
