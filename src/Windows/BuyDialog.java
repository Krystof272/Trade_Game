package Windows;

import GameMechanics.GameMechanics;
import GameMechanics.Player;
import GameMechanics.Settings;
import Others.CustomButton;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog window that allows the user to buy a specific amount of a stock.
 * The dialog displays a spinner to choose the quantity and dynamically updates
 * the total cost based on the current market price.
 */
public class BuyDialog extends JDialog {

    /**
     * Constructs and displays a BuyDialog.
     *
     * @param parent        The parent JFrame (usually the TradeWindow).
     * @param gameMechanics The GameMechanics instance to process the transaction.
     * @param player        The current Player.
     * @param shareName     The name of the stock being purchased.
     * @param currentPrice  The current price of the stock per share.
     * @param settings      The game settings (used for formatting currency).
     */
    public BuyDialog(JFrame parent, GameMechanics gameMechanics, Player player, String shareName, int currentPrice, Settings settings) {
        super(parent, "Buy " + shareName, true);

        setLayout(new BorderLayout());
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setResizable(false);

        int maxAffordable = player.getMoney() / currentPrice;
        int initialValue;
        if (maxAffordable > 0) {
            initialValue = 1;
        } else {
            initialValue = 0;
        }

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel amountLabel = new JLabel("Amount to buy:");
        amountLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(initialValue, initialValue, maxAffordable, 1);
        JSpinner amountSpinner = new JSpinner(spinnerModel);

        JLabel priceTextLabel = new JLabel("Total Price:");
        JLabel priceValueLabel = new JLabel(settings.toStringPriceSymbol(initialValue * currentPrice));
        priceValueLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
        priceTextLabel.setFont(new Font("Times New Roman", Font.PLAIN, 19));

        centerPanel.add(amountLabel);
        centerPanel.add(amountSpinner);
        centerPanel.add(priceTextLabel);
        centerPanel.add(priceValueLabel);

        amountSpinner.addChangeListener(e -> {
            int amount = (int) amountSpinner.getValue();
            priceValueLabel.setText(settings.toStringPriceSymbol(amount * currentPrice));
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
            gameMechanics.buyStock(shareName, currentPrice, (int) amountSpinner.getValue());
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
