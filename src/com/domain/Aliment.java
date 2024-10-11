package com.domain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Aliment implements Comparable<Aliment> {
    private String name;
    private double price;
    private int id;

    public Aliment() {
    }

    public Aliment(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }


    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Aliment p) {
        if (this.getPrice() > p.getPrice())
            return 1;
        if (this.getPrice() < p.getPrice())
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        String s = "ID="+this.getId()+" Name="+this.getName() + "------>" + this.getPrice() + "Lei";
        return s;
    }
    public Aliment read(){
        System.out.println("Write aliment name:");
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
        try {
            System.out.println("Write price and id:");
            price = in.nextDouble();
            id=in.nextInt();
            return new Vegetal(id,name,price);
        } catch (InputMismatchException e) {
            System.err.println("You didn't type a number!");
        }
        return new Vegetal();
    }

}


