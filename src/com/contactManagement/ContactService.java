package com.contactManagement;

import java.util.Optional;

import com.searchAndFilter.SearchService;
import com.searchAndFilter.FilterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class ContactService implements SearchService,FilterService{

    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }
    
    public Optional<Contact> findContactById(String id) {

        for (Contact contact : contacts) {
            if (contact.getId().equals(id.trim())) {
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }
    
    public boolean updateContact(String id, Contact updatedContact) {

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                contacts.set(i, updatedContact);
                return true;
            }
        }
        return false;
    }

    //delete Contact
    public boolean deleteContact(String id) {

        Iterator<Contact> iterator = contacts.iterator();

        while (iterator.hasNext()) {
            Contact contact = iterator.next();

            if (contact.getId().equals(id)) {
                iterator.remove();   // safe removal
                return true;
            }
        }

        return false;
    }
    
    //bulk delete
    public void deleteContacts(List<String> ids) {

        Iterator<Contact> iterator = contacts.iterator();

        while (iterator.hasNext()) {
            Contact contact = iterator.next();

            if (ids.contains(contact.getId())) {
                iterator.remove();
            }
        }
    }
    
    //bulk tag
    public void addTagToContacts(List<String> ids, String tag) {

        for (Contact contact : contacts) {
            if (ids.contains(contact.getId())) {
                contact.addTag(tag);
            }
        }
    }
    
    //bulk export
    public void exportContacts(List<String> ids, String fileName) throws IOException {

        File folder = new File("exports");

        // create folder if it doesn't exist
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(folder, fileName);

        FileWriter writer = new FileWriter(file);

        for (Contact contact : contacts) {
            if (ids.contains(contact.getId())) {
                writer.write(contact.toString());
                writer.write("\n-------------------\n");
            }
        }

        writer.close();
    }
    
    //Search by name
    @Override
    public List<Contact> searchByName(String name) {

        List<Contact> results = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(contact);
            }
        }

        return results;
    }
    
    //search by phone
    @Override
    public List<Contact> searchByPhone(String phone) {

        List<Contact> results = new ArrayList<>();

        for (Contact contact : contacts) {

            for (PhoneNumber p : contact.getPhoneNumbers()) {

                if (p.getNumber().contains(phone)) {
                    results.add(contact);
                    break;
                }
            }
        }

        return results;
    }
    
    //search by email
    @Override
    public List<Contact> searchByEmail(String email) {

        List<Contact> results = new ArrayList<>();

        for (Contact contact : contacts) {

            for (EmailAddress e : contact.getEmailAddresses()) {

                if (e.getEmail().toLowerCase().contains(email.toLowerCase())) {
                    results.add(contact);
                    break;
                }
            }
        }

        return results;
    }
    
    //search by tag
    @Override
    public List<Contact> searchByTag(String tag) {

        List<Contact> results = new ArrayList<>();

        for (Contact contact : contacts) {

            for (String t : contact.getTags()) {

                if (t.equalsIgnoreCase(tag)) {
                    results.add(contact);
                    break;
                }
            }
        }

        return results;
    }
    
    //filter by tag
    @Override
    public List<Contact> filterByTag(String tag) {

        List<Contact> results = new ArrayList<>();

        for (Contact contact : contacts) {

            for (String t : contact.getTags()) {

                if (t.equalsIgnoreCase(tag)) {
                    results.add(contact);
                    break;
                }
            }
        }

        return results;
    }
    
    //filter by date added
    @Override
    public List<Contact> filterByDateAdded() {

        List<Contact> results = new ArrayList<>(contacts);

        Collections.sort(results, new Comparator<Contact>() {

            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getCreatedAt().compareTo(c2.getCreatedAt());
            }
        });

        return results;
    }
    
    //filter by frequently contacted 
    @Override
    public List<Contact> filterByFrequentlyContacted() {

        List<Contact> results = new ArrayList<>(contacts);

        Collections.sort(results, new Comparator<Contact>() {

            @Override
            public int compare(Contact c1, Contact c2) {
                return Integer.compare(c2.getContactCount(), c1.getContactCount());
            }
        });

        return results;
    }
    
    
}