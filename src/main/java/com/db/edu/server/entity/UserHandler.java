package com.db.edu.server.entity;

import com.db.edu.server.WrongNickException;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserHandler {
    private final List<User> users = Collections.synchronizedList(new ArrayList<>(1000));

    public User createUser(DataOutputStream stream) {
        User user = new User(stream, this);
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void changeNick(User user, String string) throws WrongNickException {
        if (string.split(" ").length == 0) {
            throw new WrongNickException("Nick cannot be empty");
        } else if (users.stream().anyMatch(u -> u.getNick().equals(string))) {
            throw new WrongNickException("This nick already exists");
        } else if (string.split(" ").length > 1) {
            throw new WrongNickException("Nick should be one word");
        } else {
            user.setNick(string);
        }
    }
}
