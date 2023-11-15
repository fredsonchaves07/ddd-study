package com.fredsonchaves07.infrastructure.repository;


import com.fredsonchaves07.domain.checkout.entity.Order;
import com.fredsonchaves07.domain.checkout.entity.OrderItem;
import com.fredsonchaves07.domain.checkout.repository.OrderRepository;
import com.fredsonchaves07.domain.customer.entity.Address;
import com.fredsonchaves07.domain.customer.entity.Customer;
import com.fredsonchaves07.domain.customer.repository.CustomerRepository;
import com.fredsonchaves07.domain.product.entity.Product;
import com.fredsonchaves07.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryMemoryTest {

    @BeforeAll
    public static void beforeAll() {
        CustomerRepository customerMemoryRepository = new CustomerMemoryRepository();
        customerMemoryRepository.create(
                new Customer("123", "Jhon").setAddress(new Address("10", 1, "65078", "SP"))
        );
        ProductRepository productRepository = new ProductMemoryRepository();
        productRepository.create(new Product("123", "Product 1", 100));
    }

    @Test
    public void shouldCreateAOrder() {
        OrderItem orderItem = new OrderItem("1", "Product 1", 100, "123", 2);
        Order order = new Order("123", "123", List.of(orderItem));
        OrderRepository orderRepository = new OrderMemoryRepository();
        orderRepository.create(order);
        Order order1 = orderRepository.findById("123");
        assertEquals(order1.getId(), "123");
        assertEquals(order1.getTotal(), 200);
        assertEquals(order1.getCustomerId(), "123");
        assertFalse(order1.getOrderItems().isEmpty());
        assertEquals(order1.getOrderItems().get(0).getId(), "1");
        assertEquals(order1.getOrderItems().get(0).getName(), "Product 1");
        assertEquals(order1.getOrderItems().get(0).getPrice(), 200);
        assertEquals(order1.getOrderItems().get(0).getProductId(), "123");
        assertEquals(order1.getOrderItems().get(0).getQuantity(), 2);
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
