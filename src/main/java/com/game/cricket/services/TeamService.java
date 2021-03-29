package com.game.cricket.services;

import com.game.cricket.doa.TeamDoa;
import com.game.cricket.exceptions.PlayersIdException;
import com.game.cricket.exceptions.TeamInformationException;
import com.game.cricket.exceptions.TeamSizeException;
import com.game.cricket.models.Team;
import com.game.cricket.validators.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeamService {
    @Autowired
    TeamDoa teamDoa;

    @Autowired
    PlayerService playerService;

    @Autowired
    TeamValidator teamValidator;


    public Team getFullTeam(int teamId, List<Integer> playerIds){
            Team team;
            if(teamValidator.checkAllValidations(teamId,playerIds)){
                team = teamDoa.getTeam(teamId);
                team.setPlayers(playerService.getPlayers(playerIds));
                return team;
            }
            return null;
    }
}
