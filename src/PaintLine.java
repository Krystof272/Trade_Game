import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintLine extends JPanel {
    private ArrayList<Integer> yHistory;

    public PaintLine() {
        this.yHistory = new ArrayList<>();
    }

    public void paint(ArrayList<Integer> yHistoryInput) {
        yHistory = yHistoryInput;
        this.repaint();
        System.out.println(yHistory);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x_before = 0;
        int x_now;
        int y_now;
        int y_before;

        for (int i = 1; i < yHistory.size(); i++) {
            x_now =  x_before + 50;
            y_now = yHistory.get(i);
            y_before = yHistory.get(i - 1);

            if (y_now < y_before) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }

            g.drawLine(x_before, y_before, x_now, y_now);

            x_before = x_now;
        }
    }

}
