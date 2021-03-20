package com.game.cricket;

import com.game.cricket.doa.ClearData;
import com.game.cricket.models.Team;
import com.game.cricket.services.FinalBoard;
import com.game.cricket.util.RandomGenerator;

import java.math.BigDecimal;
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



        //int total_overs = 10 / 6;
        //int rest_over = 10 % 6;
        System.out.println(4*0.1);
        String total_overs = String.valueOf(Math.round(3 / 6));
        String rest_over=String.valueOf(3%6);
        double numberOfOvers=Double.valueOf(total_overs+"."+rest_over);
        System.out.println(numberOfOvers);

        // for 3 balls we are getting 0.30000004 , using Big Decimal will decrease efficiency . What to do?
//        BigDecimal decimal = new BigDecimal(total_overs + 0.1 * rest_over).setScale(1, BigDecimal.ROUND_FLOOR);
//        System.out.println(decimal.floatValue());

//        TeamFactory factory = new TeamFactory();
//
//        ClearData clearData=new ClearData();
//        clearData.eraseData();

//        //factory.addTeamsDatabase();
//
//
//        Team indian = factory.getIndianTeam();
//        Team aus = factory.getAustraliaTeam();
//        RandomGenerator randomGenerator = new RandomGenerator();
//
//
//        for(int i=0;i<1;++i){
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
