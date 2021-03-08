package com.game.cricket.models;

import java.util.List;

public interface BatsmanSelectorInterface {

    public List<Player> getBattingPlayers (List<Player> players);

    public Player getBatsman(List<? extends Player> list, int index);

}
