package com.fredsonchaves07.entity;

public class OrderItem {

    private String id;

    private String name;

    private double price;

    private String productId;

    private int quantity;

    public OrderItem(String id, String name, double price, String productId, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price * quantity;
    }
}
