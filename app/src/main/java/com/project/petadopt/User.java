package com.project.petadopt;

public class User {

    private String uid, name, password,username;

    public User() {
    }

    public User(String uid, String name, String password, String username) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
