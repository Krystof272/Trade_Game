package GameMechanics;

import java.util.HashMap;

/**
 * Represents a player in the trading game, managing their money and owned stocks.
 */
public class Player {
    private final String name;
    private int money;
    private final HashMap<String, Integer> stocksOwned;

    public Player(String name) {
        this.name = name;
        this.money = 500; // Starting money
        this.stocksOwned = new HashMap<>();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    /**
     * Adds shares of a specific stock to the player's portfolio.
     * If the player already owns shares of this stock, the amount is added to the current total.
     *
     * @param stockName The name of the stock to add.
     * @param amount    The number of shares to add.
     */
    public void addStock(String stockName, int amount) {
        if (stocksOwned.containsKey(stockName)) {
            stocksOwned.replace(stockName, stocksOwned.get(stockName) + amount);
        } else {
            stocksOwned.put(stockName, amount);
        }
    }

    /**
     * Subtracts shares of a specific stock from the player's portfolio.
     * If the remaining amount drops to 0 or below, the stock is completely removed from their portfolio.
     *
     * @param stockName The name of the stock to remove.
     * @param amount    The number of shares to subtract.
     */
    public void substractStock(String stockName, int amount) {
        if (stocksOwned.get(stockName) - amount > 0) {
            stocksOwned.replace(stockName, stocksOwned.get(stockName) - amount);
        } else {
            stocksOwned.remove(stockName);
        }
    }

    public boolean isStockOwned(String shareName) {
        return stocksOwned.containsKey(shareName);
    }

    /**
     * Retrieves the exact number of shares the player owns of a specific stock.
     *
     * @param shareName The name of the stock to check.
     * @return The number of shares owned, or 0 if none are owned.
     */
    public int getAmountOfStocksOwned(String shareName) {
        if (isStockOwned(shareName)) {
            return stocksOwned.get(shareName);
        }
        return 0;
    }
}
