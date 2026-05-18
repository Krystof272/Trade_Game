package Windows.JPanels;

import GameMechanics.GameMechanics;
import GameMechanics.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A custom JPanel responsible for drawing the stock price graph in the TradeWindow.
 * It dynamically scales the last 7 days of price history, draws X and Y axes,
 * and plots the prices as a line chart colored green (for growth) or red (for loss).
 */
public class PaintLine extends JPanel {
    private final LinkedList<Integer> yHistory;
    private ArrayList<Integer> date;
    private GameMechanics gameMechanics;
    private Settings settings;

    public PaintLine(GameMechanics gameMechanics, Settings settings, ArrayList<Integer> dateInput) {
        this.yHistory = new LinkedList<>();
        this.date = new ArrayList<>();
        this.gameMechanics = gameMechanics;
        this.settings = settings;
        this.date = dateInput;
    }

    /**
     * Updates the graph with the latest price history and requests a repaint.
     *
     * @param yHistoryInput The current price history list.
     */
    public void paint(LinkedList<Integer> yHistoryInput) {
        this.yHistory.addAll(yHistoryInput);
        this.repaint();
    }

    /**
     * Paints the background, axes, and the graphed lines representing the stock price.
     * The method calculates coordinates from the bottom-up using translation.
     *
     * @param g The Graphics context to draw on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.translate(0, getHeight() - 2);

        // Calculate the starting date for the X-axis (7 days prior)
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
        
        // Scale down the last 8 values so they fit on the screen properly
        for (int i = 0; i < 8; i++) {
            int last7xIndex = yHistory.size() - 8 + i;
            yHistory.set(last7xIndex, yHistory.get(last7xIndex) / 8);
        }

        int yDate = -5;
        int yXaxis = yDate - 40;
        int yGraphLineOfset = yXaxis - 10;
        g2d.drawLine(0, yXaxis, getWidth(), yXaxis);

        int xValueOffset = 120;
        if (settings.getCurrency().equals("$") || settings.getCurrency().equals("€")){
            xValueOffset = 90;
        }
        int xBefore = xValueOffset;
        int xNow;
        int yNow;
        int yBefore;

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        
        // Draw the 7 lines representing the past week
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

    /**
     * Draws a single line segment of the graph, colored green if the price rose,
     * or red if the price fell compared to the previous tick.
     *
     * @param g2d              The Graphics2D context.
     * @param y_now            The Y coordinate (price) at the current point.
     * @param y_before         The Y coordinate (price) at the previous point.
     * @param x_now            The X coordinate of the current point.
     * @param x_before         The X coordinate of the previous point.
     * @param y_GraphLineOfset The vertical offset used to keep the graph above the X-axis.
     */
    public void graphLine(Graphics g2d, int y_now, int y_before, int x_now, int x_before, int y_GraphLineOfset) {
        if (y_now > y_before) {
            g2d.setColor(Color.GREEN);
        } else {
            g2d.setColor(Color.RED);
        }

        g2d.drawLine(x_before, -y_before + y_GraphLineOfset, x_now, -y_now + y_GraphLineOfset);
    }

    /**
     * Draws an X-axis tick mark and its corresponding date label.
     *
     * @param g2d     The Graphics2D context.
     * @param x_now   The X coordinate for the tick mark.
     * @param y_Xaxis The Y coordinate for the main X-axis line.
     * @param yDate   The Y coordinate where the date text should be rendered.
     */
    public void xAxis(Graphics g2d, int x_now, int y_Xaxis, int yDate) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x_now, y_Xaxis + 10, x_now, y_Xaxis - 10);

        date = gameMechanics.updateDate(date);
        g2d.drawString(date.get(0) + "." + date.get(1) + "." + date.get(2), x_now - 50, yDate);
    }

    /**
     * Draws the main vertical Y-axis along with horizontal tick marks
     * and labels indicating the price levels.
     *
     * @param g2d       The Graphics2D context.
     * @param yStart    The starting Y coordinate on the bottom.
     * @param topOffset The margin from the top of the panel.
     * @param xValue    The X coordinate of the vertical Y-axis line.
     */
    public void yAxis(Graphics g2d, int yStart, int topOffset, int xValue) {
        g2d.setColor(Color.BLACK);
        int amount = 4;
        int yOffset = -(getHeight() - yStart - topOffset) / amount;
        int y_now = yStart + yOffset;

        g2d.drawLine(xValue, 0, xValue, -getHeight());
        for (int i = 0; i < amount; i++) {
            g2d.drawString(settings.toStringPriceSymbol((1250 * (i + 1))), 5, y_now + 7);
            g2d.drawLine(xValue - 10, y_now, xValue + 10, y_now);
            y_now += yOffset;
        }
    }
}