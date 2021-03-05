package com.game.cricket;

import com.game.cricket.models.*;
import com.game.cricket.util.RandomGenerator;

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

    private final int NUM_OF_OVERS = 20;

    private List<Over> firstHalfOvers;
    private List<Over> secondHalfOvers;

    private int BALLS_IN_A_OVER = 6;


    public void initializeOvers() {
        firstHalfOvers = new ArrayList<Over>(NUM_OF_OVERS);
        secondHalfOvers = new ArrayList<Over>(NUM_OF_OVERS);
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
                new Batsman("Shaswat", "Srivastava", 22),
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
        //finalBoard = new FinalBoard();
        firstHalfMatch(team1, team2);
        System.out.println("The Score to be chased: " + team1.getTotal_score());
        System.out.println(firstHalfOvers);
        System.out.println("--------------------------------");
        secondHalfMatch(team2, team1, team1.getTotal_score());
        System.out.println("Score by Team 2: " + team2.getTotal_score());
        System.out.println(secondHalfOvers);
        if (team2.getTotal_score() > team1.getTotal_score()) {
            System.out.println("Team 2 WON");
        } else {
            System.out.println("Team 1 WON");
        }

    }


    /**
     * Will Be used in the Week-2
     *
     * @param battingTeam
     * @param index
     * @return player
     */
    public Player getBattingPlayer(Team battingTeam, int index) {
        List<Player> battingPlayers = battingTeam.getPlayers();
        return battingPlayers.get(index);
    }

    /**
     * Will Be used in the Week-2
     *
     * @param bowlingTeam
     * @param index
     * @return player
     */


    public Player getBowlingPlayer(Team bowlingTeam, int index) {
        List<Player> bowling_players = bowlingTeam.getPlayers();
        return bowling_players.get(index);
    }

    public int getValidBowler(List<Player> list, int index) {
        if (index >= list.size())
            index = index % list.size();
        return index;
    }


    public void firstHalfMatch(Team batting_team, Team bowling_team) {
        RandomGenerator randomGenerator = new RandomGenerator();

        //Immutable List
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 6, -1);

        int totalScoreTeam = batting_team.getTotal_score();

        int currBatsman = 0;
        List<Player> batting_players = batting_team.getPlayers();

        int currBowler = 0;
        //TODO think of data structure which can help in getting shuffled bowlers
        // - Heap?
        List<Player> bowling_players = bowling_team.getPlayers();


        // Here Batting is becoming Player Variable.
        Player batting = batting_players.get(currBatsman);
        currBatsman++;
        Player running = batting_players.get(currBatsman);
        Player neutral;

        int currOver = 0;

        Over over=new Over();

        // TODO Breakdown Function in different sub-division
        while (currBatsman <= Team.TEAM_SIZE - 1) {
            System.out.println("Initial On Batting Side : " + batting.getFirstName());
            System.out.println("Initial On Running Side : " + running.getFirstName());

            Player bowler = bowling_players.get(currBowler);

            System.out.println("Bowler Bowling: " + bowler.getFirstName());

            if(over.getCurrBall()<Over.NUM_OF_BALLS){

            }
            else{
                over = new Over();
            }

            for (int i = bowler.getScore().getCurrBall(); i < Over.NUM_OF_BALLS; ++i) {

                int index = randomGenerator.getRandomRun(list.size());
                int int_random = list.get(index);
                over.setCurrBall(over.getCurrBall()+1);

                if (int_random != -1) {

                    totalScoreTeam += int_random;
                    batting_team.setTotal_score(totalScoreTeam);

                    batting.getScore().setRun(int_random);
                    bowler.getScore().setRunBowl(int_random);
                    bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);

                    if (int_random % 2 != 0) {
                        neutral = batting;
                        batting = running;
                        running = neutral;
                    }
                    over.setRun(int_random);
                    System.out.println("Run at Ball " + (int) (i + 1) + ": " + int_random);
                    System.out.println("After ball On Batting Side : " + batting.getFirstName());
                    System.out.println("After ball On Running Side : " + running.getFirstName());

                    //TODO In second half don't add the total runs to the batting player side(if run by wicket).

                } else {
                    bowler.getScore().setWickets(bowler.getScore().getWickets() + 1);
                    System.out.println(bowler.getScore().getOvers());

                    System.out.println(bowler.getScore().getTotalRunsBowlGiven());
                    batting.getScore().setOut(true);

                    bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);

                    System.out.println("Out at Ball: " + bowler.getScore().getCurrBall());
                    System.out.println("Out with total run: " + batting.getScore().getTotalRun());

                    over.setWicket(over.getWicket() + 1);

                    running.getScore().getTotalRun();
                    currBatsman++;

                    if (!batting_team.isLastPlayer(currBatsman)) {
                        batting = batting_players.get(currBatsman);
                    }
                    break;
                }

            }
            if (bowler.getScore().getCurrBall() == BALLS_IN_A_OVER) {
                System.out.println("Over Changing---------");

                over.setTotalRun();
                this.firstHalfOvers.add(over);

                bowler.getScore().setCurrBall(0);
                currBowler++;
                currBowler = getValidBowler(bowling_players, currBowler);
                neutral = batting;
                batting = running;
                running = neutral;
            }
            if (currBatsman > Team.TEAM_SIZE - 1) {
                over.setTotalRun();
                this.firstHalfOvers.add(over);
            }
        }

    }




    public void secondHalfMatch(Team batting_team, Team bowling_team, int chaseScore) {
        RandomGenerator randomGenerator = new RandomGenerator();

        //Immutable List
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 6, -1);

        int totalScoreTeam = batting_team.getTotal_score();

        int currBatsman = 0;
        List<Player> batting_players = batting_team.getPlayers();

        int currBowler = 0;
        //TODO think of data structure which can help in getting shuffled bowlers
        // - Heap?
        List<Player> bowling_players = bowling_team.getPlayers();

        Player batting = batting_players.get(currBatsman);
        currBatsman++;
        Player running = batting_players.get(currBatsman);
        Player neutral;

        int currOver = 0;

        Over over=new Over();


        // TODO Breakdown Function in different sub-division
        while (currBatsman <= Team.TEAM_SIZE - 1) {
            System.out.println("Initial On Batting Side : " + batting.getFirstName());
            System.out.println("Initial On Running Side : " + running.getFirstName());

            Player bowler = bowling_players.get(currBowler);

            System.out.println("Bowler Bowling: " + bowler.getFirstName());

            if(over.getCurrBall()<Over.NUM_OF_BALLS){

            }
            else{
                over = new Over();
            }


            for (int i = bowler.getScore().getCurrBall(); i < BALLS_IN_A_OVER; ++i) {

                int index = randomGenerator.getRandomRun(list.size());
                int int_random = list.get(index);

                over.setCurrBall(over.getCurrBall()+1);

                if (int_random != -1) {

                    totalScoreTeam += int_random;
                    batting_team.setTotal_score(totalScoreTeam);

                    batting.getScore().setRun(int_random);
                    bowler.getScore().setRunBowl(int_random);
                    bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);

                    if (int_random % 2 != 0) {
                        neutral = batting;
                        batting = running;
                        running = neutral;
                    }
                    over.setRun(int_random);
                    System.out.println("Run at Ball " + (int) (i + 1) + ": " + int_random);
                    System.out.println("After ball On Batting Side : " + batting.getFirstName());
                    System.out.println("After ball On Running Side : " + running.getFirstName());

                    if (batting_team.getTotal_score() > chaseScore) {
                        over.setTotalRun();
                        this.secondHalfOvers.add(over);
                        return;
                    }
                    //TODO In second half don't add the total runs to the batting player side(if run by wicket).

                } else {
                    bowler.getScore().setWickets(bowler.getScore().getWickets() + 1);
                    System.out.println(bowler.getScore().getOvers());

                    System.out.println(bowler.getScore().getTotalRunsBowlGiven());
                    batting.getScore().setOut(true);

                    bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);

                    System.out.println("Out at Ball: " + bowler.getScore().getCurrBall());
                    System.out.println("Out with total run: " + batting.getScore().getTotalRun());

                    over.setWicket(over.getWicket() + 1);

                    running.getScore().getTotalRun();

                    currBatsman++;

                    if (!batting_team.isLastPlayer(currBatsman)) {
                        batting = batting_players.get(currBatsman);
                    }
                    break;
                }

            }
            if (bowler.getScore().getCurrBall() == BALLS_IN_A_OVER) {
                System.out.println("Over Changing---------");

                over.setTotalRun();
                this.secondHalfOvers.add(over);

                bowler.getScore().setCurrBall(0);
                currBowler++;
                currBowler = getValidBowler(bowling_players, currBowler);
                neutral = batting;
                batting = running;
                running = neutral;
            }
            if (currBatsman > Team.TEAM_SIZE - 1) {
                over.setTotalRun();
                this.secondHalfOvers.add(over);
            }
        }

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
