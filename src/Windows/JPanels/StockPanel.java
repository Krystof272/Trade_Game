package Windows.JPanels;

import Others.CustomButton;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JPanel that displays summary information for a single stock
 * on the dashboard, including its name, current price, and a button
 * for opening a detailed view.
 */
public class StockPanel extends JPanel {
    private final JButton button;
    private final JLabel labelPrice;
    private final JLabel labelName;

    public StockPanel(JButton button, JLabel labelPrice, JLabel labelName) {
        this.button = button;
        this.labelPrice = labelPrice;
        this.labelName = labelName;
    }

    /**
     * Initializes the panel's layout, adds the components, and applies
     * styling (fonts, colors, and background).
     */
    public void init(){
        setLayout(new GridLayout(3, 1));

        add(labelName);
        add(labelPrice);
        add(button);

        labelPrice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        labelName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        labelName.setForeground(Color.WHITE);

        setOpaque(true);
        setBackground(new Color(83, 83, 83, 255));

        CustomButton.changeDarkGrey20(button);
    }

    public JLabel getLabelPrice() {
        return labelPrice;
    }
}
