package Run;

import GameMechanics.Settings;
import Others.CustomButton;

import javax.swing.*;
import java.util.ArrayList;

public abstract class MyWindow extends JFrame {
    public abstract void update();

    public void toDoStop(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
        CustomButton.changeGreen(buttons.getFirst());
    }

    public void run(ArrayList<JButton> buttons, Settings settings) {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        CustomButton.changeRed(buttons.getFirst());

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
