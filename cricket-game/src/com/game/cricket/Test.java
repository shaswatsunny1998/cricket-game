package com.game.cricket;

import com.game.cricket.models.Batsman;
import com.game.cricket.models.Bowler;
import com.game.cricket.models.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Player player = new Batsman("Shaswat","Srivastava",22);
        Player player1=new Batsman("Amitesh","Srivastava",22);
        Match match=new Match();

        System.out.println(player);
        System.out.println(player1);
    }
}
