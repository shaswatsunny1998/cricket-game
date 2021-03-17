package com.game.cricket.services;

import com.game.cricket.Match;
import com.game.cricket.doa.MatchDoa;
import com.game.cricket.doa.TeamPlayerDoa;
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

    public Match addMatch(Match match, int teamId1, List<Integer> playerId1, int teamId2,List<Integer>playerId2) {
        Match match1 = new Match(match.getMatchId(),match.getMatchName(),match.getVenue(),match.getMatchDate());
        Team team1 = teamService.getFullTeam(teamId1,playerId1);
        Team team2 = teamService.getFullTeam(teamId2,playerId2);
        match1.setTeam1(team1);
        match1.setTeam2(team2);
        matchDoa.addMatch(match1);
        teamPlayerDoa.addTeamPlayer(match1.getMatchId(),team1);
        teamPlayerDoa.addTeamPlayer(match1.getMatchId(),team2);
        return match1;
    }
}
