package Windows;

import Others.CustomButton;
import Run.Controler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EndGame extends JDialog {
    public EndGame(Frame owner, ArrayList<Integer> date) {
        super(owner, true);
        setSize(400, 200);
        setLocationRelativeTo(owner);
        setResizable(false);
        setTitle("WIN");

        for (int i = 0; i < date.size(); i++) {
            System.out.println(Controler.getDateStart().get(i));
            date.set(i, date.get(i) - Controler.getDateStart().get(i));
        }
        //1, 11, 2030
        if (date.getFirst() < 0) {
            date.set(0, 30 + date.getFirst());
            date.set(1, date.get(1) - 1);
        }
        if (date.get(1) < 0) {
            date.set(1, 12);
            date.set(2, date.get(2) - 1);
        }

        JPanel gridPanel = new JPanel(new GridLayout(3, 1));
        JLabel congratulations = new JLabel("Congratulations, you won the game!");
        JLabel timeLabel = new JLabel("It took you " + date.getFirst() + " days, " + date.get(1) + " months, " + date.getLast() +" years");
        JLabel timeLabelNext = new JLabel("to overflow the integer");

        congratulations.setFont(new Font("Times New Roman", Font.BOLD, 20));
        timeLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        timeLabelNext.setFont(new Font("Times New Roman", Font.BOLD, 18));


        gridPanel.add(congratulations);
        gridPanel.add(timeLabel);
        gridPanel.add(timeLabelNext);

        add(gridPanel);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Ok");
        CustomButton.changeGreen(okButton);

        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        okButton.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }
}
