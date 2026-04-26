import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TradeWindow extends MyWindow{
    private PaintLine paintLine;
    private final GameMechanics gameMechanics;
    private LinkedList<Integer> yHistory;
    private JButton button1;

    public TradeWindow(GameMechanics gameMechanics, LinkedList<Integer> yHistoryInput) {
        this.paintLine = new PaintLine();
        this.gameMechanics = gameMechanics;
        this.button1 = new JButton("Next Week");
        this.yHistory = yHistoryInput;
    }

    public void init(ArrayList<Stock> stocks) {
        setTitle("Trade Window");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(paintLine, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        CustomButton.changeGreen(button1);
        panel.add(button1, BorderLayout.EAST);

        JButton button2 = new JButton("←");
        CustomButton.changeRed(button2);
        panel.add(button2, BorderLayout.WEST);

        add(panel, BorderLayout.NORTH);


        button1.addActionListener(e -> {
            CustomButton.changeRed(button1);
            button1.setEnabled(false);
            run(button1);
        });

        button2.addActionListener(e -> {
            dispose();
            new MainWindow(gameMechanics, stocks).init();
        });

        setVisible(true);

        gameMechanics.setHeight(getHeight());
        paintLine.paint(yHistory);
    }

    @Override
    public void update() {
        gameMechanics.addNextNumber();
        paintLine.paint(yHistory);
    }
}
