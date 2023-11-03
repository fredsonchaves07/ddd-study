package com.fredsonchaves07.domain.services;

import com.fredsonchaves07.domain.entity.Customer;
import com.fredsonchaves07.domain.entity.Order;
import com.fredsonchaves07.domain.entity.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    @Test
    public void shouldGetTotalOfAllOrders() {
        OrderItem orderItem1 = new OrderItem("1", "Item 1", 100, "p1", 1);
        OrderItem orderItem2 = new OrderItem("2", "Item 2", 200, "p2", 2);
        Order order1 = new Order("1", "c1", List.of(orderItem1));
        Order order2 = new Order("2", "c1", List.of(orderItem2));
        int total = OrderService.total(List.of(order1, order2));
        assertEquals(500, total);
    }

    @Test
    public void shouldPlaceAnOrder() {
        Customer customer = new Customer("c1", "Customer 1");
        OrderItem item1 = new OrderItem("i1", "Item 1", 10, "p1", 1);
        Order order = OrderService.placeOrder(customer, List.of(item1));
        assertEquals(5, customer.getRewardsPoints());
        assertEquals(10, order.getTotal());
    }
}
