package com.game.cricket.services;

import com.game.cricket.doa.BatScoreDoa;
import com.game.cricket.models.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BatsmanService {
    @Autowired
    BatScoreDoa batScoreDoa;

    public Score allAttributes(int matchId, int playerId){
        return batScoreDoa.getBattingScore(matchId,playerId);
    }

    public Map getTotalRun(int matchId, int playerId) {
        Score score = allAttributes(matchId, playerId);
        Map<String , Integer> map= new HashMap();
        map.put("runsScored",score.getTotalRun());
        return map;
    }

    public Map getStrikeRate(int matchId, int playerId) {
        Score score = allAttributes(matchId,playerId);
        Map<String,Double> map = new HashMap<>();
        map.put("strikeRate",score.getTotalStrikeRate());
        return map;
    }



}
