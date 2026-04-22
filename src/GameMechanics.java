import java.util.LinkedList;
import java.util.Random;

public class GameMechanics {
    private final Random rnd;
    private LinkedList<Integer> yHistory;

    public GameMechanics() {
        this.rnd = new Random();
        this.yHistory = new LinkedList<>();
    }

    public void addNextNumber() {
        int number = yHistory.getLast() + rnd.nextInt(-200, 200);
        if (number < 0){
            number = 0;
        }
        yHistory.add(number);
    }

    public LinkedList<Integer> get_yHistory() {
        return yHistory;
    }

    public void addTo_yHistory(int number) {
        this.yHistory.add(number);
    }
}