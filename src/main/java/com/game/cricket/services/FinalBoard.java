package com.game.cricket.services;

import com.game.cricket.Match;
import com.game.cricket.doa.*;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FinalBoard {
    private Match match;
    private Team firstTeam;
    private Team secondTeam;


    @Autowired
    ScoreBoardDoa scoreBoardDoa;


    @Autowired
    HalfInningDoa halfInningDoa;




    public FinalBoard() {
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
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
            System.out.println("Team 2 WON : "+secondTeam.getTeamName());
        } else {
            System.out.println("Team 1 WON : "+firstTeam.getTeamName());
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


    public void addScoreBoard(){

        boolean draw=false;
        int winning ,losing;
        int winWicket,loseWicket;
        int winScore,loseScore;
        if (secondTeam.getTotal_score() > firstTeam.getTotal_score()) {

            winning=Integer.parseInt(secondTeam.getTeamId());
            losing=Integer.parseInt(firstTeam.getTeamId());
            loseWicket =wicketsDownFirstTeam();
            winWicket = wicketsDownSecondTeam();
            winScore = getRunsBySecondTeam();
            loseScore= getRunsByFirstTeam();

        } else if(secondTeam.getTotal_score() < firstTeam.getTotal_score()){
            winning=Integer.parseInt(firstTeam.getTeamId());
            losing=Integer.parseInt(secondTeam.getTeamId());
            winWicket =wicketsDownFirstTeam();
            loseWicket = wicketsDownSecondTeam();
            loseScore = getRunsBySecondTeam();
            winScore= getRunsByFirstTeam();
        }
        else{
            winning=Integer.parseInt(secondTeam.getTeamId());
            losing=Integer.parseInt(firstTeam.getTeamId());
            draw=true;
            loseWicket =wicketsDownFirstTeam();
            winWicket = wicketsDownSecondTeam();
            winScore = getRunsBySecondTeam();
            loseScore= getRunsByFirstTeam();
        }

        scoreBoardDoa.addScoreBoard(this.match.getMatchId(),winning,losing,winScore,loseScore,winWicket,loseWicket,draw);
    }

    public void addInning(){
        halfInningDoa.addHalfInningDetails(this.match);
    }

    public void addDetails(){
        addScoreBoard();
        addInning();
    }


}
