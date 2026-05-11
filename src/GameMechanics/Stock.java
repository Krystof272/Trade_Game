package GameMechanics;

import java.util.LinkedList;

public class Stock {
    private String name;
    private int fluctuation;
    private LinkedList<Integer> numbers ;

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
