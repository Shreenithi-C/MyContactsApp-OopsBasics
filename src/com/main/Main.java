/*
@author developer
@version 3
*/

package com.main;

import java.util.Scanner;
import com.userManagement.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        while (true) {

            System.out.println("\n--- UC-03: Profile Management ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Name");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
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

                        User user = type.equalsIgnoreCase("PREMIUM")
                                ? new PremiumUser(email, password, name)
                                : new FreeUser(email, password, name);

                        userService.register(user);
                        System.out.println("Registration Successful!");
                        break;

                    case 2:
                        System.out.print("Enter Email: ");
                        String loginEmail = scanner.nextLine();

                        System.out.print("Enter Password: ");
                        String loginPassword = scanner.nextLine();

                        if (userService.login(loginEmail, loginPassword)) {
                            System.out.println("Login Successful!");
                        } else {
                            System.out.println("Invalid Credentials!");
                        }
                        break;

                    case 3:
                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();

                        userService.updateName(newName);
                        System.out.println("Name Updated!");
                        break;

                    case 4:
                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Enter New Password: ");
                        String newPassword = scanner.nextLine();

                        userService.changePassword(newPassword);
                        System.out.println("Password Updated!");
                        break;

                    case 5:
                        userService.logout();
                        System.out.println("Logged Out Successfully!");
                        break;

                    case 6:
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
