package com.game.cricket.controllers;

import com.game.cricket.Match;
import com.game.cricket.MatchTransporter;
import com.game.cricket.doa.MatchDoa;
import com.game.cricket.doa.TeamDoa;
import com.game.cricket.services.FinalBoard;
import com.game.cricket.services.MatchService;
import com.game.cricket.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    MatchDoa matchDoa;

    @Autowired
    MatchService matchService;

    @Autowired
    MatchTransporter matchTransporter;


    @GetMapping("/{matchId}")
    public Match getMatchDetails(@PathVariable int matchId) {
        return matchDoa.getMatch(matchId);
    }

    //Can use the session to pass the match object from one controller to another.
    @PostMapping("/{teamId1}/{playerId1}/{teamId2}/{playerId2}")
    public Match getMatchTeams(@RequestBody Match match, @PathVariable("teamId1") int teamId1
            , @PathVariable("teamId2") int teamId2, @PathVariable("playerId1") List<Integer> playersId1
            , @PathVariable("playerId2") List<Integer> playersId2) {

        MatchTransporter.setMatch(matchService.addMatch(match, teamId1, playersId1, teamId2, playersId2));
        return MatchTransporter.getMatch();
    }

    //Is it stateless ? - not storing client information.
    @GetMapping("/get")
    public Match getMatch(){
        return MatchTransporter.getMatch();
    }

    @GetMapping("/start")
    public void startMatch(){
        Match match = MatchTransporter.getMatch();
        match.start();
        FinalBoard finalBoard = new FinalBoard(match);
        finalBoard.result();
        finalBoard.addDetails();
        System.out.println(match);
    }






}
