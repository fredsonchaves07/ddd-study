package com.fredsonchaves07.domain.customer.entity;

public class Customer {

    private String id;

    private String name;

    private Address address;

    private boolean isActive;

    private int rewardsPoints;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.isActive = false;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public Customer setAddress(Address address) {
        this.address = address;
        return this;
    }

    public void activate() {
        this.isActive = true;
    }

    public void addRewardPoints(int points) {
        rewardsPoints += points;
    }

    public int getRewardsPoints() {
        return rewardsPoints;
    }
}
