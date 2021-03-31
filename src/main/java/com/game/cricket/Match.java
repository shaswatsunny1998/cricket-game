package com.game.cricket;

import com.game.cricket.models.ModifiedInning;
import com.game.cricket.models.Over;
import com.game.cricket.models.Team;
import com.game.cricket.models.UserControlInning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    private UserControlInning firstHalfInning;
    private UserControlInning secondHalfInning;

    private boolean isFinished = false;

    public Match() {
    }


    public Match(int matchId, String matchName, String venue, Date matchDate) {
        this.matchId = matchId;
        this.matchName = matchName;
        this.venue = venue;
        this.matchDate = matchDate;
    }

    public void addTeams(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
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

    public void start() {
        initializeOvers();
        startFirstHalf();
        startSecondHalf();
    }

    public void startFirstHalf(){
        initializeOvers();
        ModifiedInning inning = new ModifiedInning();
        inning.singleInning(matchId, team1, team2, 0, this.firstHalfOvers, false);
        System.out.println("The Score to be chased: " + team1.getTotal_score());
        System.out.println(firstHalfOvers);
        System.out.println("--------------------------------");
    }


    public void startSecondHalf(){
        ModifiedInning inning = new ModifiedInning();
        inning.singleInning(matchId, team2, team1, team1.getTotal_score(), this.secondHalfOvers, true);
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


    public UserControlInning getFirstHalfInning() {
        return firstHalfInning;
    }

    public UserControlInning getSecondHalfInning() {
        return secondHalfInning;
    }

    public void setFirstHalfInning(UserControlInning firstHalfInning) {
        this.firstHalfInning = firstHalfInning;
    }

    public void setSecondHalfInning(UserControlInning secondHalfInning) {
        this.secondHalfInning = secondHalfInning;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
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
