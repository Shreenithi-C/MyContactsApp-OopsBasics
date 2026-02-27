package com.contactManagement;

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
}