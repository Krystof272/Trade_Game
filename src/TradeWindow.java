import javax.swing.*;
import java.awt.*;

public class TradeWindow extends MyWindow{
    private PaintLine paintLine;
    private final GameMechanics gameMechanics;
    private JButton button1;

    public TradeWindow(GameMechanics gameMechanics) {
        this.paintLine = new PaintLine();
        this.gameMechanics = gameMechanics;
        this.button1 = new JButton("Next Week");
    }

    public void init() {
        setTitle("Trade Window");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(paintLine, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        CustomButton.changeGreen(button1);
        panel.add(button1, BorderLayout.EAST);

        JButton button2 = new JButton("<--");
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
            new MainWindow().init();
        });

        setVisible(true);

        gameMechanics.setHeight(getHeight());
        paintLine.paint(gameMechanics.getStocks().getFirst().getNumbers());
    }

    @Override
    public void update() {
        gameMechanics.addNextNumber();
        paintLine.paint(gameMechanics.getStocks().getFirst().getNumbers());
    }
}
