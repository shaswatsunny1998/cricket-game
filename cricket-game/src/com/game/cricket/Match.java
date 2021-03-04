package com.game.cricket;

import com.game.cricket.models.Batsman;
import com.game.cricket.models.Bowler;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;
import com.game.cricket.util.RandomGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Match {
    private String matchId;
    private String matchName;
    private Team team1;
    private Team team2;

    private int BALLS_IN_A_OVER=6;


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
                new Player("Shaswat", "Srivastava", 22),
                new Player("Virat", "Kohli", 34)
                //new Player("Dhoni","Singh",45),
                //new Player("Tiger","Wicked",23)
        ));
        this.team1 = team;

        /*

        System.out.println("Adding first Team to the match");
        System.out.println("-------------------------");
        System.out.print("Team Id: ");
        String teamId = scan.nextLine();
        System.out.print("Team Name: ");
        String teamName = scan.nextLine();
        Team team = new Team(teamId, teamName);
        team.addPlayers(scan);
        this.team1 = team;
        System.out.println("Congratulations First Team has been added to the match ");
        System.out.println("-------------------------");

         */
    }


    public void addSecondTeam(Scanner scan) {

        Team team = new Team("345", "Australia");
        team.setPlayers(Arrays.asList(
                new Player("ss", "ww", 45),
                new Player("Vir", "ran", 34)
        ));

        this.team2 = team;
        /*


        System.out.println("Adding Second Team to the match");
        System.out.println("-------------------------");
        System.out.print("Team Id: ");
        String teamId = scan.nextLine();
        System.out.print("Team Name: ");
        String teamName = scan.nextLine();
        Team team = new Team(teamId, teamName);
        team.addPlayers(scan);
        this.team2 = team;
        System.out.println("Congratulations Second Team has been added to the match ");
        System.out.println("-------------------------");

         */
    }


    public void start() {
        singlePlayerMatch(team1,team2);
        System.out.println("The Score to be chased: "+team1.getTotal_score());
//        singlePlayerMatch(team2,team1);
//        System.out.println("Score of Team 2: "+team2.getTotal_score());

    }


    /*

    public Player getBowlingPlayer(Team team, int n) {
        if (n >= team.getPlayers().size()) {
            return team.getPlayers().get(n % team.getPlayers().size());
        }
        return team.getPlayers().get(n);
    }



    public void allPlayerOut(Team batting_team) {
        Team team = batting_team;

        int persons = 0;
        for (Player player : team.getPlayers()) {
            singlePlayerMatch(player, persons,team,this.team2);
        }
        System.out.println("Final Score is: " + team.getTotal_score());
    }

     */

    public Player getBattingPlayer(Team battingTeam,int index){
        List<Player> battingPlayers = battingTeam.getPlayers();
        return battingPlayers.get(index);
    }

    public Player getBowlingPlayer(Team bowlingTeam,int index){
        List<Player> bowling_players = bowlingTeam.getPlayers();
        return bowling_players.get(index);
    }

    public int getValidBowler(List<Player> list,int index){
        if(index>=list.size())
            index = index%list.size();
        return index;
    }




    public void singlePlayerMatch(Team batting_team,Team bowling_team) {
        RandomGenerator randomGenerator = new RandomGenerator();

        //Immutable List
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 6, -1);

        int totalScoreTeam = batting_team.getTotal_score();

        int currBatsman=0;
        List<Player> batting_players = batting_team.getPlayers();

        int currBowler=0;
        List<Player> bowling_players = bowling_team.getPlayers();

        Player batting=batting_players.get(currBatsman);
        currBatsman++;
        Player running = batting_players.get(currBatsman);
        Player neutral;

        while (currBatsman<=Team.TEAM_SIZE-1) {
            System.out.println("Initial On Batting Side : " + batting.getFirstName());
            System.out.println("Initial On Running Side : " + running.getFirstName());

            Bowler bowler = bowling_players.get(currBowler).getBowler();

            System.out.println("Bowler Bowling: "+bowling_players.get(currBowler).getFirstName());

            for (int i = bowler.getCurrBall(); i < BALLS_IN_A_OVER; ++i) {

                int index = randomGenerator.getRandomRun(list.size());
                int int_random = list.get(index);

                if (int_random != -1) {
                    totalScoreTeam += int_random;
                    batting_team.setTotal_score(totalScoreTeam);

                    batting.getBatsman().setRun(int_random);
                    bowler.setRun(int_random);
                    bowler.setCurrBall(bowler.getCurrBall()+1);

                    if(int_random%2!=0)
                    {
                        neutral=batting;
                        batting=running;
                        running=neutral;
                    }
                    System.out.println("Run at Ball " + (int)(i+1)+": " + int_random);
                    System.out.println("After ball On Batting Side : " + batting.getFirstName());
                    System.out.println("After ball On Running Side : " + running.getFirstName());

                } else {
                    bowler.setWicket(bowler.getWicket() + 1);
                    System.out.println(bowler.getOvers());

                    System.out.println(bowler.getTotalRuns());
                    batting.getBatsman().setOut(true);

                    currBatsman++;
                    bowler.setCurrBall(bowler.getCurrBall()+1);

                    System.out.println("Out at Ball: " + bowler.getCurrBall());
                    System.out.println("Out with total run: " + batting.getBatsman().getTotalRun());

                    running.getBatsman().getTotalRun();
                    break;
                }

            }
            if(bowler.getCurrBall()==BALLS_IN_A_OVER){
                System.out.println("Over Changing---------");
                bowler.setCurrBall(0);
                currBowler++;
                currBowler=getValidBowler(bowling_players,currBowler);

                neutral=batting;
                batting=running;
                running=neutral;
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
