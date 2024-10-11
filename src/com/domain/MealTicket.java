package com.domain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MealTicket extends PaymentMethod {
    String name, CNP;
    Double price;

    public MealTicket() {
    }

    MealTicket(int id, Double price, String name, String CNP) {
        super(id, price);
        this.name = name;
        this.CNP = CNP;
    }

    public String getCNP() {
        return this.CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return "Name:" + this.getName() + " CNP: " + this.getCNP() + " Price paid:" + this.getPrice() + ".";
    }

    public void read(double price) {
        System.out.println("Write name:");
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
        try {
            System.out.println("Write Id: ");
            int id = in.nextInt();
            System.out.println("Write CNP:");
            CNP = in.nextLine();
            if (CNP.length() != 12)
                System.out.println("INVALID CNP!");
            else {
                this.setId(id);
                this.setName(name);
                this.setCNP(CNP);
                this.setPrice(price);
            }
        } catch (InputMismatchException e) {
            System.err.println("You didn't type a number!");
        }
    }

}
