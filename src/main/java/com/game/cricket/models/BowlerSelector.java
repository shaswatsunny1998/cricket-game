package com.game.cricket.models;

import java.util.ArrayList;
import java.util.List;

public class BowlerSelector implements BowlerSelectorInterface {

    @Override
    public List<Player> getBowlingPlayers(List<Player> players) {
        List<Player> bowlingPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player instanceof Bowler) {
                bowlingPlayers.add(player);
            }
        }
        return bowlingPlayers;
    }

    @Override
    public int getValidBowler(List<Player> list, int index) {
        if (index >= list.size())
            index = index % list.size();
        return index;
    }
}
