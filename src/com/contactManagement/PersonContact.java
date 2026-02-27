package com.contactManagement;

public class PersonContact extends Contact {

    private String dateOfBirth;

    public PersonContact(String name, String dateOfBirth) {
        super(name);
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}