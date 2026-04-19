import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private PaintLine paintLine;

    public MainWindow(){
        this.paintLine = new PaintLine();
    }

    public void init(){
        setTitle("Trade Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLayout(new BorderLayout());

        add(paintLine, BorderLayout.CENTER);

        setVisible(true);
    }

    public void update(int y){
        paintLine.paint(y);
    }
}
