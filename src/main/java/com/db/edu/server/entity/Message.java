package com.db.edu.server.entity;

import java.time.LocalDateTime;

public class Message {
    private String body;
    private LocalDateTime dateTime;

    public Message(String body, LocalDateTime dateTime) {
        this.body = body;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return dateTime.toString() + ", " + body;
    }
}
