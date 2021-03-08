package com.game.cricket.models;

import java.util.List;

public class FinalBoard {

    private Team firstTeam;
    private Team secondTeam;


    public FinalBoard() {
    }

    public FinalBoard(Team firstTeam, Team secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public void result(){
        if (secondTeam.getTotal_score() > firstTeam.getTotal_score()) {
            System.out.println("Team 2 WON");
        } else {
            System.out.println("Team 1 WON");
        }
    }

    public int getRunsByFirstTeam(){
        return firstTeam.getTotal_score();
    }

    public int getRunsBySecondTeam(){
        return secondTeam.getTotal_score();
    }

    public int wicketDown(List<? extends Player> players )
    {
        return 0;

    }

    public int wicketsDownFirstTeam(){
        int wickets=0;
        return 0;

    }

    public int wicketDownsSecondTeam(){
        return  0;
    }

    public void getMatchDetails(){


    }



}
