package com.game.cricket;

import com.game.cricket.services.FinalBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        FinalBoard finalBoard = new FinalBoard();
        finalBoard.addMatch();
    }
}
