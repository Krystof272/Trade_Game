import javax.swing.*;
import java.awt.*;

public class TradeWindow extends JFrame {
    private final PaintLine paintLine;
    private final GameMechanics gameMechanics;

    public TradeWindow(GameMechanics gameMechanics) {
        this.paintLine = new PaintLine();
        this.gameMechanics = gameMechanics;
    }

    public void init() {
        setTitle("Trade Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(paintLine, BorderLayout.CENTER);

        setVisible(true);

        run();
    }

    public void update() {
        gameMechanics.addNextNumber();
        paintLine.paint(gameMechanics.get_yHistory());
    }

    public void run() {
        gameMechanics.addTo_yHistory(getHeight());

        Timer timer = new Timer(1000, e -> update());
        timer.start();
    }

}
