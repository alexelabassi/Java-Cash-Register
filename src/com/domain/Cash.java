package com.domain;

public class Cash extends PaymentMethod{
    private double price;
    String name = "A Platit Cash";

    Cash(int id,double price) {
        super(id,price);
    }

    @Override
    public String toString() {
        return "Payment Method: Cash. Price paid:"+this.price+".";
    }

}