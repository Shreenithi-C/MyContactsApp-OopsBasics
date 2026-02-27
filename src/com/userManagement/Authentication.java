package com.userManagement;

import java.util.Optional;

public interface Authentication {
    Optional<User> login(String email, String password);
}