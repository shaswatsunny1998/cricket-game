package com.game.cricket.services;

import com.game.cricket.Match;
import com.game.cricket.MatchTransporter;
import com.game.cricket.doa.*;
import com.game.cricket.models.Ball;
import com.game.cricket.models.MatchScheduler;
import com.game.cricket.models.Team;
import com.game.cricket.util.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    @Autowired
    TeamPlayerDoa teamPlayerDoa;

    @Autowired
    TeamService teamService;

    @Autowired
    PlayersDoa playersDoa;

    @Autowired
    TeamDoa teamDoa;

    @Autowired
    MatchDoa matchDoa;

    @Autowired
    HalfInningDoa halfInningDoa;

    @Autowired
    FinalBoard finalBoard;

    @Autowired
    RandomGenerator randomGenerator;

    @Autowired
    PlayerService playerService;

    @Autowired
    BallsDoa ballsDoa;


    public Match addMatch(MatchScheduler match, int teamId1, @NotEmpty List<Integer> playerId1, int teamId2, @NotEmpty List<Integer> playerId2) {
        Match match1 = new Match();
        match1.setMatchId(match.getMatchId());
        match1.setMatchName(match.getMatchName());
        match1.setVenue(match.getVenue());
        match1.setMatchDate(match.getMatchDate());
        match1.initializeOvers();

        Team team1 = teamService.getFullTeam(teamId1, playerId1);
        Team team2 = teamService.getFullTeam(teamId2, playerId2);

        if (randomGenerator.getRandomCoin() == 0) {
            match1.setTeam1(team1);
            match1.setTeam2(team2);
        } else {
            match1.setTeam2(team1);
            match1.setTeam1(team2);
        }

        matchDoa.addMatch(match1);
        teamPlayerDoa.addTeamPlayer(match1.getMatchId(), team1);
        teamPlayerDoa.addTeamPlayer(match1.getMatchId(), team2);

        return match1;
    }

    public Map getMatchSummary(int matchId) {
        Match match = matchDoa.getMatch(matchId);

        Map matchSummary = new HashMap<>();

        Map map = halfInningDoa.getInning(matchId);
        int teamId1 = (int) map.get("battingTeam");
        int teamId2 = (int) map.get("bowlingTeam");


        List<Integer> playersId1 = playersDoa.getPlayers(matchId, teamId1);
        List<Integer> playersId2 = playersDoa.getPlayers(matchId, teamId2);

        Team team1 = teamService.getFullTeam(teamId1, playersId1);
        Team team2 = teamService.getFullTeam(teamId2, playersId2);

        playerService.setPlayersScore(team1.getPlayers(), matchId);
        playerService.setPlayersScore(team2.getPlayers(), matchId);

        match.setTeam1(team1);
        match.setTeam2(team2);


        List<Ball> ballsFirsthalf = ballsDoa.getBalls(matchId,true);
        List<Ball> ballsSecondhalf = ballsDoa.getBalls(matchId,false);

        matchSummary.put("matchSummary", match);
        matchSummary.put("firstHalf", ballsFirsthalf);
        matchSummary.put("secondHalf", ballsSecondhalf);
        return matchSummary;
    }


    public Match startMatch(int matchId) {
        Match match = MatchTransporter.getMatch(matchId);
        match.start();

        finalBoard.setMatch(match);
        finalBoard.setFirstTeam(match.getTeam1());
        finalBoard.setSecondTeam(match.getTeam2());

        finalBoard.result();

        finalBoard.addDetails();
        System.out.println(match);

        return match;
    }


}
