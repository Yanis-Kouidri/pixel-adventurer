package game.gameloop;

import java.util.logging.Logger;

public abstract class GameLoop {

    protected final Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    protected volatile GameStatus status;

    private Thread gameThread;

    public GameLoop() {
        status = GameStatus.STOPPED;
    }

    public void run() {
        status = GameStatus.RUNNING;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    public void stop() {
        status = GameStatus.STOPPED;
    }

    public boolean isGameRunning() {
        return status == GameStatus.RUNNING;
    }

    protected void processInput() {
        try {
            int lag = 300;
            Thread.sleep(lag);
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
        }
    }

    protected abstract void render();

    protected abstract void processGameLoop();
}
