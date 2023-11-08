package com.spring_login_register.model;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.Size;

public class User {
//    @NotNull(message = "is required")
    private int id;
    @Size(min = 1, message = "is required")
private String name;
private String email;
private String password;
private String gender;
private String[] knownLanguages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getKnownLanguages() {
        return knownLanguages;
    }

    public void setKnownLanguages(String[] knownLanguages) {
        this.knownLanguages = knownLanguages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
