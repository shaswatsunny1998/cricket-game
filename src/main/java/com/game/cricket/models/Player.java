package com.game.cricket.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//Way Type.
//@JsonTypeInfo(use = new JsonTypeInfo.As("type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Batsman.class),
//        @JsonSubTypes.Type(value = Bowler.class),
//})
//@JsonDeserialize(as=Batsman.class)


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Batsman.class, name = "Batsman"),
        @JsonSubTypes.Type(value = Bowler.class, name = "Bowler"),
})
public abstract class Player {
    private int playerId;
    private String firstName;
    private String lastName;
    //DOB
    private int age;
    private String type;
    private Score score = new Score();

    private static int ID = 0;

    public Player() {
    }

    public Player(int playerId, String firstName, String lastName, int age, String type) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.type = type;
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
