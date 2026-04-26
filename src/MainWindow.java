import javax.swing.*;
import java.awt.*;

public class MainWindow extends MyWindow {
    private final GameMechanics gameMechanics;
    private JButton button2;
    private JLabel label;

    public MainWindow() {
        this.gameMechanics = new GameMechanics(getUserName());
        this.button2 = new JButton("Next Week");
        //this.label = new JLabel("Price: ↓" + gameMechanics.getStocks().getFirst().getNumbers().getLast());
        this.label = new JLabel();
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


        CustomButton.changeGreen(button2);
        southPanel.add(button2);

        add(southPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

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
        int price = gameMechanics.getStocks().getFirst().getNumbers().getLast();
        if (price > gameMechanics.getStocks().getFirst().getNumbers().get(gameMechanics.getStocks().getFirst().getNumbers().size() - 2)) {
            label.setForeground(Color.GREEN);
            label.setText("Price: ↑" + price);
        } else {
            label.setForeground(Color.RED);
            label.setText("Price: ↓" + price);
        }
    }

    public String getUserName() {
        String name = null;
        while (name == null || name.isEmpty()) {
            name = JOptionPane.showInputDialog(null, "Please enter your name:", "Name input",  JOptionPane.QUESTION_MESSAGE);
        }
        return name;
    }
}
