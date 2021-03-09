package com.game.cricket;

import com.game.cricket.models.Batsman;
import com.game.cricket.models.Player;

public class Test {
    public static void main(String[] args) {
        Player player = new Batsman("Shaswat","Srivastava",22);
        Player player1=new Batsman("Amitesh","Srivastava",22);
        Match match=new Match();

        System.out.println(player);
        System.out.println(player1);
    }
}
