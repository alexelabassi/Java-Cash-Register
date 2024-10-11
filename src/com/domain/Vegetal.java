package com.domain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Vegetal extends Aliment {
    String name;
    Double price;
    int id;
    int quantity;
    public Vegetal() {
    }

    public Vegetal(int id,String name, double price) {
        super(id,name,price);
        this.id=id;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }

    @Override
    public String toString() {
        return "ID="+this.getId()+" Name="+this.getName() + "------>" + this.getPrice()+ "Lei" + "/Kg";
    }
    public Vegetal read(){
        System.out.println("Write vegetal name:");
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
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.setPrice(this.quantity*this.getPrice());
    }
    public int getQuantity(){
        return this.quantity;
    }



}