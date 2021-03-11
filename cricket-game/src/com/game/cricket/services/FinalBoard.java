package com.game.cricket.services;

import com.game.cricket.Match;
import com.game.cricket.doa.*;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;

public class FinalBoard {
    private Match match;
    private Team firstTeam;
    private Team secondTeam;


    public FinalBoard() {
    }


    public FinalBoard(Match match) {
        this.match = match;
        initializeTeams();
    }

    private void initializeTeams() {
        this.firstTeam = this.match.getTeam1();
        this.secondTeam = this.match.getTeam2();
    }

    public void result() {
        if (secondTeam.getTotal_score() > firstTeam.getTotal_score()) {
            System.out.println("Team 2 WON");
        } else {
            System.out.println("Team 1 WON");
        }
    }

    public int getRunsByFirstTeam() {
        return firstTeam.getTotal_score();
    }

    public int getRunsBySecondTeam() {
        return secondTeam.getTotal_score();
    }

    public int totalWicketsDown() {
        return wicketsDownFirstTeam() + wicketsDownSecondTeam();
    }

    public int wicketsDown(Team team) {
        int wickets = 0;
        for (Player player : team.getPlayers()) {
            if (player.getScore().isOut())
                wickets += 1;
        }
        return wickets;
    }


    public int wicketsDownFirstTeam() {
        return wicketsDown(this.firstTeam);
    }

    public int wicketsDownSecondTeam() {
        return wicketsDown(this.secondTeam);
    }


    public void addMatch(){
        MatchDoa doa = new MatchDoa();
        doa.addMatch(this.match.getMatchId(),this.match.getMatchName());
    }

    public void addPlayers(){
        PlayersDoa doa = new PlayersDoa();
        doa.addPlayers(this.firstTeam,this.secondTeam);
    }

    public void addTeams(){
        TeamDoa doa = new TeamDoa();
        doa.addTeams(this.match);
    }
    public void addBattingScores(){
        BatScoreDoa doa = new BatScoreDoa();
        doa.addBattingScores(this.match);
    }

    public void addBowlerScores(){
        BallScoreDoa doa = new BallScoreDoa();
        doa.addBowlingScores(this.match);
    }
    public void addOvers(){
        OversDoa doa = new OversDoa();
        doa.addOvers(this.match);
    }

    public void addWickets(){
        WicketDoa doa =new WicketDoa();
        doa.addWickets(this.match);
    }

    public void addBalls(){
        BallsDoa doa = new BallsDoa();
        doa.addBalls(this.match);
    }
    public void addDetails(){
        addPlayers();
        addTeams();
        addBattingScores();
        addBowlerScores();
        addOvers();
        addWickets();
        addBalls();

    }


}
