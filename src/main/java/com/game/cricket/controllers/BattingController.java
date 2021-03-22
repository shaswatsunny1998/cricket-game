package com.game.cricket.controllers;


import com.game.cricket.models.Score;
import com.game.cricket.services.BatsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/matches/{matchId}/scores/batting/{playerId}")

public class BattingController {

    @Autowired
    BatsmanService batsmanService;

    @GetMapping("/")
    public Score getScore(@PathVariable("matchId") int matchId, @PathVariable("playerId") int playerId) {
        return batsmanService.allAttributes(matchId, playerId);
    }

    @GetMapping("/runs")
    public Map getTotalRuns(@PathVariable("matchId") int matchId, @PathVariable("playerId") int playerId) {
        return batsmanService.getTotalRun(matchId,playerId);
    }

    @GetMapping("/strikeRate")
    public Map getStrikeRate(@PathVariable("matchId") int matchId, @PathVariable("playerId") int playerId) {
        return batsmanService.getStrikeRate(matchId,playerId);
    }
    
}
