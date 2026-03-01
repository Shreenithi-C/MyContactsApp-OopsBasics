/*
@author developer
@version 11
*/

package com.main;

import java.util.*;
import com.userManagement.*;
import com.contactManagement.*;
import com.tagManagement.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        ContactService contactService = new ContactService();
        TagService tagService = new TagService();

        boolean running = true;

        while (running) {

            System.out.println("\n Contact Management System ");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Update Profile Name");
            System.out.println("4. Change Password");
            System.out.println("5. Create Contact");
            System.out.println("6. View Contact");
            System.out.println("7. Edit Contact");
            System.out.println("8. Delete Contact");
            System.out.println("9. Logout");
            System.out.println("10. Exit");
            System.out.println("11. Bulk Delete Contacts");
            System.out.println("12. Bulk Tag Contacts");
            System.out.println("13. Export Contacts");
            System.out.println("14. Search Contacts");
            System.out.println("15. Filter Contacts");
            System.out.println("16. Create Tag");
            System.out.println("17. View All Tags");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {

                switch (choice) {

                    // REGISTER 
                    case 1:{
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
                    }

                    // LOGIN 
                    case 2:{
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
                    }

                    // UPDATE NAME 
                    case 3:{
                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();

                        userService.updateName(newName);
                        System.out.println("Profile Updated Successfully!");
                        break;
                    }

                    // CHANGE PASSWORD 
                    case 4:{
                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Enter New Password: ");
                        String newPassword = scanner.nextLine();

                        userService.changePassword(newPassword);
                        System.out.println("Password Changed Successfully!");
                        break;
                    }

                    // CREATE CONTACT
                    case 5:{
                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Contact Type (PERSON/ORG): ");
                        String contactType = scanner.nextLine();

                        System.out.print("Enter Contact Name: ");
                        String contactName = scanner.nextLine();

                        Contact contact;

                        if (contactType.equalsIgnoreCase("ORG")) {

                            System.out.print("Enter Organization Name: ");
                            String orgName = scanner.nextLine();

                            contact = new OrganizationContact(contactName, orgName);

                        } else {

                            System.out.print("Enter Date of Birth: ");
                            String dob = scanner.nextLine();

                            contact = new PersonContact(contactName, dob);
                        }

                        System.out.print("Enter Phone Number: ");
                        String phone = scanner.nextLine();

                        contact.addPhoneNumber(new PhoneNumber(phone, "mobile"));

                        System.out.print("Enter Email: ");
                        String contactEmail = scanner.nextLine();

                        contact.addEmailAddress(new EmailAddress(contactEmail));

                        contactService.addContact(contact);

                        System.out.println("Contact Created Successfully!");
                        System.out.println("Contact ID: " + contact.getId());
                        break;
                	}
                        
                    // View Contact   
                    case 6:{

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Enter Contact ID: ");
                        String contactId = scanner.nextLine();

                        java.util.Optional<Contact> result = contactService.findContactById(contactId);

                        if (result.isPresent()) {
                        	Contact contact = result.get();
                            contact.incrementContactCount();
                            System.out.println(contact);
                        } else {
                            System.out.println("Contact not found.");
                        }

                        break;
                    }
                        
                    //Edit Contact 
                    case 7:{

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Enter Contact ID to edit: ");
                        String editId = scanner.nextLine();

                        Optional<Contact> Result =
                                contactService.findContactById(editId);

                        if (!Result.isPresent()) {
                            System.out.println("Contact not found.");
                            break;
                        }

                        Contact existing = Result.get();

                        System.out.print("Enter new name: ");
                        String newContactName = scanner.nextLine();

                        existing.setName(newContactName);

                        System.out.println("Contact Updated Successfully!");
                        break;
                    }
                    
                    //Delete Contact
                    case 8: {

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Enter Contact ID to delete: ");
                        String deleteId = scanner.nextLine();

                        Optional<Contact> deleteResult =
                                contactService.findContactById(deleteId);

                        if (!deleteResult.isPresent()) {
                            System.out.println("Contact not found.");
                            break;
                        }

                        System.out.print("Are you sure you want to delete? (yes/no): ");
                        String confirmation = scanner.nextLine();

                        if (confirmation.equalsIgnoreCase("yes")) {

                            boolean deleted = contactService.deleteContact(deleteId);

                            if (deleted) {
                                System.out.println("Contact Deleted Successfully.");
                            } else {
                                System.out.println("Deletion Failed.");
                            }

                        } else {
                            System.out.println("Deletion Cancelled.");
                        }

                        break;
                    }
                   
                    // LOGOUT
                    case 9:
                        userService.logout();
                        System.out.println("Logged Out Successfully!");
                        break;

                    // EXIT
                    case 10:
                        running = false;
                        System.out.println("Exiting Application...");
                        break;
                    
                    // Bulk Delete
                    case 11: {

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.println("Enter Contact IDs separated by comma:");
                        String input = scanner.nextLine();

                        String[] idArray = input.split(",");
                        List<String> ids = new ArrayList<>();

                        for (String id : idArray) {
                            ids.add(id.trim());
                        }

                        contactService.deleteContacts(ids);

                        System.out.println("Selected contacts deleted.");
                        break;
                    }
                    
                    //Bulk Tag
                    case 12: {

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.println("Enter Contact IDs separated by comma:");
                        String input = scanner.nextLine();

                        System.out.print("Enter tag: ");
                        String tag = scanner.nextLine();

                        String[] idArray = input.split(",");
                        List<String> ids = new ArrayList<>();

                        for (String id : idArray) {
                            ids.add(id.trim());
                        }

                        contactService.addTagToContacts(ids, tag);

                        System.out.println("Tag added to selected contacts.");
                        break;
                    }
                    
                    //Bulk export 
                    case 13: {

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.println("Enter Contact IDs separated by comma:");
                        String input = scanner.nextLine();

                        System.out.print("Enter file name (example: contacts.txt): ");
                        String fileName = scanner.nextLine();

                        String[] idArray = input.split(",");
                        List<String> ids = new ArrayList<>();

                        for (String id : idArray) {
                            ids.add(id.trim());
                        }

                        contactService.exportContacts(ids, fileName);

                        System.out.println("Contacts exported successfully.");
                        break;
                    }
                    
                    //Search
                    case 14: {

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.println("Search By:");
                        System.out.println("1. Name");
                        System.out.println("2. Phone");
                        System.out.println("3. Email");
                        System.out.println("4. Tag");

                        int searchChoice = Integer.parseInt(scanner.nextLine());

                        List<Contact> results = new ArrayList<>();

                        switch (searchChoice) {

                            case 1:
                                System.out.print("Enter name: ");
                                String name = scanner.nextLine();
                                results = contactService.searchByName(name);
                                break;

                            case 2:
                                System.out.print("Enter phone: ");
                                String phone = scanner.nextLine();
                                results = contactService.searchByPhone(phone);
                                break;

                            case 3:
                                System.out.print("Enter email: ");
                                String email = scanner.nextLine();
                                results = contactService.searchByEmail(email);
                                break;

                            case 4:
                                System.out.print("Enter tag: ");
                                String tag = scanner.nextLine();
                                results = contactService.searchByTag(tag);
                                break;

                            default:
                                System.out.println("Invalid option");
                        }

                        if (results.isEmpty()) {
                            System.out.println("No contacts found.");
                        } else {
                            for (Contact c : results) {
                                System.out.println(c);
                            }
                        }

                        break;
                    }
                    
                    //Filter
                    case 15: {

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.println("Filter Options:");
                        System.out.println("1. By Tag");
                        System.out.println("2. By Date Added");
                        System.out.println("3. Frequently Contacted");

                        int filterChoice = Integer.parseInt(scanner.nextLine());

                        List<Contact> results = new ArrayList<>();

                        switch (filterChoice) {

                            case 1:
                                System.out.print("Enter tag: ");
                                String tag = scanner.nextLine();
                                results = contactService.filterByTag(tag);
                                break;

                            case 2:
                                results = contactService.filterByDateAdded();
                                break;

                            case 3:
                                results = contactService.filterByFrequentlyContacted();
                                break;

                            default:
                                System.out.println("Invalid option");
                        }

                        if (results.isEmpty()) {
                            System.out.println("No contacts found.");
                        } else {
                            for (Contact c : results) {
                                System.out.println(c);
                            }
                        }

                        break;
                      }
                    
                    //create tag
                    case 16: {

                        if (!userService.isLoggedIn()) {
                            System.out.println("Please login first.");
                            break;
                        }

                        System.out.print("Enter tag name: ");
                        String tagName = scanner.nextLine();

                        tagService.createTag(tagName);

                        break;
                    }
                    
                    //view all tags
                    case 17: {

                        System.out.println("Available Tags:");

                        for (Tag tag : tagService.getAllTags()) {
                            System.out.println(tag);
                        }

                        break;
                    }
                    
                    default:
                        System.out.println("Invalid Option!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
