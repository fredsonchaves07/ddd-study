package com.fredsonchaves07.entity;

import java.util.List;

public class Order {

    private String id;

    private String customerId;

    private int total;

    List<OrderItem> orderItems;

    public Order(String id, String customerId, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.orderItems = items;
        validate();
    }

    private void validate() {
        if (this.customerId == null || this.customerId.isBlank()) {
            throw new Error("Name is required");
        }
        if (this.id.isEmpty()) {
            throw new Error("Id is required");
        }
        if (this.orderItems == null || this.orderItems.size() == 0) {
            throw new Error("Ordem itens is required");
        }
    }
}
