package com.game.cricket.models;

import java.math.BigDecimal;

public class Bowler {

    private int wicket;
    private int dotRun;
    private int oneRun;
    private int twoRun;
    private int threeRun;
    private int fourRun;
    private int sixRun;
    private int currBall;




    public Bowler() {
    }

    public int getTotalBalls() {
        return this.getTwoRun() + this.getOneRun() + this.getThreeRun() + this.getFourRun() + this.getSixRun() + this.getDotRun() + this.getWicket();
    }

    public int getTotalRuns() {
        return this.getTwoRun() * 2 + this.getOneRun() + this.getThreeRun() * 3 + this.getFourRun() * 4 + this.getSixRun() * 6;
    }

    public double getOvers() {
        int total_overs = getTotalBalls() / 6;
        int rest_over = getTotalBalls() % 6;
        // for 3 balls we are getting 0.30000004 , using Big Decimal will decrease efficiency . What to do?
        BigDecimal decimal = new BigDecimal(total_overs + 0.1 * rest_over).setScale(1, BigDecimal.ROUND_FLOOR);
        return decimal.doubleValue();
    }


    public void setRun(int run) {
        switch (run) {
            case 0 -> setDotRun(getDotRun() + 1);
            case 1 -> setOneRun(getOneRun() + 1);
            case 2 -> setTwoRun(getTwoRun() + 1);
            case 3 -> setThreeRun(getThreeRun() + 1);
            case 4 -> setFourRun(getFourRun() + 1);
            case 6 -> setSixRun(getSixRun() + 1);

        }
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

    public void setWicket(int wicket) {
        this.wicket = wicket;
    }

    public int getDotRun() {
        return dotRun;
    }

    public void setDotRun(int dotRun) {
        this.dotRun = dotRun;
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
    @Override
    public String toString() {
        return "Bowler{" +
                "wicket=" + wicket +
                ", dotRun=" + dotRun +
                ", oneRun=" + oneRun +
                ", twoRun=" + twoRun +
                ", threeRun=" + threeRun +
                ", fourRun=" + fourRun +
                ", sixRun=" + sixRun +
                ", currBall=" + currBall +
                '}';
    }

}
