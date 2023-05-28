package gameengine.gameloop;

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

    protected void processInput(int updatePerSecond) {
        try {
            Thread.sleep(1000 / updatePerSecond);
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
        }
    }

    protected abstract void render();

    protected abstract void processGameLoop();
}
