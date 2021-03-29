package com.game.cricket.validators;

import com.game.cricket.doa.TeamDoa;
import com.game.cricket.exceptions.PlayersIdException;
import com.game.cricket.exceptions.TeamInformationException;
import com.game.cricket.exceptions.TeamSizeException;
import com.game.cricket.models.Bowler;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;
import com.game.cricket.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamValidator {

    @Autowired
    TeamDoa teamDoa;

    @Autowired
    PlayerService playerService;

    public boolean isCorrectTeamSize(List list) {
        return list.size() == Team.TEAM_SIZE;
    }

    public boolean isAllPlayersReady(Team team) {
        return isCorrectTeamSize(team.getPlayers());
    }


    public boolean checkNumOfBowlers(Team team) {
        int bowlers = 0;
        List<Player> players = team.getPlayers();
        for (Player player: players) {
            if(player instanceof Bowler)
                bowlers++;
        }
        return bowlers>=2;
    }


    public boolean checkAllValidations(int teamId, List<Integer> playerIds) {
        try {
            Team team = teamDoa.getTeam(teamId);
            if (team == null) {
                throw new TeamInformationException("Team Id doesn't not exist: " + teamId);
            }

            team.setPlayers(playerService.getPlayers(playerIds));

            if (!isCorrectTeamSize(playerIds))
                throw new TeamSizeException("Team Size should be of size: " + Team.TEAM_SIZE);

            if (!isAllPlayersReady(team)) {
                throw new PlayersIdException("Some Player Ids not present in database ..check again :" + playerIds);
            }

            if(!checkNumOfBowlers(team))
            {
                throw new TeamSizeException("Please select at least 2 bowlers: " + playerIds);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
