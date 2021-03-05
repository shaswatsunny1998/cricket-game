package com.game.cricket.models;

public class Batsman extends Player{
    private int hoha;

    public int getHoha() {
        return hoha;
    }

    public void setHoha(int hoha) {
        this.hoha = hoha;
    }

    public Batsman(String firstName, String lastName, int age) {
        super(firstName,lastName,age,"Batsman");
    }

    public String toString() {
        return super.toString();
    }
}
