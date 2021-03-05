package com.game.testcases;

import com.game.cricket.models.Batsman;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckBatsman {
    @Test
    public void checkTotalRuns(){
        Batsman batsman=new Batsman();
        for(int i=0;i<=6;++i){
            batsman.setRun(i);
        }
        // 5 run is not added in Batsman Section.
        assertEquals(21,batsman.getTotalRun());
    }

    @Test
    public void checkIndividualScores(){
        Batsman batsman=new Batsman();
        for(int i=0;i<=6;++i){
            batsman.setRun(0);
        }
        assertEquals(batsman.getTotalRun(),new Batsman().getTotalRun());
        //DotRuns Counted
        assertEquals(batsman,new Batsman());
    }


}
