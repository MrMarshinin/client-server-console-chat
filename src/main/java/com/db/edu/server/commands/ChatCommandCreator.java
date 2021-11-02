package com.db.edu.server.commands;

public interface ChatCommandCreator {
    ChatCommand create(String argument);
}
