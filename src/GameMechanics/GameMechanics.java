package GameMechanics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Handles the core mechanics of the game, including buying, selling stocks,
 * and updating the market and in-game date.
 */
public class GameMechanics {
    private final ArrayList<Stock> stocks;
    private final Player player;

    public GameMechanics(ArrayList<Stock> stocks, Player player) {
        this.stocks = stocks;
        this.player = player;
    }

    /**
     * Processes a stock purchase if the player has enough money.
     *
     * @param shareName The name of the stock to buy.
     * @param price     The current price per share.
     * @param amount    The number of shares to purchase.
     */
    public void buyStock(String shareName, int price, int amount) {
        if (player.getMoney() >= price * amount) {
            player.addStock(shareName, amount);
            player.setMoney(player.getMoney() - price * amount);
        }
    }

    /**
     * Processes a stock sale, ensuring the player doesn't sell more shares than they own.
     *
     * @param shareName The name of the stock to sell.
     * @param price     The current price per share.
     * @param amount    The number of shares to sell.
     */
    public void sellStock(String shareName, int price, int amount) {
        if (amount > player.getAmountOfStocksOwned(shareName)) {
            amount = player.getAmountOfStocksOwned(shareName);
        }
        if (player.isStockOwned(shareName)) {
            player.substractStock(shareName, amount);
            player.setMoney(player.getMoney() + price * amount);
        }
    }

    /**
     * Simulates a market tick by adding a newly randomized price to each stock's history.
     * The price fluctuation depends on the stock's inherent fluctuation rate and includes
     * bound checks (a stock's price will never drop below 25 or exceed 5000, and crashing behavior is included).
     */
    public void addNextNumber() {
        Random rnd = new Random();
        int number;
        for (Stock stock : stocks) {
            number = stock.getNumbers().getLast() + (rnd.nextInt(-100, 110) * stock.getFluctuation());

            if (number < 25) {
                number = 25;
            } else if (number > 5000) {
                number = 5000;
                if (stock.getNumbers().get(stock.getNumbers().size() - 15) > 4500) {
                    number = 25;
                }
            }
            stock.getNumbers().add(number);
        }
    }

    /**
     * Increments the in-game calendar date by one day
     *
     * @param date A list representing the current date in the format [Day, Month, Year].
     * @return The updated date list.
     */
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
