package gameengine.application.view;

/** Sets the differents panels
 * @author Éric YU
 * @contributor Cédric ABDELBAKI
 *              - Added : javadoc
 *              - Added : commandsPanel attribute
 *              - Added : creditsPanel attribute
 *              - Added : typesPanel attribute
 *              - Added : setCommandsPanel() method
 *              - Added : setCreditsPanel() method
 *              - Added : setTypesPanel() method
 */

public class PanelMediator {

	// The game panel
    private GamePanel gamePanel;

    // The main menu panel
    private MenuPanel menuPanel;

    // The commands information panel
    private CommandsPanel commandsPanel;

	// The credits panel
	private CreditsPanel creditsPanel;

    // The type selection panel for map creation
    private TypesPanel typesPanel;

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setMenuPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public void setCommandsPanel(CommandsPanel commandsPanel) {
    	this.commandsPanel = commandsPanel;
    }

    public void setCreditsPanel(CreditsPanel creditsPanel) {
    	this.creditsPanel = creditsPanel;
    }

    public void setSelectType(TypesPanel typesPanel) {
    	this.typesPanel = typesPanel;
    }

    public GamePanel getGamePanel() {
        return (gamePanel);
    }

    public MenuPanel getMenuPanel() {
        return (menuPanel);
    }
}

