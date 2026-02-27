package com.userManagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public abstract class User {

    private String email;
    private String passwordHash;
    private String name;

    public User(String email, String password, String name) {
        validateEmail(email);
        validatePassword(password);

        this.email = email;
        this.passwordHash = hashPassword(password);
        this.name = name;
    }

    public abstract String getUserType();

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    protected void validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(regex, email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    protected void validatePassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error");
        }
    }
    
    public boolean checkPassword(String password) {
        return this.passwordHash.equals(hashPassword(password));
    }
}