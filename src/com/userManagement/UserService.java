package com.userManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private List<User> users = new ArrayList<>();
    private SessionManager sessionManager = new SessionManager();
    
    // Registration
    public void register(User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("Email already registered");
            }
        }
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    // Login
    public boolean login(String email, String password) {

        Authentication auth = new BasicAuth(users);
        Optional<User> user = auth.login(email, password);

        if (user.isPresent()) {
            sessionManager.createSession(user.get());
            return true;
        }

        return false;
    }

    public void logout() {
        sessionManager.logout();
    }

    public User getLoggedInUser() {
        return sessionManager.getLoggedInUser();
    }

    public boolean isLoggedIn() {
        return sessionManager.isLoggedIn();
    }
}