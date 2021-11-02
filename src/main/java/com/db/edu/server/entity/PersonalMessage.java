package com.db.edu.server.entity;

import java.time.LocalDateTime;

public class PersonalMessage extends Message {
    private String usernameTo;

    public PersonalMessage(String body, LocalDateTime dateTime, String username, String room, String usernameTo) {
        super(body, dateTime, username, room);
        this.usernameTo = usernameTo;
    }

    @Override
    public String getDecoratedString() {
        return "Personally from " + this.usernameFrom + ": " + this.body;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }
}
