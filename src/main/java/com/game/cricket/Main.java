package com.game.cricket;

import com.game.cricket.doa.ClearData;
import com.game.cricket.models.Team;
import com.game.cricket.services.FinalBoard;
import com.game.cricket.util.RandomGenerator;

import java.util.Date;
import java.util.Scanner;

public class Main {


    // ClearData clearData=new ClearData();
    // clearData.clearAllData();
    // clearData.eraseData();
    // Scanner scan = new Scanner(System.in);
    // match.addAllTeams(scan);

    public static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {


        TeamFactory factory = new TeamFactory();

        ClearData clearData=new ClearData();
        clearData.eraseData();

//        //factory.addTeamsDatabase();
//
//
//        Team indian = factory.getIndianTeam();
//        Team aus = factory.getAustraliaTeam();
//        RandomGenerator randomGenerator = new RandomGenerator();
//
//
//        for(int i=11;i<13;++i){
//            indian.setTotal_score(0);
//            aus.setTotal_score(0);
//            indian.resetScores();
//            aus.resetScores();
//
//            Match match = new Match(String.valueOf(i), "Ind vs Aus", "Delhi", new Date());
//            if(randomGenerator.getRandomCoin()==0)
//                match.addTeams(indian, aus);
//            else
//                match.addTeams(aus,indian);
//            System.out.println(match);
//            match.start();
//            FinalBoard finalBoard = new FinalBoard(match);
//            finalBoard.result();
//            finalBoard.addDetails();
//
//            System.out.println(match);

        //}
    }

}
