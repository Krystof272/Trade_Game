package GameMechanics;

import java.util.ArrayList;
import java.util.Random;

public class GameMechanics {
    private final ArrayList<Stock> stocks;
    private final Player player;

    public GameMechanics(ArrayList<Stock> stocks, Player player) {
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
        if (amount > player.getAmountOfStocksOwned(shareName)) {
            amount = player.getAmountOfStocksOwned(shareName);
        }
        if (player.isStockOwned(shareName)) {
            player.substractStock(shareName, amount);
            player.setMoney(player.getMoney() + price * amount);
        }
    }

    public void addNextNumber() {
        Random rnd = new Random();
        int number;
        for (Stock stock : stocks) {
            number = stock.getNumbers().getLast() + (rnd.nextInt(-100, 120) * stock.getFluctuation());

            if (number < 10) {
                number = 10;
            }
            stock.getNumbers().add(number);
        }
    }

    public ArrayList<Integer> updateDate(ArrayList<Integer> date) {
        date.set(0, date.get(0) + 1);
        if (date.getFirst() > 30) {
            date.set(0, 1);
            date.set(1, date.get(1) + 1);
        }
        if (date.get(1) > 12) {
            date.set(1, 1);
            date.set(2, date.get(2) + 1);
        }
        return date;
    }
}