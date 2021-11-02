package com.db.edu.server.storage;

import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader implements Reader {
    private final String historyFileName;

    private static final Logger log = LoggerFactory.getLogger(CustomFileReader.class);

    public CustomFileReader() {
        historyFileName = "history.txt";
    }

    @Override
    public List<String> read() {
        return readSpecificRoom("");
    }

    @Override
    public List<String> readSpecificRoom(String room) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(historyFileName));) {

            File file = new File(historyFileName);
            checkExist(file);

            String line = br.readLine();

            while (line != null) {
                Message message = tryCreateMessage(line);
                if (message == null) {
                    line = br.readLine();
                    continue;
                }

                if (message.getRoom().equals(room)) {
                    result.add(line);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public Message tryCreateMessage(String line) {
        try {
            return new Message(line);
        } catch (IllegalArgumentException e) {
            log.error("Invalid format for message");
            return null;
        }
    }

    public void checkExist(File file) throws IOException {
        if (!file.exists()){
            Printer.print("Create history file ...");
            if (!file.createNewFile()) {
                throw new IOException("Cannot create file history.txt, but it doesn't exist");
            }
            log.info("Created history file.");
        }
    }
}
