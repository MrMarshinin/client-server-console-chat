package com.db.edu.server.storage;

import com.db.edu.server.ConnectionHandler;
import com.db.edu.server.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader implements Reader {
    private String historyFileName = "history.txt";
    private File file;

    private static final Logger log = LoggerFactory.getLogger(CustomFileReader.class);

    @Override
    public List<String> read() {
        return readSpecificRoom("");
    }

    @Override
    public List<String> readSpecificRoom(String room) {
        List<String> result = new ArrayList<>();
        try {

            file = new File(historyFileName);
            checkExist(file);

            BufferedReader br = new BufferedReader(new FileReader(historyFileName));

            String line = br.readLine();

            while (line != null) {
                Message message;
                try {
                    message = new Message(line);
                } catch (IllegalArgumentException e) {
                    log.error("Invalid format for message");
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

    public ArrayList<?> checkExist(File file) throws IOException {
        if (!file.exists()){
            System.out.println("Create history file ...");
            file.createNewFile();
            log.info("Created history file.");
            return new ArrayList<>();
        }
        return null;
    }
}
