package com.db.edu.server.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileSaver implements Saver {
    private Logger logger = LoggerFactory.getLogger(FileSaver.class);

    @Override
    public void save(String message){
        File file = new File("history.txt");
        try (
                FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            checkExist(file);

            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void checkExist(File file) throws IOException {
        if(!file.exists() && file.createNewFile()){
            throw new IOException("Cannot create file history.txt, but it doesn't exist");
        }
    }
}
