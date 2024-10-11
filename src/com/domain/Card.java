package com.domain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Card extends PaymentMethod {
    private String name;
    private Double price;

    public Card() {
    }

    Card(int id, Double price, String name) {
        super(id, price);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String s = "Name:" + this.getName() + ". Price paid: " + this.getPrice();
        return s;
    }
    public void read(double price) {
        System.out.println("Write name:");
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
        try {
            System.out.println("Write id: ");
            int id=in.nextInt();
            this.setId(id);
            this.setName(name);
            this.setPrice(price);
        } catch (InputMismatchException e) {
            System.err.println("You didn't type a number!");
        }
    }


}