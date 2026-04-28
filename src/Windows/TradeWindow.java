package Windows;

import Others.CustomButton;
import Others.MyWindow;
import GameMechanics.GameMechanics;
import GameMechanics.Player;
import GameMechanics.Stock;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TradeWindow extends MyWindow {
    private final PaintLine paintLine;
    private final GameMechanics gameMechanics;
    private final LinkedList<Integer> yHistory;
    private final String shareName;
    private final JLabel playerMoney;
    private final JLabel stocksOwned;
    private final Player player;
    private final JLabel stockPrice;
    private final ArrayList<JButton> disabledButtons;

    public TradeWindow(GameMechanics gameMechanics, LinkedList<Integer> yHistoryInput, Player player, String shareName) {
        this.paintLine = new PaintLine();
        this.gameMechanics = gameMechanics;
        this.yHistory = yHistoryInput;
        this.player = player;
        this.shareName = shareName;
        this.playerMoney = new JLabel("Money: " + player.getMoney());
        this.stocksOwned = new JLabel("Owned: " + player.getAmountOfStocksOwned(shareName));
        this.stockPrice = new JLabel("" + yHistory.getLast());
        this.disabledButtons = new ArrayList<>();
    }

    public void init(ArrayList<Stock> stocks) {
        setTitle("Trade Window");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(paintLine, BorderLayout.CENTER);

        northComponentsInit(stocks);
        southComponentsInit();

        setVisible(true);

        gameMechanics.setHeight(getHeight());
        paintLine.paint(yHistory);
    }

    @Override
    public void update() {
        gameMechanics.addNextNumber();
        paintLine.paint(yHistory);
        updateText();
    }

    public void updateText() {
        playerMoney.setText("Money: " + player.getMoney());
        stocksOwned.setText("Owned: " + player.getAmountOfStocksOwned(shareName));
        stockPrice.setText("" + yHistory.getLast());
    }

    public void southComponentsInit() {
        JPanel southPanel = new JPanel();

        JButton buyMax = new JButton("Buy Max");
        CustomButton.changeBlue35(buyMax);
        southPanel.add(buyMax);

        JButton buyButton = new JButton("Buy");
        CustomButton.changeBlue35(buyButton);
        southPanel.add(buyButton);


        JButton sellButton = new JButton("Sell");
        CustomButton.changeBlue35(sellButton);
        southPanel.add(sellButton);

        JButton sellMax = new JButton("Sell Max");
        CustomButton.changeBlue35(sellMax);
        southPanel.add(sellMax);

        add(southPanel, BorderLayout.SOUTH);

        disabledButtons.add(buyMax);
        disabledButtons.add(buyButton);
        disabledButtons.add(sellButton);
        disabledButtons.add(sellMax);

        buyMax.addActionListener(e -> {
            gameMechanics.buyStock(shareName, yHistory.getLast(), player.getMoney() / yHistory.getLast());
            updateText();
        });

        buyButton.addActionListener(e -> {
            gameMechanics.buyStock(shareName, yHistory.getLast(), 1);
            updateText();
        });

        sellButton.addActionListener(e -> {
            gameMechanics.sellStock(shareName, yHistory.getLast(), 1);
            updateText();
        });

        sellMax.addActionListener(e -> {
            gameMechanics.sellStock(shareName, yHistory.getLast(), player.getAmountOfStocksOwned(shareName));
            updateText();
        });
    }

    public void northComponentsInit(ArrayList<Stock> stocks) {
        JPanel northPanel = new JPanel(new BorderLayout());

        JButton buttonBack = new JButton("←");
        CustomButton.changeRed(buttonBack);
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(buttonBack);

        JButton buttonNextweek = new JButton("Next Week");
        CustomButton.changeGreen(buttonNextweek);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(buttonNextweek);

        JPanel gridPanel = new JPanel(new GridLayout(1, 5));
        gridPanel.add(leftPanel);

        playerMoney.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JPanel leftCenterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 13));
        leftCenterPanel.add(playerMoney);
        gridPanel.add(leftCenterPanel);

        JPanel namePriceStock = new JPanel(new GridLayout(2, 1));
        JLabel labelShareName = new JLabel(shareName, SwingConstants.CENTER);
        labelShareName.setFont(new Font("Times New Roman", Font.BOLD, 35));
        namePriceStock.add(labelShareName);

        stockPrice.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        stockPrice.setHorizontalAlignment(SwingConstants.CENTER);
        namePriceStock.add(stockPrice);

        gridPanel.add(namePriceStock);

        stocksOwned.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JPanel rightCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 13));
        rightCenterPanel.add(stocksOwned);
        gridPanel.add(rightCenterPanel);

        gridPanel.add(rightPanel);
        northPanel.add(gridPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        disabledButtons.add(buttonNextweek);
        disabledButtons.add(buttonBack);

        buttonNextweek.addActionListener(e -> {
            run(disabledButtons);
        });

        buttonBack.addActionListener(e -> {
            dispose();
            new MainWindow(gameMechanics, stocks, player).init();
        });
    }
}
