import java.util.LinkedList;
import java.util.Random;

public class GameMechanics {
    private final Random rnd;
    private final LinkedList<Integer> yHistory;
    private int height;

    public GameMechanics() {
        this.rnd = new Random();
        this.yHistory = new LinkedList<>();
        yHistory.add(0);
        this.height = 0;
    }

    public void addNextNumber() {
        int number = yHistory.getLast() + rnd.nextInt( -100, 300);

        if (number < yHistory.getFirst()) {
            number = yHistory.getFirst();
        }
        yHistory.add(number);

        if (number > height - 70) {
            insreaseRatio();
        }
    }

    public LinkedList<Integer> get_yHistory() {
        return yHistory;
    }

    public void insreaseRatio() {
        for (int i = 1; i < yHistory.size(); i++) {
            int number = yHistory.get(i) / 2;

            if (number < yHistory.getFirst()) {
                number = yHistory.getFirst();
            }
            yHistory.set(i, number);
        }
    }

    public void setHeight(int height) {
        this.height = height;
    }
}