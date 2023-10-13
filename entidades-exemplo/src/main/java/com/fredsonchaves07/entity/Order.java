package com.fredsonchaves07.entity;

import java.util.List;

public class Order {

    private String id;

    private String customerId;

    List<OrderItem> orderItems;

    public Order(String id, String customerId, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.orderItems = items;
    }
}
