package com.game.cricket.exceptions;

public class PlayersIdException extends Exception{
    public PlayersIdException(String message) {
        super(message);
    }

    public PlayersIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
