import java.util.Random;

public class GameMechanics {
    Random rnd;
    public GameMechanics() {
        rnd = new Random();
    }

    public int change(){
        return  rnd.nextInt(-100, 100);
    }
}
