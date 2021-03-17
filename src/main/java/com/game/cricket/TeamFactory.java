package com.game.cricket;

import com.game.cricket.doa.PlayersDoa;
import com.game.cricket.doa.TeamDoa;
import com.game.cricket.doa.TeamPlayerDoa;
import com.game.cricket.models.Batsman;
import com.game.cricket.models.Bowler;
import com.game.cricket.models.Team;

import java.util.Arrays;

public class TeamFactory {

    public Team getIndianTeam() {
        Team team = new Team("123", "India");
        team.setPlayers(Arrays.asList(
                new Bowler("Shaswat", "Srivastava", 22),
                new Batsman("Tiger", "Srivastava", 34),
                new Bowler("Dhoni", "Srivastava", 45)
        ));
        return team;
    }

    public Team getAustraliaTeam() {
        Team team = new Team("345", "Australia");
        team.setPlayers(Arrays.asList(
                new Bowler("Ricky", "Aus", 45),
                new Bowler("Vir", "Aus", 34),
                new Batsman("Tanmay", "Aus", 23)
        ));
        return team;
    }

    public void addTeamsDatabase(){

        Team indian = this.getIndianTeam();
        Team aus = this.getAustraliaTeam();

        TeamDoa teamDoa = new TeamDoa();
        teamDoa.addTeam(indian);
        teamDoa.addTeam(aus);

        TeamPlayerDoa teamPlayerDoa = new TeamPlayerDoa();
        //teamPlayerDoa.addTeamPlayer(indian);
        //teamPlayerDoa.addTeamPlayer(aus);

        PlayersDoa playersDoa = new PlayersDoa();
        playersDoa.addPlayers(indian);
        playersDoa.addPlayers(aus);
    }

}
