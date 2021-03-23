package com.game.cricket.models;

import com.game.cricket.Match;
import com.game.cricket.doa.BallScoreDoa;
import com.game.cricket.doa.BallsDoa;
import com.game.cricket.doa.BatScoreDoa;
import com.game.cricket.doa.WicketDoa;

import java.util.List;

public class ModifiedInning {
    BallsDoa ballsDoa = new BallsDoa();
    WicketDoa wicketDoa = new WicketDoa();
    BatScoreDoa batScoreDoa = new BatScoreDoa();
    BallScoreDoa ballScoreDoa = new BallScoreDoa();
    Player batting, running, neutral;

    BowlerSelector bowlerSelector;
    BatsmanSelector batsmanSelector;

    public ModifiedInning() {

        bowlerSelector = new BowlerSelector();
        batsmanSelector = new BatsmanSelector();
    }


    //int matchId, int chaseScore,boolean isChasing,List<Over> overs
    public void ballEvent(Player bowler , Over over,Team batting_team,int i,int matchId, int currOver , boolean isChasing) {


        int int_random = getRun(batting);

        Ball ball = new Ball(int_random);

        ball.setBatsmanId(batting.getPlayerId());
        ball.setBowlerId(bowler.getPlayerId());

        over.setCurrBall(over.getCurrBall() + 1);
        over.getBalls().add(ball);

        int totalScoreTeam = batting_team.getTotal_score();


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

        } else {
            bowler.getScore().setWickets(bowler.getScore().getWickets() + 1);
            System.out.println(bowler.getScore().getOvers());

            System.out.println(bowler.getScore().getTotalRunsBowlGiven());
            batting.getScore().setOut(true);

            bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);

            System.out.println("Out at Ball: " + bowler.getScore().getCurrBall());
            System.out.println("Out with total run: " + batting.getScore().getTotalRun());

            //For out - considered 1 dot run as this ball is also being played.
            batting.getScore().setRun(0);


            //Wickets Added
            wicketDoa.addWicket(matchId, currOver + 1, !isChasing, i + 1, batting.getPlayerId(), bowler.getPlayerId());


            //Bowler Score Added -
            ballScoreDoa.addBowlingScore(matchId, bowler);

            //BatScore Added - (Corner case , if the batsman scored 0 run).
            batScoreDoa.addBattingScore(matchId, batting);



            over.setWicket(over.getWicket() + 1);

            running.getScore().getTotalRun();

            over.addWicket(batting.getPlayerId(), bowler.getPlayerId(), over.getCurrBall());

            batting.getScore().setRun(0);

        }
    }








    public void singleInning(int matchId, Team batting_team, Team bowling_team, int chaseScore, List<Over> overs, boolean isChasing) {

        int currBatsman = 0;
        int currBowler = 0;
        int currOver = 0;


        List<Player> batting_players = batsmanSelector.getBattingPlayers(batting_team.getPlayers());
        List<Player> bowling_players = bowlerSelector.getBowlingPlayers(bowling_team.getPlayers());

        batting = batting_players.get(currBatsman);
        currBatsman++;
        running = batting_players.get(currBatsman);

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
                ballEvent(bowler,over,batting_team,i,matchId,currOver,isChasing);

                if (isChasing) {
                    if (batting_team.getTotal_score() > chaseScore) {
                        over.setTotalRun();
                        over.setPlayerId(bowler.getPlayerId());
                        overs.add(over);
                        return;
                    }
                }
                if(batting.getScore().isOut())
                {
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
