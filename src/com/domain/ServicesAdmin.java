package com.domain;

import com.Persistence.AlimentRepository;
import com.Persistence.CardRepository;
import com.Persistence.MealTicketRepository;
import com.Persistence.VegetalRepository;
import com.audit.Logger;
import com.domain.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServicesAdmin {
    Scanner in = new Scanner(System.in);
    boolean found = false;
    int choice = 0;
    Logger logger = new Logger();
    VegetalRepository vegetalRepository = new VegetalRepository();
    MealTicketRepository mealTicketRepository = new MealTicketRepository();
    CardRepository cardRepository = new CardRepository();
    AlimentRepository alimentRepository = new AlimentRepository();

    void findClient(ArrayList<Receipt> Receipts) {
        logger.update("findClient");
        String name;
        System.out.println("Write name:");
        name = in.nextLine();
        for (Receipt receipt : Receipts) {
            if (receipt.getPaymentMethod().getName().equalsIgnoreCase(name)) {
                receipt.show();
                found = true;
            }

        }
        if (found == false)
            System.out.println("Didn't find client.");
    }


    void clientsOverSum(ArrayList<Receipt> Receipts) {
        logger.update("clientsOverSum");
        double sum = 0;
        found = false;
        System.out.println("Write minimum sum:");
        try {
            sum = in.nextDouble();
        } catch (InputMismatchException e) {
            System.err.println("Not a number!");
        }
        for (Receipt receipt : Receipts) {
            if (receipt.getPrice() > sum) {
                receipt.show();
                found = true;
            }

        }
        if (found = false)
            System.out.println("There's no client with a sum larger than the requested sum.");
    }

    void mainMenu() {
        System.out.println("1.Add\n2.Update\n3.Delete\n4.Exit");
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (choice == 1)
            addMenu();
        if (choice == 2)
            updateMenu();
        if (choice == 3)
            deleteMenu();
        if (choice == 4)
            return;
        if (choice > 4)
            System.out.println("Invalid choice!");

    }

    void addMenu() {
        System.out.println("1.Add vegetal\n2.Add non-vegetal");
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (choice == 1) {
            Vegetal vegetal = new Vegetal();
            vegetal.read();
            vegetalRepository.add(vegetal);
            logger.update("vegetalRepositoryAdd");
        }
        if (choice == 2) {
            Aliment aliment = new Aliment();
            aliment.read();
            alimentRepository.add(aliment);
            logger.update("alimentRepositoryAdd");
        }
        if (choice == 3)
            return;

        if (choice > 3)
            System.out.println("Invalid choice!");

    }

    void updateMenu() {
        System.out.println("1.Update vegetal\n2.Update non-vegetal\n3.Update incorrect meal ticket payment\n4.Update incorrect card payment");
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (choice == 1) {
            Vegetal vegetal = new Vegetal();
            vegetal.read();
            vegetalRepository.update(vegetal);
            logger.update("vegetalRepositoryUpdate");
        }
        if (choice == 2) {
            Aliment aliment = new Aliment();
            aliment.read();
            alimentRepository.update(aliment);
            logger.update("alimentRepositoryUpdate");
        }
        if (choice == 3) {
            double price = 0;
            MealTicket mealTicket = new MealTicket();
            System.out.println("Enter price:");
            try {
                price = in.nextDouble();
            } catch (Exception e) {
                System.out.println(e);
            }
            mealTicket.read(price);
            mealTicketRepository.update(mealTicket);
            logger.update("mealTicketRepositoryUpdate");
        }
        if (choice == 4) {
            double price = 0;
            Card card = new Card();
            System.out.println("Enter price:");
            try {
                price = in.nextDouble();
            } catch (Exception e) {
                System.out.println(e);
            }
            card.read(price);
            cardRepository.update(card);
            logger.update("cardRepositoryUpdate");
        }
        if (choice == 5)
            return;
        if (choice > 5)
            System.out.println("Invalid choice!");
    }

    void deleteMenu() {
        System.out.println("1.Delete vegetal\n2.Delete non-vegetal\n3.Delete incorrect meal ticket payment\n4.Delete incorrect card payment");
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (choice == 1) {
            Vegetal vegetal = new Vegetal();
            vegetal.read();
            vegetalRepository.delete(vegetal);
            logger.update("vegetalRepositoryDelete");
        }
        if (choice == 2) {
            Aliment aliment = new Aliment();
            aliment.read();
            alimentRepository.delete(aliment);
            logger.update("alimentRepositoryDelete");
        }
        if (choice == 3) {
            double price = 0;
            MealTicket mealTicket = new MealTicket();
            System.out.println("Enter price:");
            try {
                price = in.nextDouble();
            } catch (Exception e) {
                System.out.println(e);
            }
            mealTicket.read(price);
            mealTicketRepository.delete(mealTicket);
            logger.update("mealTicketRepositoryDelete");

        }
        if (choice == 4) {
            Card card = new Card();
            double price = 0;
            System.out.println("Enter price:");
            try {
                price = in.nextDouble();
            } catch (Exception e) {
                System.out.println(e);
            }
            card.read(price);
            cardRepository.delete(card);
            logger.update("cardRepositoryDelete");
        }
        if (choice == 5)
            return;
        if (choice > 5)
            System.out.println("Invalid choice!");
    }
}