package com.game.cricket.services;

import com.game.cricket.Match;
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


}
