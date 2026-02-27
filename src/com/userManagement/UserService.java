package com.userManagement;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<>();

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
}