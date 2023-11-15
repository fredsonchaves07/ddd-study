package com.fredsonchaves07.domain.entity;

import com.fredsonchaves07.domain.customer.entity.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void shouldThrowErrorWhenIDIsEmpty() {
        Error error = assertThrows(Error.class, () -> new Customer("", "John"));
        assertNotNull(error);
        assertEquals("Id is required", error.getMessage());
    }

    @Test
    public void shouldThrowErrorWhenNameIsEmpty() {
        Error error = assertThrows(Error.class, () -> new Customer("123", ""));
        assertNotNull(error);
        assertEquals("Name is required", error.getMessage());
    }
}
