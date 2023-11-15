package com.fredsonchaves07.domain.events.customer.handler;

import com.fredsonchaves07.domain.events.Event;
import com.fredsonchaves07.domain.events.EventHandler;
import com.fredsonchaves07.domain.events.customer.CustomerCreatedEvent;

public class Log2CustomerCreatedHandler implements EventHandler<CustomerCreatedEvent> {

    @Override
    public void handler(Event event) {
        System.out.println("Segundo log do evento CustomerCreated " + event.eventData());
    }
}
