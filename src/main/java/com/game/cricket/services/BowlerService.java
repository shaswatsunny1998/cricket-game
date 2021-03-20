package com.game.cricket.services;

import com.game.cricket.doa.BallScoreDoa;
import com.game.cricket.models.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BowlerService {

    @Autowired
    BallScoreDoa ballScoreDoa;

    public Score allAttributes(int matchId,int playerId){
        return ballScoreDoa.getBowlingScore(matchId,playerId);
    }


    public Map getEcon(int matchId, int playerId) {
        Map<String,Double> map = new HashMap<>();
        Score score=allAttributes(matchId, playerId);
        double overs = score.getOvers();
        if(overs==0){
            map.put("economy",0.0);
            return map;
        }
        double runs =score.getTotalRunsBowlGiven();
        map.put("economy",runs/overs);
        return map;
    }


    public Map getTotalOvers(int matchId, int playerId) {
        Map<String,Double> map = new HashMap<>();
        Score score=allAttributes(matchId, playerId);
        double overs = score.getOvers();
        map.put("overs",overs);
        return map;
    }


    public Map getTotalRuns(int matchId, int playerId) {
        Map<String,Integer> map = new HashMap<>();
        Score score=allAttributes(matchId, playerId);
        int runs = score.getTotalRunsBowlGiven();
        map.put("totalRuns",runs);
        return map;
    }






    //DON'T NEED THESE
    public int getWickets(int matchId,int playerId){
        Score score = allAttributes(matchId, playerId);
        return score.getWickets();
    }

    public int getDotBalls(int matchId,int playerId){
        Score score = allAttributes(matchId, playerId);
        return score.getDotBalls();
    }

    public int getOneBalls(int matchId, int playerId) {
        Score score = allAttributes(matchId, playerId);
        return score.getOneRunBalls();
    }

    public int getTwoBalls(int matchId, int playerId) {
        Score score = allAttributes(matchId, playerId);
        return score.getTwoRunBalls();
    }
    public int getFourBalls(int matchId, int playerId) {
        Score score = allAttributes(matchId, playerId);
        return score.getFourRunBalls();
    }

    public int getSixBalls(int matchId, int playerId) {
        Score score = allAttributes(matchId, playerId);
        return score.getSixRunBalls();
    }

}
