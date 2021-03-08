package com.game.cricket.models;

import com.game.cricket.Match;
import com.game.cricket.util.RandomGenerator;

import java.util.Arrays;
import java.util.List;

public class Inning {

    public int getValidBowler(List<Player> list, int index) {
        if (index >= list.size())
            index = index % list.size();
        return index;
    }

    public void singleInning(Team batting_team, Team bowling_team, int chaseScore,List<Over> overs,boolean isChasing) {
        RandomGenerator randomGenerator = new RandomGenerator();

        //Immutable List
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 6, -1);

        int totalScoreTeam = batting_team.getTotal_score();

        int currBatsman = 0;
        List<Player> batting_players = batting_team.getPlayers();

        int currBowler = 0;
        //TODO think of data structure which can help in getting shuffled bowlers
        // - Heap?
        List<Player> bowling_players = bowling_team.getPlayers();

        Player batting = batting_players.get(currBatsman);
        currBatsman++;
        Player running = batting_players.get(currBatsman);
        Player neutral;

        int currOver = 0;

        Over over=new Over();

        while (currBatsman <= Team.TEAM_SIZE - 1) {
            System.out.println("Initial On Batting Side : " + batting.getFirstName());
            System.out.println("Initial On Running Side : " + running.getFirstName());

            Player bowler = bowling_players.get(currBowler);

            System.out.println("Bowler Bowling: " + bowler.getFirstName());

            if(over.getCurrBall()<Over.NUM_OF_BALLS){

            }
            else{
                over = new Over();
            }


            for (int i = bowler.getScore().getCurrBall(); i < Over.NUM_OF_BALLS; ++i) {

                int index = randomGenerator.getRandomRun(list.size());
                int int_random = list.get(index);

                over.setCurrBall(over.getCurrBall()+1);

                if (int_random != -1) {

                    totalScoreTeam += int_random;
                    batting_team.setTotal_score(totalScoreTeam);

                    batting.getScore().setRun(int_random);
                    bowler.getScore().setRunBowl(int_random);
                    bowler.getScore().setCurrBall(bowler.getScore().getCurrBall() + 1);

                    if (int_random % 2 != 0) {
                        neutral = batting;
                        batting = running;
                        running = neutral;
                    }
                    over.setRun(int_random);
                    System.out.println("Run at Ball " + (int) (i + 1) + ": " + int_random);
                    System.out.println("After ball On Batting Side : " + batting.getFirstName());
                    System.out.println("After ball On Running Side : " + running.getFirstName());

                    if(isChasing){
                        if (batting_team.getTotal_score() > chaseScore) {
                            over.setTotalRun();
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

                    over.setWicket(over.getWicket() + 1);

                    running.getScore().getTotalRun();

                    currBatsman++;

                    if (!batting_team.isLastPlayer(currBatsman)) {
                        batting = batting_players.get(currBatsman);
                    }
                    break;
                }

            }
            if (bowler.getScore().getCurrBall() == Over.NUM_OF_BALLS) {
                System.out.println("Over Changing---------");

                over.setTotalRun();
                over.setPlayerId(bowler.getPlayerId());
                overs.add(over);

                bowler.getScore().setCurrBall(0);
                currBowler++;
                currBowler = getValidBowler(bowling_players, currBowler);
                neutral = batting;
                batting = running;
                running = neutral;
            }
            if (currBatsman > Team.TEAM_SIZE - 1) {
                over.setTotalRun();
                over.setPlayerId(bowler.getPlayerId());
                overs.add(over);
            }
        }

    }
}
