package com.game.cricket.models;

import java.util.List;

public class RunsDto {
    private List<Integer> runs;


    public RunsDto() {
    }

    public RunsDto(List<Integer> runs) {
        this.runs = runs;
    }

    public List<Integer> getRuns() {
        return runs;
    }

    public void setRuns(List<Integer> runs) {
        this.runs = runs;
    }
}
