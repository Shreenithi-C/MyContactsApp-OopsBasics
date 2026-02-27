package com.userManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private List<User> users = new ArrayList<>();
    private SessionManager sessionManager = new SessionManager();

    // UC-01: Registration
    public void register(User user) {
        for (User u : users) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("Email already registered");
            }
        }
        users.add(user);
    }

    // UC-02: Login
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

    public boolean isLoggedIn() {
        return sessionManager.isLoggedIn();
}

    public User getLoggedInUser() {
        return sessionManager.getLoggedInUser();
    }

    // UC-03: Profile Management
    public void updateName(String newName) {

        if (!sessionManager.isLoggedIn()) {
            throw new IllegalStateException("User not logged in");
        }

        sessionManager.getLoggedInUser().setName(newName);
    }

    public void changePassword(String newPassword) {

        if (!sessionManager.isLoggedIn()) {
            throw new IllegalStateException("User not logged in");
        }

        sessionManager.getLoggedInUser().changePassword(newPassword);
    }
}