package com.game.cricket.controllers;


import com.game.cricket.doa.PlayersDoa;
import com.game.cricket.doa.TeamPlayerDoa;
import com.game.cricket.models.Player;
import com.game.cricket.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayersDoa playersDoa;

    @Autowired
    PlayerService playerService;


    @GetMapping("/")
    public List<Player> listAllPlayers() {
        return playersDoa.getAllPlayers();
    }

//    @GetMapping("/listPlayers/{teamId}")
//    public List<Player> listPlayersByTeam(@PathVariable String teamId) {
//        return playersDoa.getPlayers(Integer.parseInt(teamId));
//    }


    @PostMapping("/")
    public Player addPlayer(@RequestBody Player player){
        return playerService.addPlayer(player);
    }


    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayers(@PathVariable int playerId){
        Player player = playerService.getPlayer(playerId);
        if(player!=null)
            return ResponseEntity.status(HttpStatus.OK).body(player);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
