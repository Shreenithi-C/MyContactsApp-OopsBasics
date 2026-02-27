package com.userManagement;

public class PremiumUser extends User {

    public PremiumUser(String email, String password, String name) {
        super(email, password, name);
    }

    @Override
    public String getUserType() {
        return "PREMIUM";
    }
}