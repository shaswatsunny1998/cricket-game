package com.game.testcases;

import com.game.cricket.models.Player;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckPlayer {
    /*
    public int square(int x){
        return x*x;
    }

    @Test
    public void test(){
        int a=square(5);
        assertEquals(35,a);
    }
     */


    @Test
    public void checkDetails(){
        Player player1 = new Player("Shaswat","Srivastava",45);
        Player player2 = new Player("Shaswat","Srivastava",45,"batsman");
        assertEquals("Shaswat",player1.getFirstName());
        assertEquals("Srivastava",player1.getLastName());
        assertEquals(45,player1.getAge());
        assertEquals("batsman",player1.getType());
    }

}
