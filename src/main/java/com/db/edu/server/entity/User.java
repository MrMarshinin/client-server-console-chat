package com.db.edu.server.entity;

import java.io.DataOutputStream;

public class User {
    private final DataOutputStream out;
    private String room = "all";
    private String nick = "default";
    private final UserFactory factory;

    User(DataOutputStream stream, UserFactory factory) {
        this.out = stream;
        this.factory = factory;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public DataOutputStream getOutput() {
        return out;
    }

    public String getNick() {
        return nick;
    }

    public void changeNick(String nick) throws IllegalArgumentException {
        factory.changeNick(this, nick);
    }

    void setNick(String nick) {
        this.nick = nick;
    }
}
