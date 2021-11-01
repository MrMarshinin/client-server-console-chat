package com.db.edu.commands;

public interface ChatCommandCreator {
    ChatCommand create(String argument);
}
