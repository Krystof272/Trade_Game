package GameMechanics;

import java.util.LinkedList;
import java.util.Random;

public class Stock {
    private final String name;
    private final int fluctuation;
    private final LinkedList<Integer> numbers ;

    public Stock(String name, int fluctuation) {
        this.name = name;
        this.fluctuation = fluctuation;
        this.numbers = new LinkedList<>();

        Random rnd = new Random();
        numbers.add(rnd.nextInt(500));
    }

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
