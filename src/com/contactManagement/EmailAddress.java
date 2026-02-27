package com.contactManagement;

public class EmailAddress {

    private String email;

    public EmailAddress(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}