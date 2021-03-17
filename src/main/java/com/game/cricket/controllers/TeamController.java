package com.game.cricket.controllers;


import com.game.cricket.doa.TeamDoa;
import com.game.cricket.models.Team;
import com.game.cricket.services.PlayerService;
import com.game.cricket.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamDoa teamDoa;

    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;


    @GetMapping("/listTeams")
    public List<Team> listTeams() {
        return teamDoa.getTeams();
    }

    @PostMapping("/registerTeam")
    public void addTeam(@RequestBody Team team) {
        teamDoa.addTeam(team);
    }

    @DeleteMapping("/deleteTeam/{teamId}")
    public void deleteTeam(@PathVariable String teamId) {
        teamDoa.deleteTeam(teamId);
    }

    @PutMapping("/updateTeam/{teamId}")
    public void updateTeamName(@PathVariable String teamId, @RequestBody Team team) {
        teamDoa.updateTeam(team, teamId);
    }

    @GetMapping("/{teamId}/{playerids}")
    public Team getTeam(@PathVariable int teamId, @PathVariable List<Integer> playerids) {
        return teamService.getFullTeam(teamId,playerids);
    }
}
