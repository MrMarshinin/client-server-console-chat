package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.storage.Reader;
import com.db.edu.storage.Saver;

import java.util.List;

public class GetHistoryCommand implements ChatCommand {
    @Override
    public void execute(Saver saver, Reader reader, Notifier notifier) {
        System.out.println("Execute history command");
        List<String> messages = reader.read();
        messages.forEach(notifier::sendMessage);
    }
}
