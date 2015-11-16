package com.github.rnbr.invitevb;

public class User {

    private String name;
    private String profile;

    public User(String name, String profile) {
        this.name = name;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public String getProfile() {
        return profile;
    }
}