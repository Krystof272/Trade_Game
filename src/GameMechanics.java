import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameMechanics {
    private int height;
    private final ArrayList<Stock> stocks;
    private final Player player;

    public GameMechanics(ArrayList<Stock> stocks, Player player) {
        this.height = 0;
        this.stocks = stocks;
        this.player = player;
    }

    public void buyStock(String shareName, int price, int amount) {
        if (player.getMoney() >= price * amount) {
            player.addStock(shareName, amount);
            player.setMoney(player.getMoney() - price * amount);
        }
    }

    public void sellStock(String shareName, int price, int amount) {
        if (player.isStockOwned(shareName)) {
            player.substractStock(shareName, amount);
            player.setMoney(player.getMoney() + price * amount);
        }
    }

    public void addNextNumber() {
        Random rnd = new Random();
        int number;
        for (Stock stock : stocks) {
            number = stock.getNumbers().getLast() + rnd.nextInt(-100, 200) * stock.getFluctuation();

            if (number < 0) {
                number = 10;
            }
            stock.getNumbers().add(number);

            if (number > height - 190) {
                increaseRatio(stock.getNumbers());
            }
        }
    }

    public void increaseRatio(LinkedList<Integer> yHistory) {
        //TODO change this function/move, problem: number always < 900 (height of window)
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