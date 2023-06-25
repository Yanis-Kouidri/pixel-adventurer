package gameengine.application.view;

/** Sets the differents panels
 * @author Eric YU
 * @contributor Cédric Abdelbaki
 *              - Added : javadoc
 *              - Added : mainMenuPanel attribute
 *              - Added : commandsPanel attribute
 *              - Added : creditsPanel attribute
 *              - Added : get/setCommandsPanel() methods
 *              - Added : get/setCreditsPanel() methods
 *              - Added : get/setTypesPanel() methods
 */

public class PanelMediator {

	// The game panel
    private GamePanel gamePanel;

    // The commands information panel
    private MainMenuPanel mainMenuPanel;

	// The credits panel
	private CreditsPanel creditsPanel;

    // The type selection panel for map creation
    private CommandsPanel commandsPanel;

    /** Sets a game panel
     * @param gamePanel the panel to set
     */
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /** Sets a main menu panel
     * @param mainMenuPanel the panel to set
     */
    public void setMainMenuPanel(MainMenuPanel mainMenuPanel) {
    	this.mainMenuPanel = mainMenuPanel;
    }

    /** Sets a credits panel
     * @param creditsPanel the panel to set
     */
    public void setCreditsPanel(CreditsPanel creditsPanel) {
    	this.creditsPanel = creditsPanel;
    }
    
    /** Sets a commands panel
     * @param commandsPanel
     */
    public void setCommandsPanel(CommandsPanel commandsPanel) {
    	this.commandsPanel = commandsPanel;
    }

    /** Gets the game panel
     * @return gamePanel The game panel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    
    /** Gets the main menu panel
     * @return mainMenuPanel The main menu panel
     */
    public MainMenuPanel getMainMenuPanel() {
    	return mainMenuPanel;
    }
    
    /** Gets the credits panel
     * @return creditsPanel The creditsPanel
     */
    public CreditsPanel getCreditsPanel() {
    	return creditsPanel;
    }
    
    /** Gets the commands panel
     * @return commandsPanel The commands panel
     */
    public CommandsPanel getCommandsPanel() {
    	return commandsPanel;
    }
}
 
