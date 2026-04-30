package GameMechanics;

import java.util.HashMap;

public class Player {
    private final String name;
    private int money;
    private final HashMap<String, Integer> stocksOwned;

    public Player(String name) {
        this.name = name;
        this.money = 500;
        this.stocksOwned = new HashMap<>();
    }

    public String getMoneyText(){
        String moneyText = String.valueOf(money);
        String moneyTemp = "";
        int a = 1;
        for (int i = moneyText.length() - 1; i > -1; i--) {
            moneyTemp += moneyText.charAt(i);
            if (a % 3 == 0) {
                moneyTemp += " ";
            }
            a++;
        }
        moneyText = "";
        for (int i = moneyTemp.length() - 1; i > -1; i--) {
            moneyText += moneyTemp.charAt(i);
        }
        return moneyText;
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
}
