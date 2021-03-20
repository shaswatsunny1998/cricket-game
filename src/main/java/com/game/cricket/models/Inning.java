package com.game.cricket.models;

import com.game.cricket.Match;
import com.game.cricket.doa.BallScoreDoa;
import com.game.cricket.doa.BallsDoa;
import com.game.cricket.doa.BatScoreDoa;
import com.game.cricket.doa.WicketDoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Inning {


    @Autowired
    BallsDoa ballsDoa;

    @Autowired
    WicketDoa wicketDoa;

    @Autowired
    BatScoreDoa batScoreDoa;

    @Autowired
    BallScoreDoa ballScoreDoa;


    BowlerSelector bowlerSelector;
    BatsmanSelector batsmanSelector;

    public Inning() {
        bowlerSelector = new BowlerSelector();
        batsmanSelector = new BatsmanSelector();
    }

    public void singleInning(int matchId, Team batting_team, Team bowling_team, int chaseScore, List<Over> overs, boolean isChasing) {

        int totalScoreTeam = batting_team.getTotal_score();

        int currBatsman = 0;
        List<Player> batting_players = batsmanSelector.getBattingPlayers(batting_team.getPlayers());

        int currBowler = 0;
        List<Player> bowling_players = bowlerSelector.getBowlingPlayers(bowling_team.getPlayers());

        Player batting = batting_players.get(currBatsman);
        currBatsman++;
        Player running = batting_players.get(currBatsman);
        Player neutral;

        int currOver = 0;

        Over over = new Over();

        while (currBatsman <= Team.TEAM_SIZE - 1 && currOver < Match.NUM_OF_OVERS) {
            System.out.println("Initial On Batting Side : " + batting.getFirstName());
            System.out.println("Initial On Running Side : " + running.getFirstName());

            Player bowler = bowling_players.get(currBowler);

            System.out.println("Bowler Bowling: " + bowler.getFirstName());

            if (over.getCurrBall() < Over.NUM_OF_BALLS) {

            } else {
                over = new Over();
            }


            for (int i = bowler.getScore().getCurrBall(); i < Over.NUM_OF_BALLS; ++i) {

                //Batsman has high Scoring Probability.
                int int_random = getRun(batting);

                Ball ball = new Ball(int_random);
                ball.setBatsmanId(batting.getPlayerId());
                ball.setBowlerId(bowler.getPlayerId());

                over.setCurrBall(over.getCurrBall() + 1);
                over.getBalls().add(ball);


                //Balls Added
                ballsDoa.addBall(matchId, currOver + 1, !isChasing, i + 1, int_random, batting.getPlayerId(), bowler.getPlayerId());

                if (int_random != -1) {

                    totalScoreTeam += int_random;
                    batting_team.setTotal_score(totalScoreTeam);

                    batting.getScore().setRun(int_random);


                    bowler.getScore().setRunBowl(int_random);
                    bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);


                    //Bowler Score Added -
                    ballScoreDoa.addBowlingScore(matchId, bowler);

                    //Batting Score Added -
                    batScoreDoa.addBattingScore(matchId, batting);


                    if (int_random % 2 != 0) {
                        neutral = batting;
                        batting = running;
                        running = neutral;
                    }
                    over.setRun(int_random);
                    System.out.println("Run at Ball " + (int) (i + 1) + ": " + int_random);
                    System.out.println("After ball On Batting Side : " + batting.getFirstName());
                    System.out.println("After ball On Running Side : " + running.getFirstName());

                    if (isChasing) {
                        if (batting_team.getTotal_score() > chaseScore) {
                            over.setTotalRun();
                            over.setPlayerId(bowler.getPlayerId());
                            overs.add(over);
                            return;
                        }
                    }

                    //TODO In second half don't add the total runs to the batting player side(if run by wicket).

                } else {
                    bowler.getScore().setWickets(bowler.getScore().getWickets() + 1);
                    System.out.println(bowler.getScore().getOvers());

                    System.out.println(bowler.getScore().getTotalRunsBowlGiven());
                    batting.getScore().setOut(true);

                    bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);

                    System.out.println("Out at Ball: " + bowler.getScore().getCurrBall());
                    System.out.println("Out with total run: " + batting.getScore().getTotalRun());


                    //Wickets Added
                    wicketDoa.addWicket(matchId, currOver + 1, !isChasing, i + 1, batting.getPlayerId(), bowler.getPlayerId());


                    //Bowler Score Added -
                    ballScoreDoa.addBowlingScore(matchId, bowler);


                    over.setWicket(over.getWicket() + 1);

                    running.getScore().getTotalRun();

                    //Indexing is Odd.
                    over.addWicket(batting.getPlayerId(), bowler.getPlayerId(), over.getCurrBall());

                    currBatsman++;

                    if (!batting_team.isLastPlayer(currBatsman)) {
                        batting = batting_players.get(currBatsman);
                    }
                    break;
                }

            }

            if (bowler.getScore().getCurrBall() == Over.NUM_OF_BALLS && currBatsman == Team.TEAM_SIZE) {
                over.setTotalRun();
                over.setPlayerId(bowler.getPlayerId());
                overs.add(over);
                return;
            }
            if (bowler.getScore().getCurrBall() == Over.NUM_OF_BALLS) {
                System.out.println("Over Changing---------");

                over.setTotalRun();
                over.setPlayerId(bowler.getPlayerId());
                overs.add(over);

                bowler.getScore().setCurrBall(0);

                //Bowler Score Added -
                ballScoreDoa.addBowlingScore(matchId, bowler);

                currBowler++;
                currBowler = bowlerSelector.getValidBowler(bowling_players, currBowler);
                neutral = batting;
                batting = running;
                running = neutral;
                currOver++;
            }
            if (currBatsman > Team.TEAM_SIZE - 1) {
                over.setTotalRun();
                over.setPlayerId(bowler.getPlayerId());
                overs.add(over);
                break;
            }
        }
        System.out.println("Current Batsman: " + currBatsman);
    }

    public int getRun(Player player) {
        int run = 0;
        if (player instanceof Batsman) {
            Batsman batsman = (Batsman) player;
            run = batsman.getRun();
        } else {
            Bowler bowler = (Bowler) player;
            run = bowler.getRun();
        }
        return run;
    }

}
