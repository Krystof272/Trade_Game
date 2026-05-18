package Windows.JPanels;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * A custom JPanel that continuously draws the word "WIN" in random colors.
 * Used primarily in the EndGame dialog to create a celebration effect.
 */
public class W extends JPanel {
    public W() {
    }

    public void paint() {
        repaint();
    }

    /**
     * Paints the component, drawing "WIN" in the center with a
     * randomly generated color.
     *
     * @param g The Graphics context to draw on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Random rnd = new Random();

        g2d.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255), 255));
        g2d.setFont(new Font("Times New Roman", Font.BOLD, 150));
        g2d.drawString("WIN", getWidth() / 2 - 170, getHeight() / 2 + 40);
    }
}
