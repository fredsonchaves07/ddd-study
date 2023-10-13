package com.fredsonchaves07.entity;

import java.math.BigDecimal;

public class OrderItem {

    private String id;

    private String name;

    private double price;

    public OrderItem(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
