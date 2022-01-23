package dev.imy.kimaradi.config;

import java.security.Principal;

public class AuthUser implements Principal {

    private String name;

    public AuthUser(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
