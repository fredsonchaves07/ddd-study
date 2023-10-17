package com.fredsonchaves07.entity;

public class Product {

    private String id;

    private String name;

    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.validate();
    }

    private void validate() {
        if (this.name == null || this.name.isBlank()) {
            throw new Error("Name is required");
        }
        if (this.id.isEmpty()) {
            throw new Error("Id is required");
        }
    }

    public double getPrice() {
        return price;
    }

    public void changePrice(double price) {
        this.price = price;
    }
}
