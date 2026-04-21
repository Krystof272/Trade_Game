import java.util.Random;

public class GameMechanics {
    private final Random rnd;
    public GameMechanics() {
        rnd = new Random();
    }

    public int change(){
        return  rnd.nextInt(-400, 400);
    }
}