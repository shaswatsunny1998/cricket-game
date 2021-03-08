package com.game.cricket.models;

public class Batsman extends Player{

    public Batsman(String firstName, String lastName, int age) {
        super(firstName,lastName,age,"Batsman",34);
    }

    public String toString() {
        return super.toString();
    }
}
