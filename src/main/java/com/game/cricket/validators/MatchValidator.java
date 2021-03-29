package com.game.cricket.validators;

import com.game.cricket.doa.MatchDoa;
import com.game.cricket.exceptions.MatchIdException;
import com.game.cricket.models.MatchScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MatchValidator {

    @Autowired
    MatchDoa matchDoa;

    public boolean matchPresent(MatchScheduler match) {
        try {
            if (matchDoa.isPresentMatch(match.getMatchId())) {
                throw new MatchIdException("Match Id already Existing: " + match.getMatchId());
            }
        } catch (Exception e) {
            System.out.println(e);
            return true;
        }
        return false;
    }
}
