package Others;

import javax.swing.*;
import java.awt.*;

public class CustomButton {
    public static void changeRed(JButton button) {
        button.setBackground(new Color(255, 0, 0, 255));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public static void changeGreen(JButton button) {
        button.setBackground(new Color(17, 255, 0, 255));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public static void changeLightGray35(JButton button) {
        button.setBackground(new Color(112, 112, 112, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 35));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public static void changeDarkGrey20(JButton button) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
}
