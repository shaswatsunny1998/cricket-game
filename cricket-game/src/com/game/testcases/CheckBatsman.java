package com.game.testcases;

import com.game.cricket.models.Batsman;
import com.game.cricket.models.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckBatsman {

    @Test
    public void checkBatsman(){
        Player player = new Batsman("Shaswat","Srivastava",22);
        assertTrue(player instanceof Batsman);
    }


}
