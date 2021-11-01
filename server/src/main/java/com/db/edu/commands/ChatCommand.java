package com.db.edu.commands;

interface ChatCommand {
    void execute(Saver saver, Notifier notifier);
}
