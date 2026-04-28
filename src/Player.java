import java.util.HashMap;

public class Player {
    private String name;
    private int money;
    private HashMap<String, Integer> stocksOwned;

    public Player(String name) {
        this.name = name;
        this.money = 2000;
        this.stocksOwned = new HashMap<>();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addStock(String stockName, int amount) {
        if (stocksOwned.containsKey(stockName)) {
            stocksOwned.replace(stockName, stocksOwned.get(stockName) + amount);
        } else {
            stocksOwned.put(stockName, amount);
        }
    }

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

    public int getAmountOfStocksOwned(String shareName) {
        if (isStockOwned(shareName)) {
            return stocksOwned.get(shareName);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", stocksOwned=" + stocksOwned +
                '}';
    }
}
