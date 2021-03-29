package com.game.cricket.exceptions;

public class TeamInformationException extends Exception{
    public TeamInformationException(String message) {
        super(message);
    }

    public TeamInformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
