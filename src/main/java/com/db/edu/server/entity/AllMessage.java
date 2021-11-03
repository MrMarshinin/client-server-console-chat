package com.db.edu.server.entity;

import java.time.LocalDateTime;

public class AllMessage extends Message {
    public AllMessage(String body, LocalDateTime dateTime, String username, String room) {
        super(body, dateTime, username, room);
    }

    public AllMessage(String line) {
        super();
        String[] arguments = line.split(", ");
        if (arguments.length == 1) {
            throw new IllegalArgumentException("Invalid format for message.");
        }

        this.dateTime = LocalDateTime.parse(arguments[0]);
        this.body = arguments[1];
        this.room = "";
        this.usernameFrom = "";
        if (arguments.length > 2) {
            this.room = arguments[2];
        }
        if (arguments.length > 3) {
            this.usernameFrom = arguments[3];
        }

    }

    public String getDecoratedString() {
        StringBuilder decoratedString = new StringBuilder();
        decoratedString.append(dateTime.toString());
        if (!room.isEmpty() && !room.equals("all")) {
            decoratedString.append(", ").append(room);
        }
        if (!usernameFrom.isEmpty()) {
            decoratedString.append(", ").append(usernameFrom).append(": ").append(body);
        } else {
            decoratedString.append(", ").append(body);
        }
        return decoratedString.toString();
    }

    public String toString() {
        return dateTime.toString() + ", " + body + ", " + room + ", " + usernameFrom;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AllMessage)) {
            return false;
        }
        AllMessage msg = (AllMessage) obj;
        return this.body.equals(msg.body) &&
                this.room.equals(msg.room) &&
                this.usernameFrom.equals(msg.usernameFrom) &&
                this.dateTime.equals(msg.dateTime);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
