package com.db.edu.server.entity;

import java.time.LocalDateTime;

public class Message {
    private final String body;
    private final LocalDateTime dateTime;

    public Message(String body, LocalDateTime dateTime) {
        this.body = body;
        this.dateTime = dateTime;
    }

    public String toString(User user) {
        return dateTime.toString() + ", " + user.getNick() + ": " + body;
    }
}
