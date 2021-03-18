package com.game.cricket.models;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.game.cricket.util.RandomGenerator;
import com.game.cricket.util.Runs;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@JsonTypeName("Batsman")
public class Batsman extends Player {
    private static RandomGenerator randomGenerator = new RandomGenerator();

    //Wicket Probability - 1/12
    private static List<Runs> list = Arrays.asList(Runs.ZERORUN, Runs.FOURRUN, Runs.SIXRUN, Runs.WICKET, Runs.ONERUN,
            Runs.TWORUN, Runs.THREERUN, Runs.FOURRUN, Runs.SIXRUN);

    public Batsman() {
    }

    public Batsman(String firstName, String lastName, int age) {
        super(firstName, lastName, age, "Batsman", 34);
    }


    public Batsman(int playerId, String firstName, String lastName, int age) {
        super(playerId,firstName, lastName, age, "Batsman");
    }

    public String toString() {
        return super.toString();
    }

    public int getRun() {

        int index = randomGenerator.getRandomRun(list.size());
        int int_random = list.get(index).getRun();
        //System.out.println("Batsman Scored");
        return int_random;
    }
}
