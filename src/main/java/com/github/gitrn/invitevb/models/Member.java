package com.github.gitrn.invitevb.models;

public class Member {
    
    private final String username;
    private final String profileUrl;

    public Member(String username, String profileUrl) {
        this.username = username;
        this.profileUrl = profileUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("Membro: ")
            .append(username)
            .append(" - Link do perfil: ")
            .append(profileUrl)
            .toString();
    }
}