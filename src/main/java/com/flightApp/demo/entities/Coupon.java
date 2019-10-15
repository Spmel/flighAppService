package com.flightApp.demo.entities;

public class Coupon {
    private String id;
    private double discountPercentage;

    public Coupon(String id, double discountPercentage) {
        this.id = id;
        this.discountPercentage = discountPercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
