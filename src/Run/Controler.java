package Run;

import GameMechanics.GameMechanics;
import Windows.MainWindow;
import GameMechanics.Player;
import GameMechanics.Stock;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Controler {
    private final GameMechanics gameMechanics;
    private final Player player;
    private final ArrayList<Stock> stocks;
    private final ArrayList<Integer> date;

    public Controler() {
        this.stocks = new ArrayList<>();
        this.player = new Player(getUserNameInput());
        this.gameMechanics = new GameMechanics(stocks, player);
        this.date = new ArrayList<>(Arrays.asList(13, 6, 2009));

        stocks.add(new Stock("Nvidia", 2));
        stocks.add(new Stock("AMD", 1));
        stocks.add(new Stock("Intel", 3));
        stocks.add(new Stock("Vitkovium", 5));
    }

    public void init() {
        MainWindow m = new MainWindow(gameMechanics, stocks, player, date);
        m.init();
        m.firstStart();
    }

    public String getUserNameInput() {
        String name = null;
        while (name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(null, "Please enter your name:", "Name input", JOptionPane.QUESTION_MESSAGE);
        }
        return name;
    }
}
