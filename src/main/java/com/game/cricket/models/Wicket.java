package com.game.cricket.models;

public class Wicket {
    private int batsman;
    private int bowler;
    private String type;
    private int ballNo;

    public Wicket(int batsman, int bowler, int ballNo) {
        this.batsman = batsman;
        this.bowler = bowler;
        this.ballNo = ballNo;
        this.type="Bowled";
    }

    public void setBatsman(int batsman) {
        this.batsman = batsman;
    }

    public void setBowler(int bowler) {
        this.bowler = bowler;
    }

    public int getBatsman() {
        return batsman;
    }

    public int getBowler() {
        return bowler;
    }

    @Override
    public String toString() {
        return "Wicket{" +
                "batsman=" + batsman +
                ", bowler=" + bowler +
                ", type='" + type + '\'' +
                ", ballNo=" + ballNo +
                '}';
    }

    public String getType() {
        return type;
    }

    public int getBallNo() {
        return ballNo;
    }

}

