package com.game.cricket.exceptions;

public class MatchIdException extends Exception{
    public MatchIdException(String message) {
        super(message);
    }

    public MatchIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
