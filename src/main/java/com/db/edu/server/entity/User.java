package com.db.edu.server.entity;

import java.io.DataOutputStream;

public class User {
    private final DataOutputStream out;
    private String room = "all";
    private String nick = "";

    public User(DataOutputStream stream) {
        this.out = stream;
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

    public void setNick(String nick) {
        this.nick = nick;
    }
}
