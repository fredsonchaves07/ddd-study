package com.fredsonchaves07.domain.customer.events.customer;

import com.fredsonchaves07.domain.Event;
import com.fredsonchaves07.domain.customer.entity.Customer;

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
