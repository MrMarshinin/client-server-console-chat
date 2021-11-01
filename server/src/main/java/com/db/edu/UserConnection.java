package com.db.edu;

import java.io.DataOutputStream;

public class UserConnection {
    private String room;
    private final DataOutputStream out;

    public UserConnection(DataOutputStream out) {
        this.out = out;
        this.room = "all";
    }

    public DataOutputStream getConnection() {
        return out;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String string) {
        this.room = string;
    }
}
