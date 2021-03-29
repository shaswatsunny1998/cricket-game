package com.game.cricket.exceptions;

public class TeamSizeException extends Exception{
    public TeamSizeException(String message) {
        super(message);
    }

    public TeamSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
