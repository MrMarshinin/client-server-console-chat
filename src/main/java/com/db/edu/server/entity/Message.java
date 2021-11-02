package com.db.edu.server.entity;

import java.time.LocalDateTime;

public abstract class Message {
    protected String body;
    protected LocalDateTime dateTime;
    protected String usernameFrom;
    protected String room;

    public abstract String getDecoratedString();

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getRoom() {
        return room;
    }

    protected Message(String body, LocalDateTime dateTime, String username, String room) {
        if (body.isEmpty()) {
            throw new IllegalArgumentException("Message can't be empty");
        }
        if (body.length() > 149) {
            throw new IllegalArgumentException("Message can't be longer than 150 symbols.");
        }
        this.body = body;
        this.dateTime = dateTime;
        this.usernameFrom = username;
        this.room = room;
    }
    protected Message() {}

}
