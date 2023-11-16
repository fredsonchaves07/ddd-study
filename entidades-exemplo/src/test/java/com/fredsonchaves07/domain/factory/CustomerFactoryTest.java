package com.fredsonchaves07.domain.factory;

import com.fredsonchaves07.domain.customer.entity.Address;
import com.fredsonchaves07.domain.customer.entity.Customer;
import com.fredsonchaves07.domain.customer.factory.CustomerFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerFactoryTest {

    @Test
    public void shouldCreateACustomer() {
        Customer customer = CustomerFactory.create("John");
        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals("John", customer.getName());
        assertNull(customer.getAddress());
    }

    @Test
    public void shouldCreateACustomerWithAnAddress() {
        Address address = new Address("01", 20, "65045600", "SP");
        Customer customer = CustomerFactory.createWithAddress("John", address);
        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals("John", customer.getName());
        assertEquals(address, customer.getAddress());
    }
}
