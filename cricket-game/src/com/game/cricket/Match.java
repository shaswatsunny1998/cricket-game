package com.game.cricket;

import com.game.cricket.models.Team;

import java.util.Scanner;

public class Match {
    private String matchId;
    private String matchName;
    private Team team1;
    private Team team2;

    public Match(String matchId, String matchName) {
        this.matchId = matchId;
        this.matchName = matchName;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", matchName='" + matchName + '\'' +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }


    public void addAllTeams(Scanner scan){
        addFirstTeam(scan);
        addSecondTeam(scan);
    }


    public void addFirstTeam(Scanner scan) {
        System.out.println("Adding first Team to the match");
        System.out.println("-------------------------");
        System.out.print("Team Id: ");
        String teamId = scan.nextLine();
        System.out.print("Team Name: ");
        String teamName = scan.nextLine();
        Team team = new Team(teamId, teamName);
        team.addPlayers(scan);
        this.team1=team;
        System.out.println("Congratulations First Team has been added to the match ");
        System.out.println("-------------------------");
    }


    public void addSecondTeam(Scanner scan) {
        System.out.println("Adding Second Team to the match");
        System.out.println("-------------------------");
        System.out.print("Team Id: ");
        String teamId = scan.nextLine();
        System.out.print("Team Name: ");
        String teamName = scan.nextLine();
        Team team = new Team(teamId, teamName);
        team.addPlayers(scan);
        this.team2=team;
        System.out.println("Congratulations Second Team has been added to the match ");
        System.out.println("-------------------------");
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
