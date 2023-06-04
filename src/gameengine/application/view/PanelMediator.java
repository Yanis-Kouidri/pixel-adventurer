package gameengine.application.view;

public class PanelMediator {
    private GamePanel gamePanel;
    private MenuPanel menuPanel;

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setMenuPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public GamePanel getGamePanel() {
        return (gamePanel);
    }

    public MenuPanel getMenuPanel() {
        return (menuPanel);
    }

}
