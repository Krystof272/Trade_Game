package Windows;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class PaintLine extends JPanel {
    private LinkedList<Integer> yHistory;

    public PaintLine() {
        this.yHistory = new LinkedList<>();
    }

    public void paint(LinkedList<Integer> yHistoryInput) {
        yHistory = yHistoryInput;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.translate(0, getHeight() - 2);

        for (int i = 0; i < 7; i++) {
            if (yHistory.get(i) > getHeight() - 260) {
                increaseRatio(yHistory);
            }
        }

        int x_before = 0;
        int x_now;
        int y_now;
        int y_before;

        for (int i = 0; i < 7; i++) {
            x_now = x_before + getWidth() / 7;
            y_now = yHistory.get(yHistory.size() - 7 + i);
            y_before = yHistory.get(yHistory.size() - 8 + i);

            if (y_now > y_before) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.RED);
            }

            g2d.drawLine(x_before, -y_before, x_now, -y_now);

            x_before = x_now;
        }
    }

    public void increaseRatio(LinkedList<Integer> yHistory) {
        for (int i = 1; i < yHistory.size(); i++) {
            int number = yHistory.get(i) / 2;

            if (number < 10) {
                number = 10;
            }
            yHistory.set(i, number);
        }
    }
}