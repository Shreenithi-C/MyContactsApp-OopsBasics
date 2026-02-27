package com.contactManagement;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

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
            if (contact.getId().equals(id)) {
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }
}