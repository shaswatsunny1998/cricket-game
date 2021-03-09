package com.game.cricket.models;

import java.util.List;

public interface BowlerSelectorInterface {

    public List<Player> getBowlingPlayers(List<Player> players);

    public int getValidBowler(List<Player> list, int index);
}

