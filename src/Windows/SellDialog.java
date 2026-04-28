package Windows;

import GameMechanics.GameMechanics;
import GameMechanics.Player;
import Others.CustomButton;

import javax.swing.*;
import java.awt.*;

public class SellDialog extends JDialog {
    public SellDialog(JFrame parent, GameMechanics gameMechanics, Player player, String shareName, int currentPrice) {
        super(parent, "Sell " + shareName, true);

        setLayout(new BorderLayout());
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setResizable(false);

        int maxAffordable = player.getAmountOfStocksOwned(shareName);
        int initialValue;
        if (maxAffordable > 0) {
            initialValue = 1;
        } else {
            initialValue = 0;
        }

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel amountLabel = new JLabel("Amount to sell:");
        amountLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(initialValue, initialValue, maxAffordable, 1);
        JSpinner amountSpinner = new JSpinner(spinnerModel);

        JLabel priceTextLabel = new JLabel("Money gained:");
        JLabel priceValueLabel = new JLabel("$" + (initialValue * currentPrice));
        priceValueLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
        priceTextLabel.setFont(new Font("Times New Roman", Font.PLAIN, 19));

        centerPanel.add(amountLabel);
        centerPanel.add(amountSpinner);
        centerPanel.add(priceTextLabel);
        centerPanel.add(priceValueLabel);

        amountSpinner.addChangeListener(e -> {
            int amount = (int) amountSpinner.getValue();
            priceValueLabel.setText("$" + (amount * currentPrice));
        });

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        CustomButton.changeGreen(confirmButton);
        CustomButton.changeRed(cancelButton);

        if (maxAffordable == 0) {
            amountSpinner.setEnabled(false);
            confirmButton.setEnabled(false);
        }

        confirmButton.addActionListener(e -> {
            gameMechanics.sellStock(shareName, currentPrice, (int) amountSpinner.getValue());
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        bottomPanel.add(confirmButton);
        bottomPanel.add(cancelButton);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}