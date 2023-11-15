package com.fredsonchaves07.domain.events.product;

import com.fredsonchaves07.domain.entity.Product;
import com.fredsonchaves07.domain.events.Event;

import java.time.LocalDateTime;

public class ProductCreatedEvent implements Event<Product> {

    private final Product productEventData;
    private final String eventName = "ProductCreatedEvent";
    private LocalDateTime dataTimeOcurred;

    public ProductCreatedEvent(Product productEventData) {
        this.productEventData = productEventData;
    }

    @Override
    public LocalDateTime dataTimeOcurred() {
        return dataTimeOcurred;
    }

    @Override
    public Product eventData() {
        dataTimeOcurred = LocalDateTime.now();
        return productEventData;
    }

    @Override
    public String getEventName() {
        return eventName;
    }
}