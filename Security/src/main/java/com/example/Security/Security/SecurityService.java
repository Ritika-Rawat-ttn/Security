package com.example.Security.Security;

public interface SecurityService {

    default boolean login(String userName, String password) {
        return false;
    }
}
