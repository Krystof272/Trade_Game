import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameMechanics {
    private int height;
    private ArrayList<Stock> stocks;
    private Player player;

    public GameMechanics(ArrayList<Stock> stocks, Player player) {
        this.height = 0;
        this.stocks = stocks;
        this.player = player;
    }

    public void buy1Stock(String shareName, int price){
        if (player.getMoney() > price){
            player.addStock(shareName, 1);
            player.setMoney(player.getMoney() - price);
        }
        System.out.println(player +"\nshare: "+ shareName +", price: "+ price);
    }

    public void sell1Stock(String shareName, int price){
            if (player.isStockOwned(shareName)) {
                player.substractStock(shareName, 1);
                player.setMoney(player.getMoney() + price);
            }
        System.out.println(player +"\nshare: "+ shareName +", price: "+ price);
    }

    public void addNextNumber() {
        Random rnd = new Random();
        int number;
        for (Stock stock : stocks) {
            number = stock.getNumbers().getLast() + rnd.nextInt(-100, 200) * stock.getFluctuation();

            if (number < stock.getNumbers().getFirst()) {
                number = stock.getNumbers().getFirst();
            }
            stock.getNumbers().add(number);

            if (number > height - 100) {
                increaseRatio(stock.getNumbers());
            }
        }
    }

    public void increaseRatio(LinkedList<Integer> yHistory) {
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