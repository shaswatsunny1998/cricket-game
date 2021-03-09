package com.game.testcases;

import com.game.cricket.Match;
import com.game.cricket.models.Over;
import com.game.cricket.models.Player;
import com.game.cricket.models.Team;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckMatch {
    public int getAllOverScores(List<Over> overs) {
        int sum = 0;
        for (Over over : overs) {
            sum += over.getTotalRun();
        }
        return sum;
    }

    public int getAllWickets(List<Over> overs) {
        int sum = 0;
        for (Over over : overs) {
            sum += over.getWicket();
        }
        return sum;
    }

    public int totalWickets(Team team){
        int wick=0;
        for (Player player: team.getPlayers()) {
            if (player.getScore().isOut())
                wick+=1;
        }
        return wick;
    }

    @Test
    public void checkScores() {
        Match match = new Match("123", "Ind vs Aus");
        Scanner scan = new Scanner(System.in);
        match.addAllTeams(scan);
        match.start();
        int firstHalf = getAllOverScores(match.getFirstHalfOvers());
        int secondHalf = getAllOverScores(match.getSecondHalfOvers());

        assertEquals(firstHalf, match.getTeam1().getTotal_score());
        assertEquals(secondHalf, match.getTeam2().getTotal_score());
        assertTrue(match.getFirstHalfOvers().size() <= Match.NUM_OF_OVERS);
        assertTrue(match.getSecondHalfOvers().size() <= Match.NUM_OF_OVERS);
        assertEquals(totalWickets(match.getTeam1()),getAllWickets(match.getFirstHalfOvers()));
        assertEquals(totalWickets(match.getTeam2()),getAllWickets(match.getSecondHalfOvers()));


    }
}

