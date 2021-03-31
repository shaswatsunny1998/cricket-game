package com.game.cricket.models;

import com.game.cricket.Match;
import com.game.cricket.doa.*;

import java.util.List;

public class UserControlInning {

    BallScoreDoa ballScoreDoa = new BallScoreDoa();
    TransactionDoa transactionDoa = new TransactionDoa();
    Player batting, running, neutral;

    BowlerSelector bowlerSelector;
    BatsmanSelector batsmanSelector;

    int currBatsman = 0;
    int currOver = 0;

    Team batting_team;

    List<Player> batting_players;
    List<Player> bowling_players;

    public UserControlInning(Team batting_team,Team bowling_team) {
        bowlerSelector = new BowlerSelector();
        batsmanSelector = new BatsmanSelector();

        this.batting_team = batting_team;

        batting_players = batsmanSelector.getBattingPlayers(batting_team.getPlayers());
        bowling_players = bowlerSelector.getBowlingPlayers(bowling_team.getPlayers());

        batting = batting_players.get(currBatsman);
        currBatsman++;
        running = batting_players.get(currBatsman);


        System.out.println("Batting Players: "+batting_players);
        System.out.println("Bowling Players: "+bowling_players);

    }

    public void ballEvent(Player bowler, Over over, int i, int matchId, boolean isChasing,int run) {

        int int_random = run;

        Ball ball = new Ball(int_random);

        ball.setBatsmanId(batting.getPlayerId());
        ball.setBowlerId(bowler.getPlayerId());

        over.setCurrBall(over.getCurrBall() + 1);
        over.getBalls().add(ball);

        int totalScoreTeam = batting_team.getTotal_score();

        if (int_random != -1) {

            totalScoreTeam += int_random;
            batting_team.setTotal_score(totalScoreTeam);

            batting.getScore().setRun(int_random);


            bowler.getScore().setRunBowl(int_random);
            bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);


            transactionDoa.addWithoutWicket(matchId, currOver + 1, !isChasing, i + 1, int_random, batting.getPlayerId(),
                    bowler.getPlayerId(), batting, bowler);

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


            transactionDoa.addWithWicket(matchId, currOver + 1, !isChasing, i + 1, int_random, batting.getPlayerId(),
                    bowler.getPlayerId(), batting, bowler);

            over.setWicket(over.getWicket() + 1);

            running.getScore().getTotalRun();

            over.addWicket(batting.getPlayerId(), bowler.getPlayerId(), over.getCurrBall());

            batting.getScore().setRun(0);

        }
    }

    public void singleOver(int matchId,int bowlerIndex, Over over, List<Over> overs , boolean isChasing, int chaseScore,
                           List<Integer> runs){

        while (currBatsman <= Team.TEAM_SIZE - 1 && currOver < Match.NUM_OF_OVERS) {
            System.out.println("Initial On Batting Side : " + batting.getFirstName());
            System.out.println("Initial On Running Side : " + running.getFirstName());

            Player bowler = bowling_players.get(bowlerIndex);


            System.out.println("Bowler Bowling: " + bowler.getFirstName());
            if (over.getCurrBall() < Over.NUM_OF_BALLS) {

            } else {
                over = new Over();
            }

            //Give use control

            for (int i = bowler.getScore().getCurrBall(); i < Over.NUM_OF_BALLS; ++i) {
                ballEvent(bowler, over, i, matchId, isChasing,runs.get(i));

                if (isChasing) {
                    if (batting_team.getTotal_score() > chaseScore) {
                        over.setTotalRun();
                        over.setPlayerId(bowler.getPlayerId());
                        overs.add(over);
                        return;
                    }
                }
                if (batting.getScore().isOut()) {
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

                neutral = batting;
                batting = running;
                running = neutral;

                currOver++;
                return;

            }
            if (currBatsman > Team.TEAM_SIZE - 1) {
                over.setTotalRun();
                over.setPlayerId(bowler.getPlayerId());
                overs.add(over);
                return;
            }
        }
    }

    public Over singleInningOver(int matchId,int bowlerIndex, List<Over> overs , boolean isChasing, int chaseScore,
                                 List<Integer> runs)
    {
        Over over = new Over();
        singleOver(matchId,bowlerIndex,over,overs,isChasing,chaseScore,runs);
        return over;
    }


    public int getCurrBatsman() {
        return currBatsman;
    }

    public int getCurrOver() {
        return currOver;
    }

    public Team getBatting_team() {
        return batting_team;
    }

    public List<Player> getBatting_players() {
        return batting_players;
    }

    public List<Player> getBowling_players() {
        return bowling_players;
    }
}
