package GameMechanics;

import java.util.LinkedList;

/**
 * Represents a tradable stock in the game, keeping track of its name,
 * price history, and market fluctuation rate.
 */
public class Stock {
    private String name;
    private int fluctuation;
    private LinkedList<Integer> numbers;

    public LinkedList<Integer> getNumbers() {
        return numbers;
    }

    public String getName() {
        return name;
    }

    public int getFluctuation() {
        return fluctuation;
    }
}
