package com.fredsonchaves07.infrastructure.repository;

import com.fredsonchaves07.domain.entity.Address;
import com.fredsonchaves07.domain.entity.Customer;
import com.fredsonchaves07.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRepositoryMemoryTest {

    @Test
    public void shouldCreateACustomer() {
        CustomerRepository customerRepository = new CustomerMemoryRepository();
        Customer customer = new Customer("1", "Cliente 1");
        customer.setAddress(new Address("1", 36, "6501400", "São Paulo"));
        customerRepository.create(customer);
        Customer customer1 = customerRepository.findById("1");
        assertEquals("1", customer1.getId());
        assertEquals("Cliente 1", customer1.getName());
        assertNotNull(customer1.getAddress());
        assertEquals("1", customer1.getAddress().rua());
        assertEquals(36, customer1.getAddress().numero());
        assertEquals("6501400", customer1.getAddress().cep());
        assertEquals("São Paulo", customer1.getAddress().cidade());
    }

    @Test
    public void shouldUpdateACustomer() {
        CustomerRepository customerRepository = new CustomerMemoryRepository();
        Customer customer = new Customer("1", "Cliente 1");
        customer.setAddress(new Address("1", 36, "6501400", "São Paulo"));
        customerRepository.create(customer);
        customer.setName("Cliente atualizado 1");
        customer.setAddress(new Address("2", 20, "6501401", "São Paulo"));
        customerRepository.update(customer);
        Customer customer1 = customerRepository.findById("1");
        assertEquals("1", customer1.getId());
        assertEquals("Cliente atualizado 1", customer1.getName());
        assertNotNull(customer1.getAddress());
        assertEquals("2", customer1.getAddress().rua());
        assertEquals(20, customer1.getAddress().numero());
        assertEquals("6501401", customer1.getAddress().cep());
        assertEquals("São Paulo", customer1.getAddress().cidade());
    }

    @Test
    public void shouldFindAllCustomers() {
        CustomerRepository customerRepository = new CustomerMemoryRepository();
        customerRepository.create(
                new Customer("1", "Cliente 1").setAddress(
                        new Address("1", 36, "6501400", "São Paulo")
                )
        );
        customerRepository.create(
                new Customer("2", "Cliente 2").setAddress(
                        new Address("2", 50, "6501401", "São Paulo")
                )
        );
        customerRepository.create(
                new Customer("3", "Cliente 3").setAddress(
                        new Address("3", 100, "6501402", "São Paulo")
                )
        );
        List<Customer> customers = customerRepository.findAll();
        assertFalse(customers.isEmpty());
        assertEquals(3, customers.size());
        assertEquals("1", customers.get(0).getId());
        assertEquals("Cliente 1", customers.get(0).getName());
        assertNotNull(customers.get(0).getAddress());
        assertEquals("1", customers.get(0).getAddress().rua());
        assertEquals(36, customers.get(0).getAddress().numero());
        assertEquals("6501400", customers.get(0).getAddress().cep());
        assertEquals("São Paulo", customers.get(0).getAddress().cidade());
        assertEquals("2", customers.get(1).getId());
        assertEquals("Cliente 2", customers.get(1).getName());
        assertNotNull(customers.get(1).getAddress());
        assertEquals("2", customers.get(1).getAddress().rua());
        assertEquals(50, customers.get(1).getAddress().numero());
        assertEquals("6501401", customers.get(1).getAddress().cep());
        assertEquals("São Paulo", customers.get(1).getAddress().cidade());
        assertEquals("3", customers.get(2).getId());
        assertEquals("Cliente 3", customers.get(2).getName());
        assertNotNull(customers.get(2).getAddress());
        assertEquals("3", customers.get(2).getAddress().rua());
        assertEquals(100, customers.get(2).getAddress().numero());
        assertEquals("6501402", customers.get(2).getAddress().cep());
        assertEquals("São Paulo", customers.get(2).getAddress().cidade());
    }
}
