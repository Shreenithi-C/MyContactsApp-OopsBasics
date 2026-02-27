package com.contactManagement;

public class PhoneNumber {

    private String number;
    private String type; // mobile, home, work

    public PhoneNumber(String number, String type) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }
}