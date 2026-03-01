package com.contactManagement;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class ContactService {

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
    
}