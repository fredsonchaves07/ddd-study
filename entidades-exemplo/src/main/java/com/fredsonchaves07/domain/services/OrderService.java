package com.fredsonchaves07.domain.services;

import com.fredsonchaves07.domain.entity.Customer;
import com.fredsonchaves07.domain.entity.Order;
import com.fredsonchaves07.domain.entity.OrderItem;

import java.util.List;
import java.util.UUID;

public class OrderService {

    public static int total(List<Order> orders) {
        return orders.stream().reduce(0, (subTotal, order) -> subTotal + order.getTotal(), Integer::sum);
    }

    public static Order placeOrder(Customer customer, List<OrderItem> itens) {
        if (itens.isEmpty()) {
            throw new Error("Oder must have at least one item");
        }
        Order order = new Order(UUID.randomUUID().toString(), customer.getId(), itens);
        customer.addRewardPoints(order.getTotal() / 2);
        return order;
    }
}
