package com.game.cricket.services;

import com.game.cricket.doa.TeamDoa;
import com.game.cricket.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeamService {
    @Autowired
    TeamDoa teamDoa;

    @Autowired
    PlayerService playerService;


    public Team getFullTeam(int teamId, List<Integer> playerIds) {
        Team team = teamDoa.getTeam(teamId);
        team.setPlayers(playerService.getPlayers(playerIds));
        return team;
    }

}
