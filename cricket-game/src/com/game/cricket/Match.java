package com.game.cricket;

import com.game.cricket.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Match {

    private String matchId;
    private String matchName;

    private Team team1;
    private Team team2;

    private FinalBoard finalBoard;

    public static final int NUM_OF_OVERS = 3;

    private List<Over> firstHalfOvers;
    private List<Over> secondHalfOvers;

    private final int BALLS_IN_A_OVER = 6;


    public Match() {
    }

    public void initializeOvers() {
        firstHalfOvers = new ArrayList<Over>();
        secondHalfOvers = new ArrayList<Over>();
    }

    public Match(String matchId, String matchName) {
        this.matchId = matchId;
        this.matchName = matchName;
    }

    public void addAllTeams(Scanner scan) {
        addFirstTeam(scan);
        addSecondTeam(scan);
    }


    public void addFirstTeam(Scanner scan) {

        Team team = new Team("123", "India");
        team.setPlayers(Arrays.asList(
                new Bowler("Shaswat", "Srivastava", 22),
                new Batsman("Virat", "Kohli", 34),
                new Bowler("Dhoni", "Singh", 45)
        ));
        this.team1 = team;
    }

    public void addSecondTeam(Scanner scan) {

        Team team = new Team("345", "Australia");
        team.setPlayers(Arrays.asList(
                new Bowler("ss", "ww", 45),
                new Bowler("Vir", "ran", 34),
                new Batsman("Tiger", "Wicked", 23)
        ));
        this.team2 = team;
    }


    public void start() {
        initializeOvers();
        Inning inning = new Inning();
        inning.singleInning(team1, team2, 0, this.firstHalfOvers, false);
        System.out.println("The Score to be chased: " + team1.getTotal_score());
        System.out.println(firstHalfOvers);
        System.out.println("--------------------------------");
        inning.singleInning(team2, team1, team1.getTotal_score(), this.secondHalfOvers, true);
        System.out.println("Score by Team 2: " + team2.getTotal_score());
        System.out.println(secondHalfOvers);
        FinalBoard finalBoard = new FinalBoard(this.team1, this.team2);
        finalBoard.result();
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

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", matchName='" + matchName + '\'' +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }

}
