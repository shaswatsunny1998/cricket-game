package com.game.cricket.services;

import com.game.cricket.doa.PlayersDoa;
import com.game.cricket.models.Batsman;
import com.game.cricket.models.Bowler;
import com.game.cricket.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayersDoa playersDoa;

    public List<Player> getPlayers(List<Integer> playersId)
    {
        List<Player> players = new ArrayList<Player>();
        for(int i=0;i<playersId.size();++i)
        {
            players.add(playersDoa.getPlayer(playersId.get(i)));
        }
        return players;
    }


    public Player getPlayer(int playerId){
        return playersDoa.getPlayer(playerId);
    }



    public Player addPlayer(Player player){
        Player player1 = null;
        if(player instanceof Batsman)
            player1=playersDoa.addPlayer(new Batsman(player.getPlayerId(),player.getFirstName(),player.getLastName(),player.getAge()));
        else if(player instanceof Bowler)
            player1=playersDoa.addPlayer(new Bowler(player.getPlayerId(),player.getFirstName(),player.getLastName(),player.getAge()));
        return player1;
    }

}
