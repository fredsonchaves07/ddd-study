package com.fredsonchaves07.domain.events.product;

import com.fredsonchaves07.domain.entity.Product;
import com.fredsonchaves07.domain.events.Event;

import java.time.LocalDateTime;

public class ProductCreatedEvent implements Event<Product> {

    @Override
    public LocalDateTime dataTimeOcurred() {
        return null;
    }

    @Override
    public Product eventData() {
        return null;
    }
}