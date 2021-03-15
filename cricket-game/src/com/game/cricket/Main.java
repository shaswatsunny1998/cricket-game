package com.game.cricket;

import com.game.cricket.doa.ClearData;
import com.game.cricket.services.FinalBoard;

import java.util.Scanner;


public class Main {


    public static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {

        Match match = new Match("1234", "Ind vs Aus");
        ClearData clearData=new ClearData();
        clearData.eraseData();
        Scanner scan = new Scanner(System.in);
        match.addAllTeams(scan);
        System.out.println(match);
        match.start();
        FinalBoard finalBoard = new FinalBoard(match);
        finalBoard.result();
//        finalBoard.addTeams();
//        finalBoard.addMatch();
//
//
//        finalBoard.addTeamPlayers();
//        finalBoard.addPlayers();
//        finalBoard.addBattingScores();
//        finalBoard.addBowlerScores();
//        finalBoard.addOvers();
//        finalBoard.addWickets();
//        finalBoard.addBalls();
//        finalBoard.addScoreBoard();
        finalBoard.addDetails();

        System.out.println(match);
    }

}
