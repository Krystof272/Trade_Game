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
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(paintLine, BorderLayout.CENTER);


        b1.setEnabled(false);
        add(b1, BorderLayout.EAST);


        b1.addActionListener(e -> {
            b1.setEnabled(false);
            run();
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
            }
        });
        timer.start();
    }

    public void update() {
        gameMechanics.addNextNumber();
        paintLine.paint(gameMechanics.get_yHistory());
    }
}
