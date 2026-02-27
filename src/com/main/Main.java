/*
@author developer
@version 1
*/

package com.main;

import java.util.Scanner;
import com.userManagement.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        while (true) {

            System.out.println("\n--- UC-01: User Registration ---");
            System.out.println("1. Register");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine();

                        System.out.print("User Type (FREE/PREMIUM): ");
                        String type = scanner.nextLine();

                        User user;
                        if (type.equalsIgnoreCase("PREMIUM")) {
                            user = new PremiumUser(email, password, name);
                        } else {
                            user = new FreeUser(email, password, name);
                        }

                        userService.register(user);
                        System.out.println("Registration Successful!");
                        break;

                    case 2:
                        scanner.close();
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
