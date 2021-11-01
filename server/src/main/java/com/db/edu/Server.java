package com.db.edu;

public class Server {
    public static void main(String[] args) {
        HistorySaver fileHistorySaver = new FileHistorySaver();
        fileHistorySaver.push("hello");
    }
}
