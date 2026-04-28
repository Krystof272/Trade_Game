import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainWindow extends MyWindow {
    private final GameMechanics gameMechanics;
    private final JButton button2;
    private final ArrayList<StockPanel> stockPanels;
    private final ArrayList<Stock> stocks;
    private final Player player;
    private final ArrayList<JButton> disabledButtons;

    public MainWindow(GameMechanics gameMechanics, ArrayList<Stock> stocks,  Player player) {
        this.gameMechanics = gameMechanics;
        this.button2 = new JButton("Next Week");
        this.stockPanels = new ArrayList<>();
        this.stocks = stocks;
        this.player = player;
        this.disabledButtons = new ArrayList<>();
    }

    public void firstStart(){
        button2.setEnabled(false);
        CustomButton.changeRed(button2);
        run(disabledButtons);
    }

    public void init() {
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel southPanel = new JPanel(new GridLayout(1, 5));
        southPanel.add(new JPanel());
        southPanel.add(new JPanel());

        CustomButton.changeGreen(button2);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button2);
        southPanel.add(buttonPanel);
        disabledButtons.add(button2);

        JLabel playerMoneyLabel = new JLabel("Money: " + player.getMoney());
        playerMoneyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JPanel playerMoneyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        playerMoneyPanel.add(playerMoneyLabel);
        southPanel.add(playerMoneyPanel);

        southPanel.add(new JPanel());

        JPanel dashboardPanel = new JPanel();
        for (int i = 0; i < stocks.size(); i++) {
            JButton detailedView = new JButton("Detailed View");
            stockPanels.add(new StockPanel(detailedView, new JLabel("Price: " + stocks.get(i).getNumbers().getLast()), new JLabel(stocks.get(i).getName() +"     ["+ player.getAmountOfStocksOwned(stocks.get(i).getName()) +"]")));
            stockPanels.get(i).init();
            dashboardPanel.add(stockPanels.get(i));
            disabledButtons.add(detailedView);

            int finalI = i;
            detailedView.addActionListener(e -> {
                dispose();
                new TradeWindow(gameMechanics, stocks.get(finalI).getNumbers(), player, stocks.get(finalI).getName()).init(stocks);
            });
        }
        if (stocks.getFirst().getNumbers().size() > 1){
            updateText();
        }

        add(southPanel, BorderLayout.SOUTH);
        add(dashboardPanel, BorderLayout.NORTH);

        button2.addActionListener(e -> {
            run(disabledButtons);
        });

        setVisible(true);
    }

    @Override
    public void update() {
        gameMechanics.addNextNumber();
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
                stockPanels.get(i).getLabelPrice().setText("Price: ↑" + priceNow);
            } else {
                stockPanels.get(i).getLabelPrice().setForeground(Color.RED);
                stockPanels.get(i).getLabelPrice().setText("Price: ↓" + priceNow);
            }
        }
    }
}
