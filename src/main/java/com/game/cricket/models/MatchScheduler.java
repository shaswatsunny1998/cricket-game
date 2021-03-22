package com.game.cricket.models;

import java.util.Date;
import java.util.List;


public class MatchScheduler {
    private int matchId;
    private String matchName;
    private String venue;
    private Date matchDate;

    private int teamId1;
    private int teamId2;

    private List<Integer> playerIds1;
    private List<Integer> playerIds2;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public int getTeamId1() {
        return teamId1;
    }

    public void setTeamId1(int teamId1) {
        this.teamId1 = teamId1;
    }

    public int getTeamId2() {
        return teamId2;
    }

    public void setTeamId2(int teamId2) {
        this.teamId2 = teamId2;
    }

    public List<Integer> getPlayerIds1() {
        return playerIds1;
    }

    public void setPlayerIds1(List<Integer> playerIds1) {
        this.playerIds1 = playerIds1;
    }

    public List<Integer> getPlayerIds2() {
        return playerIds2;
    }

    public void setPlayerIds2(List<Integer> playerIds2) {
        this.playerIds2 = playerIds2;
    }
}
