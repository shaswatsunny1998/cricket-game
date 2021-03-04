package com.game.cricket.models;

public class Batsman {
    private int position;
    private int dotRun;
    private int oneRun;
    private int twoRun;
    private int threeRun;
    private int fourRun;
    private int sixRun;
    private int totalRun;
    private boolean isOut;


    //TODO Add PlayerId for Removing Data Redundancy in Database(Same with Bowler Attribute)

    public Batsman() {
    }

    public Batsman(int position, boolean isOut) {
        this.position = position;
        this.isOut = false;
    }

    public int getTotalRun() {
        totalRun = this.getTwoRun()*2 + this.getOneRun() + this.getThreeRun()*3 + this.getFourRun()*4 + this.getSixRun()*6;
        return totalRun;
    }

    public void setTotalRun(int totalRun) {
        this.totalRun = totalRun;
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

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
        return "Batsman{" +
                "position=" + position +
                ", dotRun=" + dotRun +
                ", oneRun=" + oneRun +
                ", twoRun=" + twoRun +
                ", threeRun=" + threeRun +
                ", fourRun=" + fourRun +
                ", sixRun=" + sixRun +
                ", totalRun=" + totalRun +
                ", isOut=" + isOut +
                '}';
    }
}
