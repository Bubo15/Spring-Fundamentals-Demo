package com.shop.dealershop.service.model;

import com.shop.dealershop.data.entities.Role;

import java.util.Set;

public class UserLoginServiceModel {
    private String username;
    private String password;
    private Set<Role> roles;


    public UserLoginServiceModel() {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
