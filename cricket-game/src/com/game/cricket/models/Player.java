package com.game.cricket.models;

public class Player implements Person{
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String type=null;
    private boolean onField=false;
    private static int idNext=0;

    public Player() {
    }

    public Player(String firstName, String lastName, int age) {
        this.id = idNext++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    @Override
    public boolean isPlaying() {
        return this.onField;
    }
}
