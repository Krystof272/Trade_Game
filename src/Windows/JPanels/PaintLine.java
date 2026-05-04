package Windows.JPanels;

import GameMechanics.GameMechanics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PaintLine extends JPanel {
    private final LinkedList<Integer> yHistory;
    private ArrayList<Integer> date;
    private GameMechanics gameMechanics;

    public PaintLine() {
        this.yHistory = new LinkedList<>();
        this.date = new ArrayList<>();
    }

    public void paint(LinkedList<Integer> yHistoryInput, ArrayList<Integer> dateInput, GameMechanics gameMechanics) {
        this.yHistory.addAll(yHistoryInput);
        date = dateInput;
        this.gameMechanics = gameMechanics;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.translate(0, getHeight() - 2);

        date.set(0, date.getFirst() - 7);
        if (date.getFirst() < 1) {
            date.set(0, 30 + date.getFirst());
            date.set(1, date.get(1) - 1);
        }
        if (date.get(1) < 1) {
            date.set(1, 12);
            date.set(2, date.get(2) - 1);
        }

        int topOffset = 120;
        for (int i = 0; i < 8; i++) {
            int last7xIndex = yHistory.size() - 8 + i;
            yHistory.set(last7xIndex, yHistory.get(last7xIndex) / 8);
        }

        int yDate = -5;
        int yXaxis = yDate - 40;
        int yGraphLineOfset = yXaxis - 10;
        g2d.drawLine(0, yXaxis, getWidth(), yXaxis);

        int xValueOffset = 80;
        int xBefore = xValueOffset;
        int xNow;
        int yNow;
        int yBefore;

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        for (int i = 0; i < 7; i++) {
            xNow = xBefore + (getWidth() - xValueOffset) / 7;
            yNow = yHistory.get(yHistory.size() - 7 + i);
            yBefore = yHistory.get(yHistory.size() - 8 + i);

            xAxis(g2d, xNow, yXaxis, yDate);
            graphLine(g2d, yNow, yBefore, xNow, xBefore, yGraphLineOfset);

            xBefore = xNow;
        }
        yAxis(g2d, yGraphLineOfset, topOffset, xValueOffset);
    }

    public void graphLine(Graphics g2d, int y_now, int y_before, int x_now, int x_before, int y_GraphLineOfset) {
        if (y_now > y_before) {
            g2d.setColor(Color.GREEN);
        } else {
            g2d.setColor(Color.RED);
        }

        g2d.drawLine(x_before, -y_before + y_GraphLineOfset, x_now, -y_now + y_GraphLineOfset);
    }

    public void xAxis(Graphics g2d, int x_now, int y_Xaxis, int yDate) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x_now, y_Xaxis + 10, x_now, y_Xaxis - 10);

        date = gameMechanics.updateDate(date);
        g2d.drawString(date.get(0) + "." + date.get(1) + "." + date.get(2), x_now - 50, yDate);
    }

    public void yAxis(Graphics g2d, int yStart, int topOffset, int xValue) {
        g2d.setColor(Color.BLACK);
        int amount = 4;
        int yOffset = -(getHeight() - yStart - topOffset) / amount;
        int y_now = yStart + yOffset;

        g2d.drawLine(xValue, 0, xValue, -getHeight());
        for (int i = 0; i < amount; i++) {
            g2d.drawString(String.valueOf((1250 * (i + 1))), 5, y_now + 7);
            g2d.drawLine(xValue - 10, y_now, xValue + 10, y_now);
            y_now += yOffset;
        }
    }
}