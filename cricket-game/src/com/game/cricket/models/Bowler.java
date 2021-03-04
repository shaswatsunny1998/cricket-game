package com.game.cricket.models;

public class Bowler {

    private int wicket;
    private int dotRun;
    private int oneRun;
    private int twoRun;
    private int threeRun;
    private int fourRun;
    private int sixRun;


    public Bowler() {
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

    public int getTotalBalls() {
        return this.getTwoRun() + this.getOneRun() + this.getThreeRun() + this.getFourRun() + this.getSixRun()+this.getDotRun();
    }

    public int getTotalRuns() {
        return this.getTwoRun() * 2 + this.getOneRun() + this.getThreeRun() * 3 + this.getFourRun() * 4 + this.getSixRun() * 6;
    }

    public double getOvers() {
        int total_overs= getTotalBalls()/ 6;
        int rest_over= getTotalBalls() % 6;
        return total_overs+0.1*rest_over;
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
                '}';
    }

    public void setRun(int run) {
        switch (run) {
            case 0:
                setDotRun(getDotRun() + 1);
                break;
            case 1:
                setOneRun(getOneRun() + 1);
                break;
            case 2:
                setTwoRun(getTwoRun() + 1);
                break;
            case 3: setThreeRun(getThreeRun()+1);
                break;
            case 4: setFourRun(getFourRun()+1);
                break;
        }
    }

}
