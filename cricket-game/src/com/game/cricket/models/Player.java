package com.game.cricket.models;

public class Player{
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String type=null;
    private Batsman batsman=new Batsman();
    private Bowler bowler=new Bowler();

    private static int idNext=0;

    public Player() {
    }

    public Player(String firstName, String lastName, int age) {
        this.id = idNext++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.batsman.setPosition(this.id);
    }

    public Player(String firstName, String lastName, int age, String type) {
        this.id = idNext++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", batsman=" + batsman +
                ", bowler=" + bowler +
                '}';
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

    public Batsman getBatsman() {
        return batsman;
    }

    public Bowler getBowler(){
        return bowler;
    }

    public void setBatsman(Batsman batsman) {
        this.batsman = batsman;
    }

}
