package com.game.cricket.models;

import java.util.*;

public class BatsmanSelector implements BatsmanSelectorInterface{


    public BatsmanSelector() {
    }

    @Override
    public List<Player> getBattingPlayers (List<Player> players) {
        Collections.sort(players,new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return p2.getScore().getStrikeRate()- p1.getScore().getStrikeRate();
            }
        });

        return players;
    }

    @Override
    public Player getBatsman(List<? extends Player> list, int index) {
        return list.get(index);
    }
}
