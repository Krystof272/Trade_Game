import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameMechanics {
    private final Random rnd;
    private final ArrayList<Stock> stocks;
    private int height;
    private Player player;

    public GameMechanics(String name) {
        this.rnd = new Random();
        this.stocks = new ArrayList<>();
        stocks.add(new Stock("Nvidia", 1));
        this.height = 0;
        this.player = new Player(name);
    }

    public void addNextNumber() {
        int number;
        for (Stock stock : stocks) {
            number = stock.getNumbers().getLast() + rnd.nextInt(-100, 300);

            if (number < stock.getNumbers().getFirst()) {
                number = stock.getNumbers().getFirst();
            }
            stock.getNumbers().add(number);

            if (number > height - 70) {
                insreaseRatio(stock.getNumbers());
            }
        }
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void insreaseRatio(LinkedList<Integer> yHistory) {
        for (int i = 1; i < yHistory.size(); i++) {
            int number = yHistory.get(i) / 2;

            if (number < yHistory.getFirst()) {
                number = yHistory.getFirst();
            }
            yHistory.set(i, number);
        }
    }

    public void setHeight(int height) {
        this.height = height;
    }
}