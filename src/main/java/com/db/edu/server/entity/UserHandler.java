package com.db.edu.server.entity;

import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

public class UserHandler {
    private List<User> users = new LinkedList<>();

    public User createUser(DataOutputStream stream) {
        User user = new User(stream, this);
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void changeNick(User user, String string) throws IllegalArgumentException {
        if (string.split(" ").length == 0) {
            throw new IllegalArgumentException("Nick cannot be empty");
        } else if (users.stream().anyMatch(u -> u.getNick().equals(string))) {
            throw new IllegalArgumentException("This nick already exists");
        } else if (string.split(" ").length > 1) {
            throw new IllegalArgumentException("Nick should be one word");
        } else {
            user.setNick(string);
        }
    }
}
