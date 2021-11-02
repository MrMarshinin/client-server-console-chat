package com.db.edu.server.entity;

import java.time.LocalDateTime;

public class Message {
    private final String body;
    private final LocalDateTime dateTime;

    private String username;
    private String room;

    public Message(String body, LocalDateTime dateTime, String username, String room) {
        this.body = body;
        this.dateTime = dateTime;
        this.username = username;
        this.room = room;
    }

    public String toString() {
        return dateTime.toString() + ", " + room + ", " + username + ": " + body;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
