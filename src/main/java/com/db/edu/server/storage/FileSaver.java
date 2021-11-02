package com.db.edu.server.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileSaver implements Saver {
    Logger logger = LoggerFactory.getLogger(FileSaver.class);

    @Override
    public void save(String message){
        try {
            File file = new File("history.txt");
            checkExist(file);
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(message);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public void checkExist(File file) throws IOException {
        if(!file.exists()){
            if (file.createNewFile()) {
                throw new IOException("Cannot create file history.txt, but it doesn't exist");
            }
        }
    }
}
