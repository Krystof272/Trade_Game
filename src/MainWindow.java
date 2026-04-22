import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final GameMechanics gameMechanics;

    public MainWindow() {
        this.gameMechanics = new GameMechanics();
    }

    public void init() {
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();

        JButton button = new JButton("Open graph");

        panel.add(button);


        add(panel, BorderLayout.NORTH);


        button.addActionListener(e -> {
            dispose();
            new TradeWindow(gameMechanics).init();
        });


        setVisible(true);
    }
}
