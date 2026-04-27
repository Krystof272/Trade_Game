import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TradeWindow extends MyWindow {
    private final PaintLine paintLine;
    private final GameMechanics gameMechanics;
    private final LinkedList<Integer> yHistory;

    public TradeWindow(GameMechanics gameMechanics, LinkedList<Integer> yHistoryInput) {
        this.paintLine = new PaintLine();
        this.gameMechanics = gameMechanics;
        this.yHistory = yHistoryInput;
    }

    public void init(ArrayList<Stock> stocks, String shareName) {
        setTitle("Trade Window");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(paintLine, BorderLayout.CENTER);

        southButtonsInit(shareName);
        northComponentsInit(stocks, shareName);

        setVisible(true);

        gameMechanics.setHeight(getHeight());
        paintLine.paint(yHistory);
    }

    @Override
    public void update() {
        gameMechanics.addNextNumber();
        paintLine.paint(yHistory);
    }

    public void southButtonsInit(String shareName){
        JPanel southPanel = new JPanel();

        JButton buyButton = new JButton("Buy");
        CustomButton.changeBlue(buyButton);
        southPanel.add(buyButton);



        JButton sellButton = new JButton("Sell");
        CustomButton.changeBlue(sellButton);
        southPanel.add(sellButton);

        add(southPanel, BorderLayout.SOUTH);

        buyButton.addActionListener(e -> {
            gameMechanics.buy1Stock(shareName, yHistory.getLast());
        });

        sellButton.addActionListener(e -> {
            gameMechanics.sell1Stock(shareName, yHistory.getLast());
        });
    }

    public void northComponentsInit(ArrayList<Stock> stocks, String shareName){
        JPanel northPanel = new JPanel(new BorderLayout());

        JButton buttonBack = new JButton("←");
        CustomButton.changeRed(buttonBack);
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(buttonBack);

        JButton buttonNextweek = new JButton("Next Week");
        CustomButton.changeGreen(buttonNextweek);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(buttonNextweek);

        JPanel gridPanel = new JPanel(new GridLayout(1, 3));
        gridPanel.add(leftPanel);

        JLabel labelShareName = new JLabel(shareName, SwingConstants.CENTER);
        labelShareName.setFont(new Font("Times New Roman", Font.BOLD, 35));
        gridPanel.add(labelShareName);

        gridPanel.add(rightPanel);

        northPanel.add(gridPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);


        buttonNextweek.addActionListener(e -> {
            CustomButton.changeRed(buttonNextweek);
            buttonNextweek.setEnabled(false);
            run(buttonNextweek);
        });

        buttonBack.addActionListener(e -> {
            dispose();
            new MainWindow(gameMechanics, stocks).init();
        });
    }
}
