package com.db.edu.server;

import java.time.LocalDateTime;

public class Message {
    String body;
    LocalDateTime dateTime;

    public Message(String body, LocalDateTime dateTime) {
        this.body = body;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return dateTime.toString() + ", " + body;
    }
}
