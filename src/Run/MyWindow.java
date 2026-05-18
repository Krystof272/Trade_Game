package Run;

import GameMechanics.Settings;
import Others.CustomButton;

import javax.swing.*;
import java.util.ArrayList;

/**
 * An abstract base class for game windows that require a recurring update cycle.
 */
public abstract class MyWindow extends JFrame {
    
    /**
     * An abstract method that must be implemented by subclasses to define
     * what happens during a single simulation tick.
     */
    public abstract void update();

    /**
     * Re-enables all buttons in the provided list and visually resets the
     * primary button (first in the list) back to green.
     * This is typically called when a simulation cycle finishes.
     *
     * @param buttons A list of buttons to re-enable.
     */
    public void toDoStop(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
        CustomButton.changeGreen(buttons.getFirst());
    }

    /**
     * Disables all buttons in the provided list and visually changes the
     * primary button (first in the list) to red to indicate a cycle is running.
     * This is typically called when a simulation cycle starts.
     *
     * @param buttons A list of buttons to disable.
     */
    public void toDoStart(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        CustomButton.changeRed(buttons.getFirst());
    }

    /**
     * Starts a simulation cycle. It disables the provided buttons, runs the
     * update() method exactly 7 times (once per day in a week) using a Timer
     * with the configured delay, and then re-enables the buttons when finished.
     *
     * @param buttons  The UI buttons to disable during the run.
     * @param settings The game settings, used to retrieve the simulation delay.
     */
    public void run(ArrayList<JButton> buttons, Settings settings) {
        toDoStart(buttons);

        int[] count = {0};
        Timer timer = new Timer(settings.getDelay(), e -> {
            if (count[0] < 7) {
                update();
                count[0]++;
            } else {
                ((Timer) e.getSource()).stop();
                toDoStop(buttons);
            }
        });
        timer.start();
    }
}
