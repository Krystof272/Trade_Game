package Others;

import javax.swing.*;
import java.awt.*;

/**
 * A utility class providing static methods to apply predefined visual styles
 * to JButtons across the application.
 */
public class CustomButton {
    
    /**
     * Styles the button with a red background, black text, and a size 30 font.
     * Also removes border and focus painting.
     *
     * @param button The JButton to style.
     */
    public static void changeRed(JButton button) {
        button.setBackground(new Color(255, 0, 0, 255));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    /**
     * Styles the button with a green background, black text, and a size 30 font.
     * Also removes border and focus painting.
     *
     * @param button The JButton to style.
     */
    public static void changeGreen(JButton button) {
        button.setBackground(new Color(17, 255, 0, 255));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    /**
     * Styles the button with a light gray background, white text, and a size 35 font.
     * Also removes border and focus painting.
     *
     * @param button The JButton to style.
     */
    public static void changeLightGray35(JButton button) {
        button.setBackground(new Color(112, 112, 112, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 35));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    /**
     * Styles the button with a dark gray background, white text, and a size 20 font.
     * Also removes border and focus painting.
     *
     * @param button The JButton to style.
     */
    public static void changeDarkGrey20(JButton button) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
}
