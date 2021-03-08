package com.game.cricket;

public enum Runs {
    ONERUN(1),TWORUN(2),THREERUN(3),FOURRUN(4),SIXRUN(6),WICKET(-1);
    private int value;

    private Runs(int value)
    {
        this.value = value;
    }

    public int getRun(){
        return this.value;
    }

}
