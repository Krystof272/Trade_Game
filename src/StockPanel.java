import javax.swing.*;
import java.awt.*;

public class StockPanel extends JPanel {
    private JButton button;
    private JLabel labelPrice;
    private JLabel labelName;

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

    public JButton getButton() {
        return button;
    }

    public JLabel getLabelPrice() {
        return labelPrice;
    }
}
