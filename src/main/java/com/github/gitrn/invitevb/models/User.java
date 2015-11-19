package com.github.gitrn.invitevb.models;

public class User {
    
    /**
     * Nome de usuário.
     */
    private final String username;
    
    /**
     * Link do perfil.
     */
    private final String profileUrl;

    public User(String username, String profileUrl) {
        this.username = username;
        this.profileUrl = profileUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getUsername() {
        return username;
    }
}