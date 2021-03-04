package com.game.cricket.models;

public class FinalBoard {

    private String wonTeam;
    private String looseTeam;
    private boolean winByWicket;

    public FinalBoard(String wonTeam, String looseTeam, boolean winByWicket) {
        this.wonTeam = wonTeam;
        this.looseTeam = looseTeam;
        this.winByWicket = winByWicket;
    }

    public String getWonTeam() {
        return wonTeam;
    }

    public void setWonTeam(String wonTeam) {
        this.wonTeam = wonTeam;
    }

    public String getLooseTeam() {
        return looseTeam;
    }

    public void setLooseTeam(String looseTeam) {
        this.looseTeam = looseTeam;
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
                "wonTeam='" + wonTeam + '\'' +
                ", looseTeam='" + looseTeam + '\'' +
                ", winByWicket=" + winByWicket +
                '}';
    }
}
