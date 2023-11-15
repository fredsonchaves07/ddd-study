package com.fredsonchaves07.domain.events.customer;

import com.fredsonchaves07.domain.entity.Customer;
import com.fredsonchaves07.domain.events.Event;

import java.time.LocalDateTime;

public class CustomerCreatedEvent implements Event<Customer> {

    private final Customer customerEventData;

    private final String eventName = "CustomerCreatedEvent";

    private LocalDateTime dataTimeOcurred;

    public CustomerCreatedEvent(Customer customerEventData) {
        this.customerEventData = customerEventData;
    }

    @Override
    public LocalDateTime dataTimeOcurred() {
        return dataTimeOcurred;
    }

    @Override
    public Customer eventData() {
        dataTimeOcurred = LocalDateTime.now();
        return customerEventData;
    }

    @Override
    public String getEventName() {
        return eventName;
    }
}
