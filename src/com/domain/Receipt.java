package com.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    LocalDateTime date = LocalDateTime.now(); // Create a date object
    double price;
    PaymentMethod paymentMethod;
    static int id = 0;
    int quantity;
    List<Aliment> cart = new ArrayList<>();

    Receipt() {
    }

    Receipt(double price, PaymentMethod paymentMethod) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }
    public PaymentMethod getPaymentMethod(){
        return this.paymentMethod;
    }
    void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getPrice() {
        return this.price;
    }

    public void add(Aliment aliment) {
        cart.add(aliment);
        this.price += aliment.getPrice();
    }

    public void add(Vegetal vegetal,int quantity) {
        Vegetal copy=new Vegetal();
        copy=vegetal;
        copy.setQuantity(quantity);
        cart.add(copy);
        this.price+=copy.getPrice();
    }


    public List<Aliment> getCart(){
        return this.cart;
    }
    public void show() {
        for (Aliment aliment : cart) {
            System.out.println(aliment);
        }
        System.out.println("\nTotal Price: " + this.price + ". Date:" + this.date);

    }
}
