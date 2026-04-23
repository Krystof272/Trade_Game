import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final GameMechanics gameMechanics;
    private JButton b2;
    private JLabel l1;

    public MainWindow() {
        this.gameMechanics = new GameMechanics();
        this.b2 = new JButton("Next Week");
        this.l1 = new JLabel("Price: ↓" + gameMechanics.get_yHistory().getLast());
    }

    public void init() {
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        JButton b1 = new JButton("Detailed view");
        CustomButton.changeBlue(b1);
        p1.add(b1, BorderLayout.SOUTH);

        l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        l1.setForeground(Color.RED);
        p1.add(l1, BorderLayout.NORTH);


        northPanel.add(p1);

        add(northPanel, BorderLayout.NORTH);


        CustomButton.changeRed(b2);
        b2.setEnabled(false);

        southPanel.add(b2);

        add(southPanel, BorderLayout.SOUTH);


        b1.addActionListener(e -> {
            dispose();
            new TradeWindow(gameMechanics).init();
        });

        b2.addActionListener(e -> {
            b2.setEnabled(false);
            CustomButton.changeRed(b2);
            run();
        });

        setVisible(true);

        run();
    }

    public void run() {
        int[] count = {0};
        Timer timer = new Timer(1000, e -> {
            if (count[0] < 7) {
                update();
                count[0]++;
            } else {
                ((Timer) e.getSource()).stop();
                b2.setEnabled(true);
                CustomButton.changeGreen(b2);
            }
        });
        timer.start();
    }

    public void update() {
        gameMechanics.addNextNumber();

        int price = gameMechanics.get_yHistory().getLast();
        if (price > gameMechanics.get_yHistory().get(gameMechanics.get_yHistory().size() - 2)) {
            l1.setForeground(Color.GREEN);
            l1.setText("Price: ↑" + price);
        } else {
            l1.setForeground(Color.RED);
            l1.setText("Price: ↓" + price);
        }

    }
}
