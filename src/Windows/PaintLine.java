package Windows;

import GameMechanics.GameMechanics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class PaintLine extends JPanel {
    private LinkedList<Integer> yHistory;
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

        int topOffset = 130;
        int countIncreasedRatio = 1;
        for (int i = 0; i < 7; i++) {
            if (yHistory.get(yHistory.size() - 7 + i) > getHeight() - topOffset) {
                increaseRatio(yHistory);
                countIncreasedRatio++;
            }
        }

        int x_before = 0;
        int x_now;
        int y_now;
        int y_before;

        int y_Date = -5;
        int y_Xaxis = y_Date - 40;
        int y_GraphLineOfset = y_Xaxis - 5;
        g2d.drawLine(0, y_Xaxis, getWidth(), y_Xaxis);

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        for (int i = 0; i < 7; i++) {
            x_now = x_before + getWidth() / 7;
            y_now = yHistory.get(yHistory.size() - 7 + i);
            y_before = yHistory.get(yHistory.size() - 8 + i);

            xAxis(g2d, x_now, y_Xaxis, y_Date);
            graphLine(g2d, y_now, y_before, x_now, x_before, y_GraphLineOfset);

            x_before = x_now;
        }
        yAxis(g2d, y_GraphLineOfset, topOffset, countIncreasedRatio);
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

    public void yAxis(Graphics g2d, int yStart, int topOffset, int countIncreasedRatio) {
        g2d.setColor(Color.BLACK);
        int amount = 4;
        int yOffset = -(getHeight() - yStart - topOffset) / amount;
        int y_now = yStart + yOffset;
        int xValue = 80;

        g2d.drawLine(xValue, 0, xValue, -getHeight());
        for (int i = 0; i < amount; i++) {
            g2d.drawString(String.valueOf((225 * (i + 1)) * countIncreasedRatio), 5, y_now + 7);
            g2d.drawLine(xValue - 10, y_now, xValue + 10, y_now);
            y_now += yOffset;
        }
    }
}