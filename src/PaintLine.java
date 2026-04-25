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
        g.translate(0, getHeight() - 2);

        int x_before = 0;
        int x_now;
        int y_now;
        int y_before;

        if (yHistory.size() > 7) {
            for (int i = 0; i < 7; i++) {
                x_now = x_before + getWidth() / 7;
                y_now = yHistory.get(yHistory.size() - 7 + i);
                y_before = yHistory.get(yHistory.size() - 8 + i);

                if (y_now > y_before) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.RED);
                }

                g.drawLine(x_before, -y_before, x_now, -y_now);

                x_before = x_now;
            }
        } else {
            for (int i = 1; i < yHistory.size(); i++) {
                x_now = x_before + getWidth() / 7;
                y_now = yHistory.get(i);
                y_before = yHistory.get(i - 1);

                if (y_now > y_before) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.RED);
                }

                g.drawLine(x_before, -y_before, x_now, -y_now);

                x_before = x_now;
            }
        }
    }
}
