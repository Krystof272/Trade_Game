package Run;

import GameMechanics.GameMechanics;
import Others.GameData;
import Windows.MainWindow;
import GameMechanics.Player;
import GameMechanics.Settings;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The main controller class responsible for initializing the game data,
 * setting up the player, and launching the main application window.
 */
public class Controler {
    private final GameMechanics gameMechanics;
    private final Player player;
    private static ArrayList<Integer> dateStart;
    private final Settings settings;
    private final GameData gameData;

    /**
     * Constructs a new Controler.
     * Loads game data from JSON, prompts the user for their name to create a Player,
     * initializes game mechanics, saves the starting date, and sets up the settings.
     */
    public Controler() {
        this.gameData = GameData.loadGameDataFromResources("/gameData.json");
        this.player = new Player(getUserNameInput());
        this.gameMechanics = new GameMechanics(gameData.getStocks(), player);
        dateStart = new ArrayList<>();
        dateStart.addAll(gameData.getDate());
        this.settings = new Settings(gameData.getBackgroundList(), gameData.getCurrencyList());
    }

    /**
     * Initializes and starts the main application window.
     */
    public void init() {
        MainWindow m = new MainWindow(gameMechanics, gameData.getStocks(), player, gameData.getDate(), settings);
        m.init();
        m.firstStart();
    }

    /**
     * Prompts the user with a dialog box to enter their name.
     * Continuously prompts until a valid, non-empty name is provided.
     *
     * @return The name entered by the user.
     */
    public String getUserNameInput() {
        String name = null;
        while (name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(null, "Please enter your name:", "Name input", JOptionPane.QUESTION_MESSAGE);
        }
        return name;
    }

    /**
     * Retrieves the game's starting date.
     * This is used later to calculate how long the player has been playing.
     *
     * @return An ArrayList representing the starting date [Day, Month, Year].
     */
    public static ArrayList<Integer> getDateStart() {
        return dateStart;
    }
}
