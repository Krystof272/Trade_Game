package Windows;

import GameMechanics.GameMechanics;
import Others.CustomButton;
import Run.MyWindow;
import GameMechanics.Player;
import GameMechanics.Stock;
import Windows.JPanels.BackgroundPanel;
import Windows.JPanels.StockPanel;
import GameMechanics.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * The main dashboard window of the game. It displays an overview of all
 * available stocks, the player's total money, the current in-game date,
 * and allows the player to advance the week or open detailed stock views.
 */
public class MainWindow extends MyWindow {
    private final GameMechanics gameMechanics;
    private final JButton button2;
    private final ArrayList<StockPanel> stockPanels;
    private final ArrayList<Stock> stocks;
    private final Player player;
    private final ArrayList<JButton> disabledButtons;
    private ArrayList<Integer> date;
    private final JLabel dateLabel;
    private final Settings settings;

    /**
     * Constructs the main dashboard window.
     *
     * @param gameMechanics The core game logic handler.
     * @param stocks        The list of all stocks available in the game.
     * @param player        The current player.
     * @param date          The current in-game date.
     * @param settings      The game settings (e.g., currency, background).
     */
    public MainWindow(GameMechanics gameMechanics, ArrayList<Stock> stocks, Player player, ArrayList<Integer> date, Settings settings) {
        this.gameMechanics = gameMechanics;
        this.button2 = new JButton("Next Week");
        this.stockPanels = new ArrayList<>();
        this.stocks = stocks;
        this.player = player;
        this.disabledButtons = new ArrayList<>();
        this.date = date;
        this.dateLabel = new JLabel(date.get(0) + "." + date.get(1) + "." + date.get(2));
        this.settings = settings;
    }

    /**
     * Executes the initial startup logic. For example, automatically advancing
     * the game by one week to generate the first set of price changes.
     */
    public void firstStart() {
        run(disabledButtons, settings);
    }

    /**
     * Initializes the UI layout, loads the background image, sets up the
     * top settings bar, the central dashboard grid, and the bottom control panel.
     */
    public void init() {
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Image backgroundImage = null;
        try {
            InputStream is = getClass().getResourceAsStream("/" + settings.getImageBackgroundName());
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                backgroundImage = ImageIO.read(new File("resources/" + settings.getImageBackgroundName()));
            }
        } catch (Exception e) {
            System.err.println("Could not load background image: " + e.getMessage());
            e.printStackTrace();
        }
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        southPanel(backgroundPanel);
        dashboardPanel(backgroundPanel);

        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        settingsPanel.setOpaque(false);

        JButton settingsButton = new JButton("Settings");
        CustomButton.changeDarkGrey20(settingsButton);
        settingsPanel.add(settingsButton);
        backgroundPanel.add(settingsPanel, BorderLayout.NORTH);
        disabledButtons.add(settingsButton);

        settingsButton.addActionListener(e -> {
            new SettingDialog(this, settings);
            new MainWindow(gameMechanics, stocks, player, date, settings).init();
            dispose();
        });
        button2.addActionListener(e -> run(disabledButtons, settings));

        setVisible(true);
    }

    /**
     * Initializes and adds the bottom panel containing the date display,
     * the "Next Week" button, and the player's total money.
     *
     * @param backgroundPanel The parent panel to add the bottom controls to.
     */
    public void southPanel(BackgroundPanel backgroundPanel) {
        JPanel southPanel = new JPanel(new GridLayout(1, 5));
        southPanel.setOpaque(false);
        southPanel.add(new JPanel() {{
            setOpaque(false);
        }});

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        datePanel.setOpaque(false);
        datePanel.add(dateLabel);
        southPanel.add(datePanel);

        CustomButton.changeGreen(button2);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(button2);
        southPanel.add(buttonPanel);
        disabledButtons.add(button2);

        JLabel playerMoneyLabel = new JLabel("Money: " + settings.toStringPriceSymbol(player.getMoney()));
        playerMoneyLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        JPanel playerMoneyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playerMoneyPanel.setOpaque(false);
        playerMoneyPanel.add(playerMoneyLabel);
        southPanel.add(playerMoneyPanel);

        southPanel.add(new JPanel() {{
            setOpaque(false);
        }});

        backgroundPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Initializes the central dashboard grid. It iterates through all available stocks,
     * creates a StockPanel for each, and adds them to the main layout.
     *
     * @param backgroundPanel The parent panel to add the stock grid to.
     */
    public void dashboardPanel(BackgroundPanel backgroundPanel) {
        JPanel dashboardPanel = new JPanel(new GridLayout(2, stocks.size() / 2));
        dashboardPanel.setOpaque(false);
        for (int i = 0; i < stocks.size(); i++) {
            JButton detailedView = new JButton("Detailed View");
            stockPanels.add(new StockPanel(detailedView, new JLabel("Price: " + settings.toStringPriceSymbol(stocks.get(i).getNumbers().getLast())), new JLabel(stocks.get(i).getName() + "  [" + player.getAmountOfStocksOwned(stocks.get(i).getName()) + "]  ")));
            stockPanels.get(i).init();
            dashboardPanel.add(stockPanels.get(i));
            disabledButtons.add(detailedView);

            int finalI = i;
            detailedView.addActionListener(e -> {
                dispose();
                new TradeWindow(gameMechanics, stocks.get(finalI).getNumbers(), player, stocks.get(finalI).getName(), date, settings).init(stocks);
            });
        }
        JPanel stockPanel = new JPanel();
        stockPanel.setOpaque(false);
        stockPanel.add(dashboardPanel);

        backgroundPanel.add(stockPanel, BorderLayout.CENTER);

        updateText();
    }

    /**
     * Executes a single simulation tick. This increments the market,
     * updates the in-game date, and refreshes the UI text.
     */
    @Override
    public void update() {
        gameMechanics.addNextNumber();
        date = gameMechanics.updateDate(date);
        updateText();
    }

    /**
     * Refreshes the date label and iterates through all stock panels on the dashboard
     * to update their displayed price. It compares the current price with the price
     * from 7 days ago to show a green 'up' arrow or a red 'down' arrow.
     */
    public void updateText() {
        int priceNow;
        int priceBefore;
        int indexBefore;
        LinkedList<Integer> yHistory;
        for (int i = 0; i < stockPanels.size(); i++) {
            yHistory = stocks.get(i).getNumbers();
            indexBefore = yHistory.size() - 8;
            if (indexBefore < 0){
                indexBefore = 0;
            }

            priceNow = yHistory.getLast();
            priceBefore = yHistory.get(indexBefore);

            if (priceNow < priceBefore) {
                stockPanels.get(i).getLabelPrice().setForeground(Color.RED);
                stockPanels.get(i).getLabelPrice().setText("Price: ↓" + settings.toStringPriceSymbol(priceNow));
            } else {
                stockPanels.get(i).getLabelPrice().setForeground(Color.GREEN);
                stockPanels.get(i).getLabelPrice().setText("Price: ↑" + settings.toStringPriceSymbol(priceNow));
            }
        }
        dateLabel.setText(date.get(0) + "." + date.get(1) + "." + date.get(2));
    }
}
