package com.domain;


import com.audit.Logger;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice = 0;
        Logger logger=new Logger();

        while (choice != 3) {
            System.out.println("Are you an admin or a client?\n1.Admin \n2.Client\n3.Exit");
            Scanner in = new Scanner(System.in);
            try {
                choice = in.nextInt();
            } catch (Exception e) {
                System.out.println(e);
            }
            if (choice == 1) {
                ServicesAdmin servicesAdmin = new ServicesAdmin();
                logger.update("servicesAdmin");
                servicesAdmin.mainMenu();
            }
            if (choice == 2) {
                ServicesClient servicesClient = new ServicesClient();
                logger.update("servicesClient");
                servicesClient.mainMenu();
            }

        }
        System.out.println("Exit.");


    }

}



