package com.domain;

public class PaymentMethod {

    private double price;
    private int id;

    PaymentMethod() {
    }

    PaymentMethod(int id, double price) {
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return "";
    }

    public double getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String s = "Payment Method:";
        return s;
    }


}
