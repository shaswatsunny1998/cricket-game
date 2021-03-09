package com.game.cricket.models;

import com.game.cricket.util.RandomGenerator;
import com.game.cricket.util.Runs;

import java.util.Arrays;
import java.util.List;

public class Bowler extends Player {
    private static RandomGenerator randomGenerator = new RandomGenerator();

    //Wicket Probability - 1/7
    private static List<Runs> list = Arrays.asList(Runs.ZERORUN, Runs.ONERUN, Runs.TWORUN, Runs.THREERUN,
            Runs.FOURRUN, Runs.SIXRUN, Runs.WICKET);

    public Bowler(String firstName, String lastName, int age) {
        super(firstName, lastName, age, "Bowler");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getRun() {
        int index = randomGenerator.getRandomRun(list.size());
        int int_random = list.get(index).getRun();
        //System.out.println("Bowler Scored");
        return int_random;
    }

}
