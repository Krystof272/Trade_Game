package Run;

import GameMechanics.GameMechanics;
import Others.GameData;
import Windows.MainWindow;
import GameMechanics.Player;
import GameMechanics.Settings;

import javax.swing.*;
import java.util.ArrayList;

public class Controler {
    private final GameMechanics gameMechanics;
    private final Player player;
    private static ArrayList<Integer> dateStart;
    private final Settings settings;
    private final GameData gameData;

    public Controler() {
        this.gameData = GameData.loadGameDataFromResources("/gameData.json");
        this.player = new Player(getUserNameInput());
        this.gameMechanics = new GameMechanics(gameData.getStocks(), player);
        dateStart = new ArrayList<>();
        dateStart.addAll(gameData.getDate());
        this.settings = new Settings(gameData.getBackgroundList(), gameData.getCurrencyList());
    }

    public void init() {
        MainWindow m = new MainWindow(gameMechanics, gameData.getStocks(), player, gameData.getDate(), settings);
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

    public static ArrayList<Integer> getDateStart() {
        return dateStart;
    }
}
