package com.game.cricket;

import com.game.cricket.models.Batsman;

import java.util.Scanner;


public class Main {


    public static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {

        Match match = new Match("123", "Ind vs Aus");
        Scanner scan = new Scanner(System.in);

        match.addAllTeams(scan);
        System.out.println(match);
        match.start();
        System.out.println(match);


        /*
        RandomGenerator randomGenerator=new RandomGenerator();
        int value=randomGenerator.getRandomCoin();
        if(value==0){
            System.out.println(coin.HEADS);
        }
        else{
            System.out.println(coin.TAILS);
        }

         */


    }

}
