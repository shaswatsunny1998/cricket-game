package com.game.cricket.models;

import java.math.BigDecimal;

public class Score {


    //TODO Add PlayerId for Removing Data Redundancy in Database(Same with Bowler Attribute)
    // Add yet_to_bat field
    // Add number_of_balls_played

    //Batting Attributes
    private int position;
    private int dotRun;
    private int oneRun;
    private int twoRun;
    private int threeRun;
    private int fourRun;
    private int sixRun;
    private int totalRun;
    private boolean isOut;
    private int strikeRate;

    //Balling Attributes
    private int wickets;
    private int dotBalls;
    private int oneRunBalls;
    private int twoRunBalls;
    private int threeRunBalls;
    private int fourRunBalls;
    private int sixRunBalls;
    private int currBall;

    public Score() {
    }

    //Bowler Attribute Constructor
    public Score(int wickets, int dotBalls, int oneRunBalls, int twoRunBalls, int threeRunBalls, int fourRunBalls, int sixRunBalls, int currBall) {
        this.wickets = wickets;
        this.dotBalls = dotBalls;
        this.oneRunBalls = oneRunBalls;
        this.twoRunBalls = twoRunBalls;
        this.threeRunBalls = threeRunBalls;
        this.fourRunBalls = fourRunBalls;
        this.sixRunBalls = sixRunBalls;
        this.currBall = currBall;
    }




    //Batting Attribute Constructor
    public Score(int dotRun, int oneRun, int twoRun, int threeRun, int fourRun, int sixRun, int totalRun, boolean isOut, int strikeRate) {
        this.dotRun = dotRun;
        this.oneRun = oneRun;
        this.twoRun = twoRun;
        this.threeRun = threeRun;
        this.fourRun = fourRun;
        this.sixRun = sixRun;
        this.totalRun = totalRun;
        this.isOut = isOut;
        this.strikeRate = strikeRate;
    }

    public Score(int strikeRate) {
        this.strikeRate = strikeRate;
    }

    // Batsman methods
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

    public int getTotalBallsPlayed() {
        return this.getTwoRun() + this.getOneRun() + this.getThreeRun() + this.getFourRun() +
                this.getSixRun() + this.getDotRun();
    }


    public double getTotalStrikeRate(){
        if(getTotalRun()==0)
            return 0.0;
        BigDecimal decimal = new BigDecimal((getTotalRun()*100.0)/getTotalBallsPlayed()).setScale(2, BigDecimal.ROUND_FLOOR);
        return decimal.doubleValue();
    }



    //Bowler methods
    public int getTotalBallsBowl() {
        return this.getTwoRunBalls() + this.getOneRunBalls() + this.getThreeRunBalls() + this.getFourRunBalls() +
                this.getSixRunBalls() + this.getDotBalls() + this.getWickets();
    }

    public int getTotalRunsBowlGiven(){
        return this.getTwoRunBalls() *2 + this.getOneRunBalls() + this.getThreeRunBalls()*3 + this.getFourRunBalls()*4 +
                this.getSixRunBalls()*6;
    }
    public double getOvers() {
        /*int total_overs = getTotalBallsBowl() / 6;
        int rest_over = getTotalBallsBowl() % 6;
        // for 3 balls we are getting 0.30000004 , using Big Decimal will decrease efficiency . What to do?
        BigDecimal decimal = new BigDecimal(total_overs + 0.1 * rest_over).setScale(1, BigDecimal.ROUND_FLOOR);
        return decimal.doubleValue();

         */

        String total_overs = String.valueOf(Math.round(getTotalBallsBowl() / 6));
        String rest_over=String.valueOf(getTotalBallsBowl()%6);
        double numberOfOvers=Double.valueOf(total_overs+"."+rest_over);
        return numberOfOvers;
    }


    public void setRunBowl(int run) {
        switch (run) {
            case 0 -> setDotBalls(getDotBalls() + 1);
            case 1 -> setOneRunBalls(getOneRunBalls() + 1);
            case 2 -> setTwoRunBalls(getTwoRunBalls() + 1);
            case 3 -> setThreeRunBalls(getThreeRunBalls() + 1);
            case 4 -> setFourRunBalls(getFourRunBalls() + 1);
            case 6 -> setSixRunBalls(getSixRunBalls() + 1);

        }
    }



    //Getters and Setters


    public int getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(int strikeRate) {
        this.strikeRate = strikeRate;
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

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getDotBalls() {
        return dotBalls;
    }

    public void setDotBalls(int dotBalls) {
        this.dotBalls = dotBalls;
    }

    public int getOneRunBalls() {
        return oneRunBalls;
    }

    public void setOneRunBalls(int oneRunBalls) {
        this.oneRunBalls = oneRunBalls;
    }

    public int getTwoRunBalls() {
        return twoRunBalls;
    }

    public void setTwoRunBalls(int twoRunBalls) {
        this.twoRunBalls = twoRunBalls;
    }

    public int getThreeRunBalls() {
        return threeRunBalls;
    }

    public void setThreeRunBalls(int threeRunBalls) {
        this.threeRunBalls = threeRunBalls;
    }

    public int getFourRunBalls() {
        return fourRunBalls;
    }

    public void setFourRunBalls(int fourRunBalls) {
        this.fourRunBalls = fourRunBalls;
    }

    public int getSixRunBalls() {
        return sixRunBalls;
    }

    public void setSixRunBalls(int sixRunBalls) {
        this.sixRunBalls = sixRunBalls;
    }

    public int getCurrBall() {
        return currBall;
    }

    public void setCurrBall(int currBall) {
        this.currBall = currBall;
    }

    @Override
    public String toString() {
        return "Score{" +
                "position=" + position +
                ", dotRun=" + dotRun +
                ", oneRun=" + oneRun +
                ", twoRun=" + twoRun +
                ", threeRun=" + threeRun +
                ", fourRun=" + fourRun +
                ", sixRun=" + sixRun +
                ", totalRun=" + totalRun +
                ", isOut=" + isOut +
                ", strikeRate=" + strikeRate +
                ", wickets=" + wickets +
                ", dotBalls=" + dotBalls +
                ", oneRunBalls=" + oneRunBalls +
                ", twoRunBalls=" + twoRunBalls +
                ", threeRunBalls=" + threeRunBalls +
                ", fourRunBalls=" + fourRunBalls +
                ", sixRunBalls=" + sixRunBalls +
                ", currBall=" + currBall +
                '}';
    }
}
