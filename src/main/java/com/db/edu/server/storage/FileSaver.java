package com.db.edu.server.storage;

import java.io.*;

public class FileSaver implements Saver {

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
        }
    }

    public void checkExist(File file) throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
    }
}
