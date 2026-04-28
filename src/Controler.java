import javax.swing.*;
import java.util.ArrayList;

public class Controler {
    private GameMechanics gameMechanics;
    private Player player;
    private ArrayList<Stock> stocks;

    public Controler() {
        this.stocks = new ArrayList<>();
        this.player = new Player(getUserNameInput());
        this.gameMechanics = new GameMechanics(stocks, player);


        stocks.add(new Stock("Nvidia", 2));
        stocks.add(new Stock("AMD", 1));
        stocks.add(new Stock("Intel", 3));
        stocks.add(new Stock("Vitkovium", 5));
    }

    public void init() {
        new MainWindow(gameMechanics, stocks, player).init();
    }

    public String getUserNameInput() {
        String name = null;
        while (name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(null, "Please enter your name:", "Name input", JOptionPane.QUESTION_MESSAGE);
        }
        return name;
    }
}
