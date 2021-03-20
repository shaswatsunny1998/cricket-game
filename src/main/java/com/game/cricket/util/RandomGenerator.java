package com.game.cricket.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {

    Random random = new Random();

    public int getRandomRun(int size) {
        return random.nextInt(size);

    }

    public int getRandomCoin() {
        return random.nextInt(2);
    }
}
