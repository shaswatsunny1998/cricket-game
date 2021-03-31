package com.game.cricket.models;

import java.util.List;

public class OverDto {
    private int matchId;
    private int bowlerId;
    private List<Integer> runs;

    public OverDto() {
    }

    public OverDto(int matchId, int bowlerId, List<Integer> runs) {
        this.matchId = matchId;
        this.bowlerId = bowlerId;
        this.runs = runs;
    }
}
