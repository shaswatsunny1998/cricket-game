package com.game.cricket.models;

public class Over {
    public static final int NUM_OF_BALLS=6;
    private int wicket;
    private int dotBalls;
    private int oneRun;
    private int twoRun;
    private int threeRun;
    private int fourRun;
    private int sixRun;
    private int totalRun;
    private int currBall;


    public Over() {
    }


    public int getCurrBall() {
        return currBall;
    }

    public void setCurrBall(int currBall) {
        this.currBall = currBall;
    }

    public int getWicket() {
        return wicket;
    }

    public void setWicket(int wickets) {
        this.wicket = wickets;
    }

    public int getDotBalls() {
        return dotBalls;
    }

    public void setDotBalls(int dotBalls) {
        this.dotBalls = dotBalls;
    }

    public int getOneRun() {
        return oneRun;
    }

    public void setOneRun(int oneRun) {
        this.oneRun = oneRun;
    }

    public int getTwoRun() {
        return twoRun;
    }

    public void setTwoRun(int twoRun) {
        this.twoRun = twoRun;
    }

    public int getThreeRun() {
        return threeRun;
    }

    public void setThreeRun(int threeRun) {
        this.threeRun = threeRun;
    }

    public int getFourRun() {
        return fourRun;
    }

    public void setFourRun(int fourRun) {
        this.fourRun = fourRun;
    }

    public int getSixRun() {
        return sixRun;
    }

    public void setSixRun(int sixRun) {
        this.sixRun = sixRun;
    }

    public int getTotalRun() {
        return totalRun;
    }

    public void setTotalRun() {
        this.totalRun = this.fourRun*4+this.oneRun+this.threeRun*3+this.twoRun*2+this.sixRun*6;
    }
    public void setRun(int run) {
        switch (run) {
            case 0 -> setDotBalls(getDotBalls() + 1);
            case 1 -> setOneRun(getOneRun() + 1);
            case 2 -> setTwoRun(getTwoRun() + 1);
            case 3 -> setThreeRun(getThreeRun() + 1);
            case 4 -> setFourRun(getFourRun() + 1);
            case 6 -> setSixRun(getSixRun() + 1);
        }
    }

    @Override
    public String toString() {
        return "Over{" +
                "wicket=" + wicket +
                ", dotBalls=" + dotBalls +
                ", oneRun=" + oneRun +
                ", twoRun=" + twoRun +
                ", threeRun=" + threeRun +
                ", fourRun=" + fourRun +
                ", sixRun=" + sixRun +
                ", totalRun=" + totalRun +
                '}';
    }
}
