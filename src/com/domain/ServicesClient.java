package com.domain;

import com.Persistence.AlimentRepository;
import com.Persistence.CardRepository;
import com.Persistence.MealTicketRepository;
import com.Persistence.VegetalRepository;
import com.domain.Card;
import com.domain.Cash;
import com.domain.MealTicket;
import com.domain.Receipt;

import java.util.*;

public class ServicesClient {
    VegetalRepository vegetalRepository = new VegetalRepository();
    MealTicketRepository mealTicketRepository = new MealTicketRepository();
    CardRepository cardRepository = new CardRepository();
    AlimentRepository alimentRepository = new AlimentRepository();
    static int receipt_nr = 0;
    Receipt receipt = new Receipt();

    ArrayList<Receipt> receipts = new ArrayList();
    static int idCash = 0;
    Scanner in = new Scanner(System.in);

    void addVegetalCart() {
        int id = 0;
        int quantity = 0;

        System.out.println("Write id: ");
        try {
            id = in.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Not a number!");
        }
        if (vegetalRepository.get(id) != null) {
            System.out.println("Write the quantity in kgs:");
            try {
                quantity = in.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Not a number!");
            }
            receipt.add(vegetalRepository.get(id), quantity);

        } else {
            System.out.println("Vegetal with given id doesn't exit!");
        }


    }

    void addAlimentCart()  {
        int id = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Write id: ");
        try {
            id = in.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Not a number!");
        }
        if (vegetalRepository.get(id) != null) {
            receipt.add(vegetalRepository.get(id));

        } else {
            System.out.println("Aliment with given id doesn't exit!");
        }


    }


    void payCash() {
        if (receipt.getPrice() == 0)
            System.out.println("Your cart is empty!");
        else {
            System.out.println("To pay: " + receipt.getPrice());
            idCash++;
            Cash payment = new Cash(idCash, receipt.getPrice());
            receipt.setPaymentMethod(payment);
            System.out.println("\nSuccesful transaction. Freeing cart!\n");
            receipt.paymentMethod = payment;
            receipts.add(receipt);
            receipt = new Receipt();
            receipt_nr++;
        }

    }

    void payCard() {
        if (receipt.getPrice() == 0)
            System.out.println("Your cart is empty!");
        else {
            System.out.println("Price: " + receipt.getPrice());
            Card payment = new Card();
            payment.read(receipt.getPrice());
            System.out.println("\nSuccesful transaction. Freeing cart!.\n");
            receipt.setPaymentMethod(payment);
            receipts.add(receipt);
            receipt_nr++;
        }
        mainMenu();
    }

    void payTickets() {
        if (receipt.getPrice() == 0)
            System.out.println("Your cart is empty!");
        else {
            System.out.println("Price:" + receipt.getPrice());
            MealTicket payment = new MealTicket();
            payment.read(receipt.getPrice());
            System.out.println("\nSuccesful transaction. Freeing cart!\n");
            receipts.add(receipt);
            receipt = new Receipt();
            receipt_nr++;
        }
        mainMenu();

    }

    void seeCart() {
        System.out.println("----------------CART---------------\n");
        for (Object object : receipt.cart)
            System.out.println(object);
        System.out.println("\n");
        mainMenu();

    }

    void seeReceipts() {
        for (Receipt receipt : receipts) {
            System.out.println("----------new receipt-----------");
            receipt.show();
        }

    }


    void SecondMenu() {
        System.out.println("Is it a vegetal or a non-vegetal" +
                "\n1.Vegetal" + "\n2.Non-vegetal" + "\n3.Exit");
    }
    void showVegetals(){
        vegetalRepository.show();
    }
    void showAliments(){
        vegetalRepository.show();
    }
    void mainMenu(){
        int choice=0;
        System.out.println("List of Vegetals:");
        vegetalRepository.show();
        System.out.print("\nList of non-vegetals:\n");
        alimentRepository.show();
        System.out.println("1.Add product to cart\n2.See cart\n3.Pay\n4.Exit");
        try {
            choice=in.nextInt();
        }
        catch (Exception e){
            System.out.println(e);
        }
        if (choice==1)
            addMenu();
        if (choice==2)
            seeCart();
        if (choice==3)
            payMenu();
        if (choice==4){
            return;
        }
        if (choice>4)
            System.out.println("Invalid choice!");
            mainMenu();
    }
    void addMenu(){
        int choice=0;
        System.out.println("Purchase vegetal or non-vegetal?\n1.Vegetal\n2.Non-Vegetal\n3.exit");
        try {
            choice=in.nextInt();
        }
        catch (Exception e){
            System.out.println(e);
        }
        if (choice==1)
            try {
                addVegetalCart();
            }catch(Exception e){
                System.out.println(e);
            }
        if (choice==2)
            addAlimentCart();
        if (choice>4)
            addMenu();

    }
    void payMenu(){
        int choice=0;
        System.out.println("Pay cash, card or with meal tickets?\n1.Cash\n2.Card\n3.Meal Tickets\n4.Exit");
        try {
            choice=in.nextInt();
        }
        catch (Exception e){
            System.out.println(e);
        }
        if (choice==1)
            payCash();
        if (choice==2)
            payCard();
        if (choice==3)
            payTickets();
        if (choice>4)
            System.out.println("Invalid choice!");
            mainMenu();
    }
}





