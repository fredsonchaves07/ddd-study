package com.fredsonchaves07.domain.events.product.handler;

import com.fredsonchaves07.domain.events.Event;
import com.fredsonchaves07.domain.events.EventHandler;
import com.fredsonchaves07.domain.events.product.ProductCreatedEvent;

public class SendEmailWhenProductIsCreatedHandler implements EventHandler<ProductCreatedEvent> {

    @Override
    public void handler(Event event) {
        System.out.println("Sending email to " + event.eventData());
    }
}
