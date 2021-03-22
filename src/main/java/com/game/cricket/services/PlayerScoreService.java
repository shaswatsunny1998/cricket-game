package com.game.cricket.services;

import com.game.cricket.doa.BallScoreDoa;
import com.game.cricket.doa.BatScoreDoa;
import com.game.cricket.models.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerScoreService {
    @Autowired
    BatScoreDoa batScoreDoa;

    @Autowired
    BallScoreDoa ballScoreDoa;


    public Score mergeScores(int matchId,int playerId)
    {
        Score bowlingScore = ballScoreDoa.getBowlingScore(matchId, playerId);
        Score battingScore = batScoreDoa.getBattingScore(matchId,playerId);
        battingScore.setScores(bowlingScore);
        return battingScore;
    }
}
