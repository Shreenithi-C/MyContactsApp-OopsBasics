package com.contactManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Contact {

    private String id;
    private String name;
    private List<PhoneNumber> phoneNumbers;
    private List<EmailAddress> emailAddresses;
    private LocalDateTime createdAt;

    public Contact(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Contact name cannot be empty");
        }
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        this.emailAddresses = new ArrayList<>();
        
    }
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    public void addPhoneNumber(PhoneNumber phone) {
        phoneNumbers.add(phone);
    }

    public void addEmailAddress(EmailAddress email) {
        emailAddresses.add(email);
    }
    
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("\n===== Contact Details =====\n");
        sb.append(String.format("ID: %s\n", id));
        sb.append(String.format("Name: %s\n", name));
        sb.append(String.format("Created At: %s\n", createdAt));

        sb.append("Phone Numbers:\n");
        for (PhoneNumber phone : phoneNumbers) {
            sb.append(String.format(" - %s (%s)\n",
                    phone.getNumber(), phone.getType()));
        }

        sb.append("Email Addresses:\n");
        for (EmailAddress email : emailAddresses) {
            sb.append(String.format(" - %s\n", email.getEmail()));
        }

        return sb.toString();
    }
}
