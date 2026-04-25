import javax.swing.*;

public abstract class MyWindow extends JFrame {
    public abstract void update();

    public void toDoStop(JButton button) {
        button.setEnabled(true);
        CustomButton.changeGreen(button);
    };

    public void run(JButton button) {
        int[] count = {0};
        Timer timer = new Timer(1000, e -> {
            if (count[0] < 7) {
                update();
                count[0]++;
            } else {
                ((Timer) e.getSource()).stop();
                toDoStop(button);
            }
        });
        timer.start();
    }
}
