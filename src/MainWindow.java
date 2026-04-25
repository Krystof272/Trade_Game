import javax.swing.*;
import java.awt.*;

public class MainWindow extends MyWindow {
    private final GameMechanics gameMechanics;
    private JButton button2;
    private JLabel label;

    public MainWindow() {
        this.gameMechanics = new GameMechanics();
        this.button2 = new JButton("Next Week");
        this.label = new JLabel("Price: ↓" + gameMechanics.get_yHistory().getLast());
    }

    public void init() {
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        JButton button1 = new JButton("Detailed view");
        CustomButton.changeBlue(button1);
        panel1.add(button1, BorderLayout.SOUTH);

        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setForeground(Color.RED);
        panel1.add(label, BorderLayout.NORTH);


        northPanel.add(panel1);

        add(northPanel, BorderLayout.NORTH);


        CustomButton.changeGreen(button2);
        southPanel.add(button2);

        add(southPanel, BorderLayout.SOUTH);


        button1.addActionListener(e -> {
            dispose();
            new TradeWindow(gameMechanics).init();
        });

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
        int price = gameMechanics.get_yHistory().getLast();
        if (price > gameMechanics.get_yHistory().get(gameMechanics.get_yHistory().size() - 2)) {
            label.setForeground(Color.GREEN);
            label.setText("Price: ↑" + price);
        } else {
            label.setForeground(Color.RED);
            label.setText("Price: ↓" + price);
        }
    }
}
