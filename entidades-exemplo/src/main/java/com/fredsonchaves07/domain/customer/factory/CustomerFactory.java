package com.fredsonchaves07.domain.customer.factory;

import com.fredsonchaves07.domain.customer.entity.Address;
import com.fredsonchaves07.domain.customer.entity.Customer;

import java.util.UUID;

public class CustomerFactory {

    public static Customer create(String name) {
        return new Customer(UUID.randomUUID().toString(), name);
    }

    public static Customer createWithAddress(String name, Address address) {
        Customer customer = create(name);
        customer.setAddress(address);
        return customer;
    }
}
