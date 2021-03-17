package com.game.cricket;


import org.springframework.stereotype.Component;

@Component
public class MatchTransporter {
    //private static boolean matchAvailable=false;
    private static Match match;

//    public static boolean isMatchAvailable() {
//        return matchAvailable;
//    }

    public static void setMatch(Match match) {
        MatchTransporter.match=match;
        //matchAvailable = true;
    }

    public static Match getMatch() {
        //matchAvailable = false;
        return match;
    }
}
