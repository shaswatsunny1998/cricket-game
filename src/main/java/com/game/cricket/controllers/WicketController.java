package com.game.cricket.controllers;


import com.game.cricket.models.WicketDto;
import com.game.cricket.services.WicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class WicketController {

    @Autowired
    WicketService wicketService;

    @GetMapping("/wickets/{matchId}")
    public List<WicketDto> getWickets(@PathVariable int matchId) {
        return wicketService.getWicketDtos(matchId);
    }
}
