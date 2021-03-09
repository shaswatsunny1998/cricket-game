package com.game.cricket.models;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamId;
    private String teamName;
    private List<Player> players;
    private int total_score;

    public static final int TEAM_SIZE = 3;


    public Team(String teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.players = new ArrayList<Player>();

    }


    public boolean isLastPlayer(int index) {
        if (index == TEAM_SIZE)
            return true;
        return false;

    }
/*
    public Player addPlayer(Scanner scan) {
        System.out.println("---------");
        System.out.println("Enter First Name: ");
        String firstName = scan.nextLine();
        System.out.println("Enter Second Name: ");
        String lastName = scan.nextLine();
        System.out.println("Enter age: ");
        int age = scan.nextInt();
        scan.nextLine();
        Player player = new Player(firstName, lastName, age);
        return player;
    }

    public void addPlayers(Scanner scan) {
        for (int i = 0; i < TEAM_SIZE; ++i) {
            System.out.println("Adding Information of Player: " + (int) (i + 1));
            players.add(addPlayer(scan));
        }

    }

 */


    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", players=" + players +
                ", total_score=" + total_score +
                '}';
    }

}
