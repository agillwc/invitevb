package com.github.rnbr.invitevb.models;

import com.github.rnbr.invitevb.modes.SendMode;

public class Settings {
    
    private final User user;
    
    private final SendMode sendMode;
    private final String homePage;
    private final String message;
    private final int interval;

    public Settings(User user, SendMode sendMode, String homePage, String message, int interval) {
        this.user = user;
        this.sendMode = sendMode;
        this.homePage = homePage;
        this.message = message;
        this.interval = interval;
    }

    public User getUser() {
        return user;
    }

    public SendMode getSendMode() {
        return sendMode;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getMessage() {
        return message;
    }

    public int getInterval() {
        return interval;
    }
}