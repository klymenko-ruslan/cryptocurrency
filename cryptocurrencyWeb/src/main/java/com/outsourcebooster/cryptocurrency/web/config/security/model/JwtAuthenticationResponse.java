package com.outsourcebooster.cryptocurrency.web.config.security.model;

import java.io.Serializable;

import com.outsourcebooster.cryptocurrency.web.model.user.User;

public class JwtAuthenticationResponse {

    private final String token;
    private final User user;

    public JwtAuthenticationResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }

    public User getUser() { return this.user;}
}
