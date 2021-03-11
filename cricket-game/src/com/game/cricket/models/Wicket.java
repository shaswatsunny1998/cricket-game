package com.game.cricket.models;

public class Wicket {
    private String batsman;
    private String bowler;
    private String type;
    private int ballNo;

    public Wicket(String batsman, String bowler, int ballNo) {
        this.batsman = batsman;
        this.bowler = bowler;
        this.ballNo = ballNo;
        this.type = "Bowled";
    }


    public String getBatsman() {
        return batsman;
    }

    public String getBowler() {
        return bowler;
    }

    public String getType() {
        return type;
    }

    public int getBallNo() {
        return ballNo;
    }

    @Override
    public String toString() {
        return "Wicket{" +
                "batsman='" + batsman + '\'' +
                ", bowler='" + bowler + '\'' +
                ", type='" + type + '\'' +
                ", ballNo=" + ballNo +
                '}';
    }
}

