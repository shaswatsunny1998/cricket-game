package com.game.cricket.util;

import java.util.Random;

public class RandomGenerator {

    Random random = new Random();

    public int getRandomRun(int size){
        return random.nextInt(size);
    }
}
