package com.contactManagement;

public class PersonContact extends Contact {

    private String dateOfBirth;

    public PersonContact(String name, String dateOfBirth) {
        super(name);
        this.dateOfBirth = dateOfBirth;
    }
    
    public PersonContact(PersonContact other) {
        super(other);
        this.dateOfBirth = other.dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}