package com.db.edu.server;

public class WrongNickException extends Exception{
    public WrongNickException() {
        super();
    }

    public WrongNickException(String message) {
        super(message);
    }

    public WrongNickException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongNickException(Throwable cause) {
        super(cause);
    }

    protected WrongNickException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
