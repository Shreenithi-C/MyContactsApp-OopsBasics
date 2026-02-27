package com.userManagement;

public class FreeUser extends User {

    public FreeUser(String email, String password, String name) {
        super(email, password, name);
    }

    @Override
    public String getUserType() {
        return "FREE";
    }
}