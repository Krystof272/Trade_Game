import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private PaintLine paintLine;
    private GameMechanics gameMechanics;

    public MainWindow(){
        this.gameMechanics = new GameMechanics();
        this.paintLine = new PaintLine();
    }

    public void init(){
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(paintLine, BorderLayout.CENTER);
        
        setVisible(true);
        run();
    }

    public void update(int y){
        paintLine.paint(y);
        System.out.println(getHeight());
    }

    public void run(){
        while (true){
            update(gameMechanics.change());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
