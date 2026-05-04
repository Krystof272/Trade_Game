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
import javax.imageio.ImageIO;

public class MainWindow extends MyWindow {
    private final GameMechanics gameMechanics;
    private final JButton button2;
    private final ArrayList<StockPanel> stockPanels;
    private final ArrayList<Stock> stocks;
    private final Player player;
    private final ArrayList<JButton> disabledButtons;
    private ArrayList<Integer> date;
    private JLabel dateLabel;
    private Settings settings;

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

    public void firstStart() {
        button2.setEnabled(false);
        CustomButton.changeRed(button2);
        run(disabledButtons, settings);
    }

    public void init() {
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("resources/" + settings.getImageBackgroundName()));
        } catch (Exception e) {
            System.err.println("Could not load background image: " + e.getMessage());
            e.printStackTrace();
        }
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

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

        JLabel playerMoneyLabel = new JLabel("Money: " + player.getMoneyText(settings.getCurrency()));
        playerMoneyLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        JPanel playerMoneyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playerMoneyPanel.setOpaque(false);
        playerMoneyPanel.add(playerMoneyLabel);
        southPanel.add(playerMoneyPanel);

        southPanel.add(new JPanel() {{
            setOpaque(false);
        }});

        JPanel dashboardPanel = new JPanel(new GridLayout(2, stocks.size() / 2));
        dashboardPanel.setOpaque(false);
        for (int i = 0; i < stocks.size(); i++) {
            JButton detailedView = new JButton("Detailed View");
            stockPanels.add(new StockPanel(detailedView, new JLabel("Price: " + stocks.get(i).getNumbers().getLast() + " " + settings.getCurrency()), new JLabel(stocks.get(i).getName() + "  [" + player.getAmountOfStocksOwned(stocks.get(i).getName()) + "]")));
            stockPanels.get(i).init();
            dashboardPanel.add(stockPanels.get(i));
            disabledButtons.add(detailedView);

            int finalI = i;
            detailedView.addActionListener(e -> {
                dispose();
                new TradeWindow(gameMechanics, stocks.get(finalI).getNumbers(), player, stocks.get(finalI).getName(), date, settings).init(stocks);
            });
        }
        if (stocks.getFirst().getNumbers().size() > 1) {
            updateText();
        }
        JPanel stockPanel = new JPanel();
        stockPanel.setOpaque(false);
        stockPanel.add(dashboardPanel);
        backgroundPanel.add(southPanel, BorderLayout.SOUTH);
        backgroundPanel.add(stockPanel, BorderLayout.CENTER);

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

    @Override
    public void update() {
        gameMechanics.addNextNumber();
        date = gameMechanics.updateDate(date);
        updateText();
    }

    public void updateText() {
        int priceNow;
        int priceBefore;
        LinkedList<Integer> yHistory;
        for (int i = 0; i < stockPanels.size(); i++) {
            yHistory = stocks.get(i).getNumbers();
            priceNow = yHistory.getLast();
            priceBefore = yHistory.get(yHistory.size() - 2);

            if (priceNow > priceBefore) {
                stockPanels.get(i).getLabelPrice().setForeground(Color.GREEN);
                stockPanels.get(i).getLabelPrice().setText("Price: ↑" + priceNow + " " + settings.getCurrency());
            } else {
                stockPanels.get(i).getLabelPrice().setForeground(Color.RED);
                stockPanels.get(i).getLabelPrice().setText("Price: ↓" + priceNow + " " + settings.getCurrency());
            }
        }
        dateLabel.setText(date.get(0) + "." + date.get(1) + "." + date.get(2));
    }
}
