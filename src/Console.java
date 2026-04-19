public class Console {
    private GameMechanics gameMechanics;
    private MainWindow mainWindow;

    public Console() {
        this.gameMechanics = new GameMechanics();
        this.mainWindow = new MainWindow();
    }

    public void init(){
        mainWindow.init();

        run();
    }

    public void run(){
        while (true){
            mainWindow.update(gameMechanics.change());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
