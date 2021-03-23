package com.game.cricket.controllers;

import com.game.cricket.doa.BallsDoa;
import com.game.cricket.models.Ball;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/balls/match/{matchId}")
public class BallsController {
    @Autowired
    BallsDoa ballsDoa;

    @GetMapping("/")
    public Map getBalls(@PathVariable int matchId)
    {
        Map<String,List<Ball>> map = new HashMap<>();
        List<Ball> ballsFirsthalf = ballsDoa.getBalls(matchId, true);
        List<Ball> ballsSecondhalf = ballsDoa.getBalls(matchId, false);
        map.put("firstHalf", ballsFirsthalf);
        map.put("secondHalf", ballsSecondhalf);
        return map;
    }

    @GetMapping("/bowler/{playerId}")
    public List<Ball> getBallsBowler(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId)
    {
        return ballsDoa.getBallsPlayer(matchId,playerId,false);
    }

    @GetMapping("/batsman/{playerId}")
    public List<Ball> getBallsBatsman(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId)
    {
        return ballsDoa.getBallsPlayer(matchId,playerId,true);
    }
}
