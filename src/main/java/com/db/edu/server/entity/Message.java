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
    public Message(String line) {
        String[] arguments = line.split(": ");
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Invalid format for message.");
        }
        this.body = arguments[1];
        String[] metaInfo = arguments[0].split(", ");
        if (metaInfo.length != 3) {
            throw new IllegalArgumentException("Invalid meta info formant for message.");
        }
        this.dateTime = LocalDateTime.parse(metaInfo[0]);
        this.room = metaInfo[1];
        this.username = metaInfo[2];

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
    public String getRoom() {
        return room;
    }
}
