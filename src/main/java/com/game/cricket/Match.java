package com.game.cricket;

import com.game.cricket.models.Inning;
import com.game.cricket.models.Over;
import com.game.cricket.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Match {

    private int matchId;
    private String matchName;
    private String venue;
    private Date matchDate;

    private Team team1;
    private Team team2;

    //private FinalBoard finalBoard;

    public static final int NUM_OF_OVERS = 6;

    private List<Over> firstHalfOvers;
    private List<Over> secondHalfOvers;

    private final int BALLS_IN_A_OVER = 6;

    public Match() {
    }

    public Match(int matchId, String matchName, String venue, Date matchDate) {
        this.matchId = matchId;
        this.matchName = matchName;
        this.venue = venue;
        this.matchDate = matchDate;
    }

    public void addTeams(Team team1,Team team2){
        this.team1=team1;
        this.team2=team2;
    }

    public void initializeOvers() {
        this.firstHalfOvers = new ArrayList<Over>();
        this.secondHalfOvers = new ArrayList<Over>();
    }

    public List<Over> getFirstHalfOvers() {
        return firstHalfOvers;
    }

    public void setFirstHalfOvers(List<Over> firstHalfOvers) {
        this.firstHalfOvers = firstHalfOvers;
    }

    public List<Over> getSecondHalfOvers() {
        return secondHalfOvers;
    }

    public void setSecondHalfOvers(List<Over> secondHalfOvers) {
        this.secondHalfOvers = secondHalfOvers;
    }

/*
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

 */


    @Autowired
    Inning inning;

    public void start() {
        initializeOvers();
        //Inning inning = new Inning();
        inning.singleInning(matchId,team1, team2, 0, this.firstHalfOvers, false);
        System.out.println("The Score to be chased: " + team1.getTotal_score());
        System.out.println(firstHalfOvers);
        System.out.println("--------------------------------");
        inning.singleInning(matchId,team2, team1, team1.getTotal_score(), this.secondHalfOvers, true);
        System.out.println("Score by Team 2: " + team2.getTotal_score());
        System.out.println(secondHalfOvers);
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
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
