package com.game.cricket;

import com.game.cricket.models.Batsman;
import com.game.cricket.models.Bowler;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
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


    public void addAllTeams(Scanner scan) {
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
        this.team1 = team;
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
        this.team2 = team;
        System.out.println("Congratulations Second Team has been added to the match ");
        System.out.println("-------------------------");
    }


    public void start() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 6, -1);

        Player p1 = team1.getPlayers().get(0);
        Player p2 = team2.getPlayers().get(0);

        Batsman batsman = p1.getBatsman();
        Bowler bowler = p2.getBowler();

        int total_run = 0;
        Random random = new Random();
        int index = random.nextInt(list.size());
        int int_random = list.get(index);

        while (int_random != -1) {
            total_run += int_random;
            batsman.setRun(int_random);
            bowler.setRun(int_random);
            System.out.println("Run at Ball : " + int_random);
            index = random.nextInt(list.size());
            int_random = list.get(index);
        }
        bowler.setWicket(bowler.getWicket() + 1);
        System.out.println(bowler.getOvers());
        System.out.println(bowler.getTotalRuns());
        System.out.println("Out with total run: " + total_run);
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
