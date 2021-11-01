package com.db.edu;

import java.io.DataOutputStream;

public class User {
    private final DataOutputStream out;
    private String room = "all";

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
}
