package com.outsourcebooster.cryptocurrency.web.config.security.model;

public class JwtAuthenticationRequest {

    private String email;
    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
