package Windows;

import GameMechanics.Player;
import Others.CustomButton;
import Run.Controler;
import Windows.JPanels.W;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A dialog window that appears when the player reaches the end-game condition
 * (e.g., triggering a money overflow or debt threshold). It calculates and displays
 * the total in-game time elapsed since the start of the game, accompanied by a visual animation.
 */
public class EndGame extends JDialog {
    private W w;
    private Thread thread;

    /**
     * Constructs and displays the EndGame dialog.
     *
     * @param owner  The parent frame that owns this dialog.
     * @param date   The current in-game date.
     * @param player The current player, used to display their name.
     */
    public EndGame(Frame owner, ArrayList<Integer> date, Player player) {
        super(owner, true);
        setSize(600, 400);
        setLocationRelativeTo(owner);
        setResizable(false);
        setTitle("WIN");

        for (int i = 0; i < date.size(); i++) {
            date.set(i, date.get(i) - Controler.getDateStart().get(i));
        }

        if (date.getFirst() < 0) {
            date.set(0, 30 + date.getFirst());
            date.set(1, date.get(1) - 1);
        }
        if (date.get(1) < 0) {
            date.set(1, 11);
            date.set(2, date.get(2) - 1);
        }

        JPanel gridPanel = getJPanel(date, player);
        JPanel textPanel = new JPanel(new FlowLayout());
        textPanel.add(gridPanel);
        add(textPanel, BorderLayout.NORTH);

        w = new W();
        add(w, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Ok");
        CustomButton.changeGreen(okButton);

        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        okButton.addActionListener(e -> {
            thread = null;
            dispose();
        });

        thread = new Thread(this::run);
        thread.start();

        setVisible(true);
    }

    /**
     * Helper method to generate the UI panel that contains the congratulatory text
     * and the total elapsed in-game time.
     *
     * @param date   The calculated elapsed time as [Days, Months, Years].
     * @param player The player object containing the username.
     * @return A constructed JPanel with the appropriate labels.
     */
    private static JPanel getJPanel(ArrayList<Integer> date, Player player) {
        JPanel gridPanel = new JPanel(new GridLayout(2, 1));
        JLabel congratulations = new JLabel("You won!!!    " + player.getName());
        JLabel timeLabel = new JLabel("It took you " + date.getFirst() + " days, " + date.get(1) + " months and " + date.getLast() + " years to overflow the integer!!!");

        congratulations.setFont(new Font("Times New Roman", Font.BOLD, 35));
        timeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JPanel congratulationsPanel = new JPanel(new FlowLayout());
        congratulationsPanel.add(congratulations);

        gridPanel.add(congratulationsPanel);
        gridPanel.add(timeLabel);
        return gridPanel;
    }

    /**
     * The run loop for the animation thread. Continuously calls the paint
     * method on the 'W' panel to animate the text until the thread is stopped.
     */
    public void run() {
        while (thread != null) {
            w.paint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
