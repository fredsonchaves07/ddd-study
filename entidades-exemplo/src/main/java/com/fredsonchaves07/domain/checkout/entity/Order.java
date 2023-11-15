package com.fredsonchaves07.domain.checkout.entity;

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
        this.total = items.stream().reduce(0, (subTotal, order) -> subTotal + order.getPrice(), Integer::sum);
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

    public String getId() {
        return id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getTotal() {
        return total;
    }
}
