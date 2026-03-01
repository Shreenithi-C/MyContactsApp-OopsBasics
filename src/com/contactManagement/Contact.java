package com.contactManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;
import com.tagManagement.*;

public abstract class Contact {

    private String id;
    private String name;
    private List<PhoneNumber> phoneNumbers;
    private List<EmailAddress> emailAddresses;
    private LocalDateTime createdAt;
    private int contactCount=0;
    private Set<Tag> tags = new HashSet<>();
    
    public Contact(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Contact name cannot be empty");
        }
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        this.emailAddresses = new ArrayList<>();
        this.tags = new HashSet<>();
    }
    
    //deep copy
    public Contact(Contact other) {
        this.id = other.id;
        this.name = other.name;
        this.createdAt = other.createdAt;

        this.phoneNumbers = new ArrayList<>();
        for (PhoneNumber p : other.phoneNumbers) {
            this.phoneNumbers.add(new PhoneNumber(p.getNumber(), p.getType()));
        }

        this.emailAddresses = new ArrayList<>();
        for (EmailAddress e : other.emailAddresses) {
            this.emailAddresses.add(new EmailAddress(e.getEmail()));
        }
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
    
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
    
    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = new ArrayList<>(phoneNumbers); 
    }

    public void setEmailAddresses(List<EmailAddress> emailAddresses) {
        this.emailAddresses = new ArrayList<>(emailAddresses); 
    }
    
    public void incrementContactCount() {
        contactCount++;
    }

    public int getContactCount() {
        return contactCount;
    }
    
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
