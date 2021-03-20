package com.game.cricket;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MatchTransporter {

    private static Map<Integer, Match> matchMap = new ConcurrentHashMap<>();

    public void setMatch(int matchId, Match match) {
        matchMap.put(matchId, match);
    }

    public static Match getMatch(int matchId) {
        return matchMap.get(matchId);
    }

    public Map<Integer, Match> getMatchMap() {
        return matchMap;
    }

}
