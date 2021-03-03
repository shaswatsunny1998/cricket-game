package com.game.cricket.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team {

    private String teamId;
    private String teamName;
    private List<Player> players;
    private int teamSize = 2;


    public Team(String teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.players = new ArrayList<Player>();

    }

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
        for (int i = 0; i < teamSize; ++i) {
            System.out.println("Adding Information of Player: " + (int) (i + 1));
            players.add(addPlayer(scan));
        }

    }


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

    @Override
    public String toString() {
        return "Team{" +
                "teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", players=" + players +
                '}';
    }
}
