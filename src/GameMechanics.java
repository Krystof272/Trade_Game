import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameMechanics {
    private int height;
    private ArrayList<Stock> stocks;

    public GameMechanics(ArrayList<Stock> stocks) {
        this.height = 0;
        this.stocks = stocks;
    }

    public void addNextNumber() {
        Random rnd = new Random();
        int number;
        for (Stock stock : stocks) {
            number = stock.getNumbers().getLast() + rnd.nextInt(-100, 300) * stock.getFluctuation();

            if (number < stock.getNumbers().getFirst()) {
                number = stock.getNumbers().getFirst();
            }
            stock.getNumbers().add(number);

            if (number > height - 70) {
                insreaseRatio(stock.getNumbers());
            }
        }
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