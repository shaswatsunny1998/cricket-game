package com.game.cricket.models;

public abstract class Player {
    private int playerId;
    private String firstName;
    private String lastName;
    private int age;
    private String type = null;
    private Score score = new Score();

    private static int ID = 0;

    public Player() {
    }

    public Player(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Player(String firstName, String lastName, int age, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.type = type;
        this.playerId = ID;
        ID++;

    }

    public Player(String firstName, String lastName, int age, String type, int strikeRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.type = type;
        this.playerId = ID;
        score.setStrikeRate(strikeRate);
        ID++;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", score=" + score +
                '}';
    }
}
