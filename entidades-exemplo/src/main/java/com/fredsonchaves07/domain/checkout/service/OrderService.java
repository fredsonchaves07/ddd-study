package com.fredsonchaves07.domain.checkout.service;

import com.fredsonchaves07.domain.checkout.entity.Order;
import com.fredsonchaves07.domain.checkout.entity.OrderItem;
import com.fredsonchaves07.domain.customer.entity.Customer;

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
