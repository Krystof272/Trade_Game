import javax.swing.*;
import java.awt.*;

public class CustomButton {
    public static void changeRed(JButton button) {
        button.setBackground(new Color(255, 0, 0, 255));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public static void changeGreen(JButton button) {
        button.setBackground(new Color(17, 255, 0, 255));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public static void changeBlue(JButton button) {
        button.setBackground(new Color(33, 85, 255, 255));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }


}
