package com.ada.avanade.service;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class DiceRoller {
    private Random random;

    public DiceRoller() {
        random = new Random();
    }

    public int roll(int numberOfDice, int numberOfSides) {
        int result = 0;
        for (int i = 0; i < numberOfDice; i++) {
            result += random.nextInt(numberOfSides) + 1;
        }
        return result;
    }
}
