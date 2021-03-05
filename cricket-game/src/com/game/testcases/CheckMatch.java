package com.game.testcases;
import com.game.cricket.Match;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CheckMatch {

    @Test
    public void checkScores(){
        Match match = new Match("123", "Ind vs Aus");
        Scanner scan = new Scanner(System.in);
        match.addAllTeams(scan);
        match.start();

        int batting_score=0;
        int bowling_score=0;

        List<Player> players1=match.getTeam1().getPlayers();
        List<Player> players2=match.getTeam2().getPlayers();

        for(int i=0;i< Team.TEAM_SIZE;++i){
            batting_score+=players1.get(i).getBatsman().getTotalRun();
            batting_score+=players2.get(i).getBatsman().getTotalRun();

            bowling_score+=players1.get(i).getBowler().getTotalRuns();
            bowling_score+=players2.get(i).getBowler().getTotalRuns();
        }

        assertEquals(batting_score,bowling_score);
    }
}
