package com.github.rnbr.invitevb;

public class User {

    private final String name;
    private final String profile;

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