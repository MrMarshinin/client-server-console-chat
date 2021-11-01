package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.Saver;

public class GetHistoryCommand implements ChatCommand {
    @Override
    public void execute(Saver saver, Notifier notifier) {
        System.out.println("Execute history command");
        notifier.sendErrorMessage("Not implemented yet");
    }
}
