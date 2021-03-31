package com.game.cricket.services;

import com.game.cricket.Match;
import com.game.cricket.MatchTransporter;
import com.game.cricket.doa.*;
import com.game.cricket.exceptions.MatchIdException;
import com.game.cricket.exceptions.TeamInformationException;
import com.game.cricket.models.*;
import com.game.cricket.util.RandomGenerator;
import com.game.cricket.validators.MatchValidator;
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

    @Autowired
    WicketService wicketService;


    @Autowired
    ScoreBoardDoa scoreBoardDoa;

    @Autowired
    MatchValidator matchValidator;


    public Match addMatch(MatchScheduler match, int teamId1, @NotEmpty List<Integer> playerId1, int teamId2, @NotEmpty List<Integer> playerId2) {

        try {

            if (matchValidator.matchPresent(match))
                return null;
            Match match1 = new Match();
            match1.setMatchId(match.getMatchId());
            match1.setMatchName(match.getMatchName());
            match1.setVenue(match.getVenue());
            match1.setMatchDate(match.getMatchDate());
            match1.initializeOvers();


            Team team1 = teamService.getFullTeam(teamId1, playerId1);
            if (team1 == null)
                throw new TeamInformationException("TeamId: " + teamId1 + " is not ready");

            Team team2 = teamService.getFullTeam(teamId2, playerId2);
            if (team2 == null)
                throw new TeamInformationException("TeamId: " + teamId2 + " is not ready");


            //Coin tossed
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

            // Only for User control feature
            match1.initializeOvers();
            match1.setFirstHalfInning(new UserControlInning(match1.getTeam1(), match1.getTeam2()));
            match1.setSecondHalfInning(new UserControlInning(match1.getTeam2(), match1.getTeam1()));


            return match1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Map getMatchSummary(int matchId) throws MatchIdException {

        Match match = matchDoa.getMatch(matchId);

        if (match == null) {
            throw new MatchIdException("Match id not Available: " + matchId);
        }

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

        team1.setTotal_score(scoreBoardDoa.getScore(matchId, teamId1));
        team2.setTotal_score(scoreBoardDoa.getScore(matchId, teamId2));

        match.setTeam1(team1);
        match.setTeam2(team2);


        List<Ball> ballsFirsthalf = ballsDoa.getBalls(matchId, true);
        List<Ball> ballsSecondhalf = ballsDoa.getBalls(matchId, false);


        matchSummary.put("matchSummary", match);
        matchSummary.put("firstHalf", ballsFirsthalf);
        matchSummary.put("secondHalf", ballsSecondhalf);
        matchSummary.put("wickets", wicketService.getWicketDtos(matchId));
        return matchSummary;
    }


    public Match startMatch(int matchId) {
        try {
            Match match = MatchTransporter.getMatch(matchId);
            if (match == null)
                throw new MatchIdException("Match not Scheduled with matchId: " + matchId);
            match.start();

            finalBoard.setMatch(match);
            finalBoard.setFirstTeam(match.getTeam1());
            finalBoard.setSecondTeam(match.getTeam2());

            finalBoard.result();

            finalBoard.addDetails();
            System.out.println(match);

            return match;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Match startFirstHalf(int matchId) {
        try {
            Match match = MatchTransporter.getMatch(matchId);
            if (match == null)
                throw new MatchIdException("Match not Scheduled with matchId: " + matchId);
            match.startFirstHalf();
            return match;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Match startSecondHalf(int matchId) {
        try {
            Match match = MatchTransporter.getMatch(matchId);
            if (match == null)
                throw new MatchIdException("Match not Scheduled with matchId: " + matchId);
            match.startSecondHalf();
            finalBoard.setMatch(match);
            finalBoard.setFirstTeam(match.getTeam1());
            finalBoard.setSecondTeam(match.getTeam2());

            finalBoard.result();

            finalBoard.addDetails();
            System.out.println(match);
            return match;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }




    public Over firstHalfOvers(int matchId, int bowlerId, List<Integer> runs) {
        try {
            Match match = MatchTransporter.getMatch(matchId);
            if (match == null)
                throw new MatchIdException("Match not Scheduled with matchId: " + matchId);
            return match.getFirstHalfInning().singleInningOver(matchId, bowlerId, match.getFirstHalfOvers(), false, 0, runs);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public Over secondHalfOvers(int matchId, int bowlerId, List<Integer> runs) {
        try {
            Match match = MatchTransporter.getMatch(matchId);
            if (match == null)
                throw new MatchIdException("Match not Scheduled with matchId: " + matchId);
            Over over = match.getSecondHalfInning().singleInningOver(matchId, bowlerId, match.getFirstHalfOvers(), true, match.getTeam1().getTotal_score(), runs);

            if(over.getBalls().size()!=Over.NUM_OF_BALLS || match.getSecondHalfOvers().size()==Match.NUM_OF_OVERS)
            {
                System.out.println("--------------------Match Over-----------------");
                finalBoard.setMatch(match);
                finalBoard.setFirstTeam(match.getTeam1());
                finalBoard.setSecondTeam(match.getTeam2());
                finalBoard.result();
                finalBoard.addDetails();

                System.out.println(match);

                match.setFinished(true);
            }
            return over;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public UserControlInning getFirstHalfOver(int matchId) {
        try {
            Match match = MatchTransporter.getMatch(matchId);
            if (match == null)
                throw new MatchIdException("Match not Scheduled with matchId: " + matchId);
            return match.getFirstHalfInning();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public UserControlInning getSecondHalfOver(int matchId) {
        try {
            Match match = MatchTransporter.getMatch(matchId);
            if (match == null)
                throw new MatchIdException("Match not Scheduled with matchId: " + matchId);
            return match.getSecondHalfInning();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


}
