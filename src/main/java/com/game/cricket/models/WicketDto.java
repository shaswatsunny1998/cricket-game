package com.game.cricket.models;


public class WicketDto {
    private int overNo;
    private boolean firstHalf;
    private Wicket wicket;


    public WicketDto(int overNo, boolean firstHalf, Wicket wicket) {
        this.overNo = overNo;
        this.firstHalf = firstHalf;
        this.wicket = wicket;
    }


    public int getOverNo() {
        return overNo;
    }

    public void setOverNo(int overNo) {
        this.overNo = overNo;
    }

    public boolean isFirstHalf() {
        return firstHalf;
    }

    public void setFirstHalf(boolean firstHalf) {
        this.firstHalf = firstHalf;
    }

    public Wicket getWicket() {
        return wicket;
    }

    public void setWicket(Wicket wicket) {
        this.wicket = wicket;
    }
}
