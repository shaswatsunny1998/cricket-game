package com.game.cricket.models;

public class Ball {
    private int run;

    public Ball() {
    }

    public Ball(int run) {
        this.run = run;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "run=" + run +
                '}';
    }
}
