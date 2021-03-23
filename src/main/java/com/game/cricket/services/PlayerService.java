package com.game.cricket.services;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.game.cricket.doa.PlayersDoa;
import com.game.cricket.models.Batsman;
import com.game.cricket.models.Bowler;
import com.game.cricket.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayersDoa playersDoa;

    @Autowired
    PlayerScoreService playerScoreService;

    public List<Player> getPlayers(List<Integer> playersId)
    {
        List<Player> players = new ArrayList<Player>();
        for(int i=0;i<playersId.size();++i)
        {
            Player player = playersDoa.getPlayer(playersId.get(i));
            players.add(player);
        }
        return players;
    }


    public List<Player> getMatchTeamsPlayer(int matchId,int teamId)
    {
        List<Integer> ids = playersDoa.getPlayers(matchId, teamId);
        return getPlayers(ids);
    }


    public List<Player> getPlayersByMatch(int matchId)
    {
        List<Integer> ids = playersDoa.getPlayersByMatch(matchId);
        return getPlayers(ids);
    }


    public void setPlayersScore(List<Player> players,int matchId)
    {
        for (Player player:players) {
            player.setScore(playerScoreService.mergeScores(matchId,player.getPlayerId()));
        }

    }

    public List<Player> getPlayerDetails()
    {
         return playersDoa.getAllPlayers();
    }



    public MappingJacksonValue getMapping(Player player){
        SimpleBeanPropertyFilter simpleBeanPropertyFilter =
                SimpleBeanPropertyFilter.serializeAllExcept("score");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("playerFilter", simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(player);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
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
