package com.game.cricket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MatchTransporter {

    private static Map<Integer, Match> matchMap = new ConcurrentHashMap<>();

    private Match match;

    public static void setMatch(Match match) {
        MatchTransporter.matchMap.put(match.getMatchId(), match);
    }

    public static Match getMatch(int matchId) {
        return matchMap.get(matchId);
    }
}
