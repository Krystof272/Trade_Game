import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TradeWindow extends JFrame {
    private final PaintLine paintLine;
    private final GameMechanics gameMechanics;
    private final ArrayList<Integer> yHistory;

    public TradeWindow() {
        this.gameMechanics = new GameMechanics();
        this.paintLine = new PaintLine();
        this.yHistory = new ArrayList<>();
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
        yHistory.add(gameMechanics.change());
        paintLine.paint(yHistory);
    }

    public void run() {
        yHistory.add(getHeight());

        Timer timer = new Timer(1000, e -> update());
        timer.start();
    }

}
