import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainWindow extends MyWindow {
    private GameMechanics gameMechanics;
    private JButton button2;
    private ArrayList<StockPanel> stockPanels;
    private ArrayList<Stock> stocks;

    public MainWindow(GameMechanics gameMechanics, ArrayList<Stock> stocks){
        this.gameMechanics = gameMechanics;
        this.button2 = new JButton("Next Week");
        this.stockPanels = new ArrayList<>();
        this.stocks = stocks;
    }

    public void init() {
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        for (int i = 0; i < stocks.size(); i++) {
            stockPanels.add(new StockPanel(new JButton("Detailed view"), new JLabel("Price: " + stocks.get(i).getNumbers().getLast()), new JLabel(stocks.get(i).getName())));
            stockPanels.get(i).init();
            northPanel.add(stockPanels.get(i));

            int finalI = i;
            stockPanels.get(i).getButton().addActionListener(e -> {
                dispose();
                new TradeWindow(gameMechanics, stocks.get(finalI).getNumbers()).init(stocks);
            });
        }
        if (stocks.getFirst().getNumbers().size() > 1){
            updateText();
        }

        CustomButton.changeGreen(button2);
        southPanel.add(button2);

        add(southPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);


        button2.addActionListener(e -> {
            button2.setEnabled(false);
            CustomButton.changeRed(button2);
            run(button2);
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
