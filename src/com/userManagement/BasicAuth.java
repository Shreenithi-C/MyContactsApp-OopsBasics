package com.userManagement;

import java.util.List;
import java.util.Optional;

public class BasicAuth implements Authentication {

    private List<User> users;

    public BasicAuth(List<User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> login(String email, String password) {

        for (User user : users) {
            if (user.getEmail().equals(email)
                    && user.checkPassword(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}