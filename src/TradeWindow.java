import javax.swing.*;
import java.awt.*;

public class TradeWindow extends JFrame {
    private PaintLine paintLine;
    private final GameMechanics gameMechanics;
    private JButton b1;

    public TradeWindow(GameMechanics gameMechanics) {
        this.paintLine = new PaintLine();
        this.gameMechanics = gameMechanics;
        this.b1 = new JButton("Next Week");
    }

    public void init() {
        setTitle("Trade Window");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(paintLine, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        b1.setEnabled(false);
        CustomButton.changeRed(b1);
        panel.add(b1, BorderLayout.EAST);

        JButton b2 = new JButton("<--");
        CustomButton.changeRed(b2);
        panel.add(b2, BorderLayout.WEST);

        add(panel, BorderLayout.NORTH);


        b1.addActionListener(e -> {
            CustomButton.changeRed(b1);
            b1.setEnabled(false);
            run();
        });

        b2.addActionListener(e -> {
            dispose();
            new MainWindow().init();
        });

        setVisible(true);

        run();
    }

    public void run() {
        gameMechanics.setHeight(getHeight());

        int[] count = {0};
        Timer timer = new Timer(1000, e -> {
            if (count[0] < 7) {
                update();
                count[0]++;
            } else {
                ((Timer) e.getSource()).stop();
                b1.setEnabled(true);
                CustomButton.changeGreen(b1);
            }
        });
        timer.start();
    }

    public void update() {
        gameMechanics.addNextNumber();
        paintLine.paint(gameMechanics.get_yHistory());
    }
}
