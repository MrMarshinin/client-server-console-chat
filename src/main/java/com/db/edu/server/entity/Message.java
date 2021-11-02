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
        String[] arguments = line.split(", ");
        if (arguments.length == 1) {
            throw new IllegalArgumentException("Invalid format for message.");
        }
        this.dateTime = LocalDateTime.parse(arguments[0]);
        this.body = arguments[1];
        this.room = "";
        this.username = "";
        if (arguments.length > 2) {
            this.room = arguments[2];
        }
        if (arguments.length > 3) {
            this.username = arguments[3];
        }
    }

    public String getDecoratedString() {
        StringBuilder decoratedString = new StringBuilder("");
        decoratedString.append(dateTime.toString());
        if (!room.isEmpty() && !room.equals("all")) {
            decoratedString.append(", " + room);
        }
        if (!username.isEmpty()) {
            decoratedString.append(", " + username + ": " + body);
        } else {
            decoratedString.append(", " + body);
        }
        return decoratedString.toString();
    }
    public String toString() {
        return dateTime.toString() + ", " + body + ", " + room + ", " + username;
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
