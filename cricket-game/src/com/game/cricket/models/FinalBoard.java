package com.game.cricket.models;

public class FinalBoard {

    private String firstTeam;
    private String secondTeam;
    private boolean winByWicket;

    public FinalBoard() {
    }

    public FinalBoard(String firstTeam, String secondTeam, boolean winByWicket) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.winByWicket = winByWicket;
    }

    public String getfirstTeam() {
        return firstTeam;
    }

    public void setfirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getsecondTeam() {
        return secondTeam;
    }

    public void setsecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public boolean isWinByWicket() {
        return winByWicket;
    }

    public void setWinByWicket(boolean winByWicket) {
        this.winByWicket = winByWicket;
    }

    @Override
    public String toString() {
        return "FinalBoard{" +
                "firstTeam='" + firstTeam + '\'' +
                ", secondTeam='" + secondTeam + '\'' +
                ", winByWicket=" + winByWicket +
                '}';
    }
}
