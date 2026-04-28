package Windows;

import Others.CustomButton;

import javax.swing.*;
import java.awt.*;

public class StockPanel extends JPanel {
    private final JButton button;
    private final JLabel labelPrice;
    private final JLabel labelName;

    public StockPanel(JButton button, JLabel labelPrice, JLabel labelName) {
        this.button = button;
        this.labelPrice = labelPrice;
        this.labelName = labelName;
    }

    public void init(){
        setLayout(new GridLayout(3, 1));

        add(labelName);
        add(labelPrice);
        add(button);

        labelPrice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        labelName.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        CustomButton.changeBlue20(button);
    }

    public JLabel getLabelPrice() {
        return labelPrice;
    }
}
