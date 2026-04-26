import javax.swing.*;
import java.util.ArrayList;

public class Controler {
    private GameMechanics gameMechanics;
    private Player player;
    private ArrayList<Stock> stocks;

    public Controler() {
        this.stocks = new ArrayList<>();
        this.gameMechanics = new GameMechanics(stocks);
        this.player = new Player(getUserNameInput());

        stocks.add(new Stock("Nvidia", 2));
        stocks.add(new Stock("AMD", 1));
        stocks.add(new Stock("Intel", 5));
    }

    public void init() {
        new MainWindow(gameMechanics, stocks).init();
    }

    public String getUserNameInput() {
        String name = null;
        while (name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(null, "Please enter your name:", "Name input", JOptionPane.QUESTION_MESSAGE);
        }
        return name;
    }
}
