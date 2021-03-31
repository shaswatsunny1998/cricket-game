package com.game.cricket.controllers;

import com.game.cricket.Match;
import com.game.cricket.MatchTransporter;
import com.game.cricket.exceptions.MatchIdException;
import com.game.cricket.models.MatchScheduler;
import com.game.cricket.models.Over;
import com.game.cricket.models.RunsDto;
import com.game.cricket.models.UserControlInning;
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
        if (match == null)
            return null;
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

    @GetMapping("/start/firstHalf/{matchId}")
    public Match startFirstHalf(@PathVariable int matchId) {
        return matchService.startFirstHalf(matchId);
    }

    @GetMapping("/start/secondHalf/{matchId}")
    public Match startsecondHalf(@PathVariable int matchId) {
        return matchService.startSecondHalf(matchId);
    }

    @GetMapping("/matchTransporter")
    public Map getMatchTransporter() {
        return matchTransporter.getMatchMap();
    }

    @GetMapping("/matchSummary/{matchId}")
    public Map matchSummary(@PathVariable int matchId) throws MatchIdException {
        return matchService.getMatchSummary(matchId);
    }

    @PostMapping("/{matchId}/nextOverFirst/{bowlerId}")
    public Over getNextOver(@PathVariable("matchId") int matchId, @PathVariable("bowlerId") int bowlerId,
                            @RequestBody RunsDto runsDto) {
        return matchService.firstHalfOvers(matchId, bowlerId, runsDto.getRuns());
    }

    @PostMapping("/{matchId}/nextOverSecond/{bowlerId}")
    public Over getNextOverSecond(@PathVariable("matchId") int matchId, @PathVariable("bowlerId") int bowlerId,
                            @RequestBody RunsDto runsDto) {
        return matchService.secondHalfOvers(matchId, bowlerId, runsDto.getRuns());
    }

    @GetMapping("{matchId}/firstHalfInning")
    public UserControlInning getInning(@PathVariable int matchId) {
        return matchService.getFirstHalfOver(matchId);
    }

    @GetMapping("{matchId}/secondHalfInning")
    public UserControlInning getSecondInning(@PathVariable int matchId) {
        return matchService.getSecondHalfOver(matchId);
    }

}
