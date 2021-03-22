package com.game.cricket.controllers;


import com.game.cricket.models.Score;
import com.game.cricket.services.PlayerScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches/{matchId}/totalScores/{playerId}")
public class PlayerScoresController {

    @Autowired
    PlayerScoreService playerScoreService;

    @GetMapping("/")
    public Score getDetails(@PathVariable("matchId") int matchId,@PathVariable("playerId") int playerId){
        return playerScoreService.mergeScores(matchId, playerId);
    }

}
