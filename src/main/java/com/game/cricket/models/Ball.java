package com.game.cricket.models;

public class Ball {
    private int run;
    private int batsmanId;
    private int bowlerId;

    public Ball() {
    }

    public Ball(int run) {
        this.run = run;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getBatsmanId() {
        return batsmanId;
    }

    public void setBatsmanId(int batsmanId) {
        this.batsmanId = batsmanId;
    }

    public int getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(int bowlerId) {
        this.bowlerId = bowlerId;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "run=" + run +
                ", batsmanId=" + batsmanId +
                ", bowlerId=" + bowlerId +
                '}';
    }
}
