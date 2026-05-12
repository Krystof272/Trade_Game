package Windows.JPanels;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class W extends JPanel {
    public W() {
    }

    public void paint() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Random rnd = new Random();

        g2d.setColor(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255), 255));
        g2d.setFont(new Font("Times New Roman", Font.BOLD, 100));
        g2d.drawString("W", getWidth() / 2, getHeight() / 2);
    }
}
