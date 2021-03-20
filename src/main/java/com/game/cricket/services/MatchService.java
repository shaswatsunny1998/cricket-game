package com.game.cricket.services;

import com.game.cricket.Match;
import com.game.cricket.MatchTransporter;
import com.game.cricket.doa.MatchDoa;
import com.game.cricket.doa.TeamPlayerDoa;
import com.game.cricket.models.MatchScheduler;
import com.game.cricket.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    TeamPlayerDoa teamPlayerDoa;

    @Autowired
    TeamService teamService;

    @Autowired
    MatchDoa matchDoa;

    @Autowired
    Match match1;

    @Autowired
    FinalBoard finalBoard;


    public Match addMatch(MatchScheduler match, int teamId1, List<Integer> playerId1, int teamId2, List<Integer> playerId2) {
        match1.setMatchId(match.getMatchId());
        match1.setMatchName(match.getMatchName());
        match1.setVenue(match.getVenue());
        match1.setMatchDate(match.getMatchDate());

        //match1 = new Match(match.getMatchId(),match.getMatchName(),match.getVenue(),match.getMatchDate());

        Team team1 = teamService.getFullTeam(teamId1, playerId1);
        Team team2 = teamService.getFullTeam(teamId2, playerId2);


        match1.setTeam1(team1);
        match1.setTeam2(team2);
        matchDoa.addMatch(match1);


        teamPlayerDoa.addTeamPlayer(match1.getMatchId(), team1);
        teamPlayerDoa.addTeamPlayer(match1.getMatchId(), team2);

        return match1;
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
