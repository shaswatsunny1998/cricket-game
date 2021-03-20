package com.game.cricket.controllers;

import com.game.cricket.models.Score;
import com.game.cricket.services.BowlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/matches/{matchId}/scores/bowling/{playerId}")

public class BowlerController {

    @Autowired
    BowlerService bowlerService;

    @GetMapping("/")
    public Score getScore(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId)
    {
        return bowlerService.allAttributes(matchId,playerId);
    }

    @GetMapping("/econ")
    public Map getEconomyRate(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId)
    {
        return bowlerService.getEcon(matchId,playerId);
    }


    @GetMapping("/totalruns")
    public Map getTotalRuns(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId){
        return bowlerService.getTotalRuns(matchId,playerId);
    }

    @GetMapping("/overs")
    public Map getTotalOvers(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId){
        return bowlerService.getTotalOvers(matchId,playerId);
    }





    // DON'T NEED THESE CONTROLLERS
    @GetMapping("/wickets")
    public int getWickets(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId)
    {
        return bowlerService.getWickets(matchId, playerId);

    }

    @GetMapping("/dotballs")
    public int getDotBalls(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId){
        return bowlerService.getDotBalls(matchId, playerId);
    }

    @GetMapping("/onerunballs")
    public int getOneBalls(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId){
        return bowlerService.getOneBalls(matchId, playerId);
    }


    @GetMapping("/tworunballs")
    public int getTwoBalls(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId){
        return bowlerService.getTwoBalls(matchId, playerId);
    }

    @GetMapping("/fourrunballs")
    public int getThreeBalls(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId){
        return bowlerService.getFourBalls(matchId, playerId);
    }
    @GetMapping("/sixrunballs")
    public int getSixBalls(@PathVariable("matchId") int matchId , @PathVariable("playerId") int playerId){
        return bowlerService.getSixBalls(matchId, playerId);
    }


}
