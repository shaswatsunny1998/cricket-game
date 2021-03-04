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
                new Player("Virat", "Kohli", 34),
                new Player("Dhoni","Singh",45),
                new Player("Tiger","Wicked",23)
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
        allPlayerOut();
    }


    public void allPlayerOut() {
        Team team = this.team1;

        int persons = 0;
        for (Player player : team.getPlayers()) {
            singlePlayerMatch(player, persons);
        }
        System.out.println("Final Score is: " + team.getTotal_score());
    }

    public Player getPlayer(Team team, int n) {
        if (n >= team.getPlayers().size()) {
            return team.getPlayers().get(n % team.getPlayers().size());
        }
        return team.getPlayers().get(n);
    }

    public void singlePlayerMatch(Player p1, int persons) {
        RandomGenerator randomGenerator = new RandomGenerator();

        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 6, -1);
        int totalScoreTeam1 = team1.getTotal_score();
        Batsman batsman = p1.getBatsman();


        while (true) {
            Player p2 = getPlayer(team2, persons);
            persons++;
            Bowler bowler = p2.getBowler();
            int index = randomGenerator.getRandomRun(list.size());
            int int_random = list.get(index);
            for (int i = 0; i < BALLS_IN_A_OVER; ++i) {
                if (int_random != -1) {
                    totalScoreTeam1 += int_random;
                    team1.setTotal_score(totalScoreTeam1);
                    batsman.setRun(int_random);
                    bowler.setRun(int_random);
                    System.out.println("Run at Ball " + (int)(i+1)+": " + int_random);
                    index = randomGenerator.getRandomRun(list.size());
                    int_random = list.get(index);
                } else {
                    bowler.setWicket(bowler.getWicket() + 1);
                    System.out.println(bowler.getOvers());
                    System.out.println(bowler.getTotalRuns());
                    System.out.println("Out with total run: " + batsman.getTotalRun());
                    return;
                }
            }
            System.out.println("Over Changing---------");
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
