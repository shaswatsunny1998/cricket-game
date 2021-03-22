package com.game.cricket.controllers;

import com.game.cricket.Match;
import com.game.cricket.MatchTransporter;
import com.game.cricket.models.MatchScheduler;
import com.game.cricket.services.BowlerService;
import com.game.cricket.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/matches")
@Validated
public class MatchController {

    @Autowired
    MatchService matchService;

    @Autowired
    MatchTransporter matchTransporter;

    @Autowired
    BowlerService bowlerService;

    @PostMapping("/schedule")
    public Match getMatchTeams(@Valid @RequestBody MatchScheduler matchScheduler) {
        Match match = matchService.addMatch(matchScheduler, matchScheduler.getTeamId1(),
                matchScheduler.getPlayerIds1(), matchScheduler.getTeamId2(), matchScheduler.getPlayerIds2());
        matchTransporter.setMatch(match.getMatchId(), match);
        return matchTransporter.getMatch(matchScheduler.getMatchId());
    }

    @GetMapping("/get/{matchId}")
    public Match getMatch(@PathVariable int matchId) {
        return matchTransporter.getMatch(matchId);
    }

    @GetMapping("/start/{matchId}")
    public Match startMatch(@PathVariable int matchId) {

        return matchService.startMatch(matchId);
    }

    @GetMapping("/matchTransporter")
    public Map getMatchTransporter() {
        return matchTransporter.getMatchMap();
    }

    @GetMapping("/matchSummary/{matchId}")
    public Map matchSummary(@PathVariable int matchId)
    {
        return matchService.getMatchSummary(matchId);
    }


}
