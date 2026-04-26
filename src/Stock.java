import java.util.LinkedList;

public class Stock {
    private String name;
    private int fluctuation;
    private LinkedList<Integer> numbers ;

    public Stock(String name, int fluctuation) {
        this.name = name;
        this.fluctuation = fluctuation;
        this.numbers = new LinkedList<>();
        numbers.add(0);
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
