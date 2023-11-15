package com.fredsonchaves07.domain.product.events.product.handler;

import com.fredsonchaves07.domain.Event;
import com.fredsonchaves07.domain.EventHandler;
import com.fredsonchaves07.domain.product.events.product.ProductCreatedEvent;

public class SendEmailWhenProductIsCreatedHandler implements EventHandler<ProductCreatedEvent> {

    @Override
    public void handler(Event event) {
        System.out.println("Sending email to " + event.eventData());
    }
}
