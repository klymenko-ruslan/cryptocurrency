package com.outsourcebooster.cryptocurrency.web.model;

import java.util.Collection;

/**
 * Created by rklimemnko on 29.05.2016.
 */
public class User {
    private String username;
    private String password;
    private Collection<Role> roles;

    public User() {}

    public User(String username, String password, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
