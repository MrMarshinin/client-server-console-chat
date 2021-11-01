package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.UserConnection;
import com.db.edu.storage.Saver;

public class GetHistoryCommand implements ChatCommand {
    @Override
    public void execute(UserConnection user, Saver saver, Notifier notifier) {
        System.out.println("Execute history command");
        //notifier.sendErrorMessage("Not implemented yet");
    }
}
