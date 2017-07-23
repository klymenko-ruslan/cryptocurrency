package com.outsourcebooster.cryptocurrency.web.config.security.model;

import java.io.Serializable;

public class JwtAuthenticationResponse {

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
