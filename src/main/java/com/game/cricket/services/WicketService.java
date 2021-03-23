package com.game.cricket.services;

import com.game.cricket.doa.WicketDoa;
import com.game.cricket.models.WicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WicketService {

    @Autowired
    WicketDoa wicketDoa;

    public List<WicketDto> getWicketDtos(int matchId) {
        List<WicketDto> wicketDtos = new ArrayList<>();
        wicketDtos.addAll(wicketDoa.getWicketsDto(matchId, true));
        wicketDtos.addAll(wicketDoa.getWicketsDto(matchId, false));
        return wicketDtos;
    }
}



