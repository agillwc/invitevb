package com.github.rnbr.invitevb.models;

import java.util.Objects;

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
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        return Objects.equals(this.username, other.username);
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