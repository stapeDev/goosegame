package it.stapedev.data;

import java.util.Random;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Dice {

    private static final Integer maxDiceNumber = 7;
    private static final Integer minDiceNumber = 1;

    private Integer first;

    private Integer second;

    public Dice(Integer first, Integer second) {
        this.roll(first, second);
    }

    public Dice() {
        this.roll();
    }

    public void roll() {
        this.first = getRandomNumber();
        this.second = getRandomNumber();
    }

    public void roll(Integer first, Integer second) {
        if (isValid(first) && isValid(second)) {
            this.first = first;
            this.second = second;
        } else {
            throw new RuntimeException("Valori non ammissibili");
        }
    }

    private Integer getRandomNumber() {
        Random random = new Random();
        return random.nextInt(maxDiceNumber - minDiceNumber) + minDiceNumber;
    }

    private Boolean isValid(Integer value) {
        return value >= minDiceNumber && value <= maxDiceNumber;
    }

}
