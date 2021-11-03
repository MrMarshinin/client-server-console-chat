package com.db.edu.server.entity;

import com.db.edu.server.WrongNickException;

import java.io.DataOutputStream;

public class User {
    private final DataOutputStream out;
    private String room = "all";
    private String nick = "default";
    private final UserHandler handler;

    User(DataOutputStream stream, UserHandler handler) {
        this.out = stream;
        this.handler = handler;
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

    public void changeNick(String nick) throws WrongNickException {
        handler.changeNick(this, nick);
    }

    void setNick(String nick) {
        this.nick = nick;
    }
}
