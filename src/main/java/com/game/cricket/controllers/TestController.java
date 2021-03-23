package com.game.cricket.controllers;


import com.game.cricket.Match;
import com.game.cricket.MatchTransporter;
import com.game.cricket.services.FinalBoard;
import com.game.cricket.testcases.CheckMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches/test")
public class TestController {

    @Autowired
    FinalBoard finalBoard;

    @GetMapping("/{matchId}")
    public void testScores(@PathVariable int matchId)
    {
        Match match = MatchTransporter.getMatch(matchId);
        match.start();

        finalBoard.setMatch(match);
        finalBoard.setFirstTeam(match.getTeam1());
        finalBoard.setSecondTeam(match.getTeam2());
        finalBoard.result();

        CheckMatch checkMatch = new CheckMatch();
        checkMatch.checkScores(match);
    }
}
