package GameMechanics;

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
        if (amount > player.getAmountOfStocksOwned(shareName)) {
            amount = player.getAmountOfStocksOwned(shareName);
        }
        if (player.isStockOwned(shareName)) {
            player.substractStock(shareName, amount);
            player.setMoney(player.getMoney() + price * amount);
        }
    }

    public void addNextNumber()

    {
        Random rnd = new Random();
        int number;
        for (Stock stock : stocks) {
            number = stock.getNumbers().getLast() + (rnd.nextInt(-100, 100) * stock.getFluctuation());

            if (number < 10) {
                number = 10;
            }
            stock.getNumbers().add(number);
        }
    }

    public void setHeight(int height) {
        this.height = height;
    }
}