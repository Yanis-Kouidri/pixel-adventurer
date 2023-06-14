package gameengine.application.model;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/** Allows to creates a CommandsMap object that contains the commands to control the game
 * @author Cédric ABDELBAKI
 * @version 0.1
 */
public class CommandsMap{

	// The command that moves the player to the left
	private int moveLeftCommand;

	// The command that moves the player to the right
	private int moveRightCommand;

	// The command that makes the player jump
	private int jumpCommand;

	// The command that opens the game menu
	private int openCloseMenuCommand;

	// The command that opens and closes the inventory
	private int openCloseInventoryCommand;

	// The command that allows to navigate through the inventory
	private int navigateThroughInventoryCommand;

	// The command that allows to use the primary action of an item
	private int useItemPrimaryCommand;

	// The command that allows to use the secondary action of an item
	private int useItemSecondaryCommand;

	// The command that allows to drop an item
	private int dropItemCommand;

	/** Constructor : Instantiates the CommandsMap Object with default commands
	 */
	public CommandsMap() {

		// Movement commands
		this.moveLeftCommand = KeyEvent.VK_Q;
		this.moveRightCommand = KeyEvent.VK_D;
		this.jumpCommand = KeyEvent.VK_SPACE;

		// Interface interaction commands
		this.openCloseMenuCommand = KeyEvent.VK_ESCAPE;
		this.openCloseInventoryCommand = KeyEvent.VK_I;
		this.navigateThroughInventoryCommand = KeyEvent.VK_E;

		// Item interaction commands
		this.useItemPrimaryCommand = MouseEvent.BUTTON1;
		this.useItemSecondaryCommand = MouseEvent.BUTTON2;
		this.dropItemCommand = KeyEvent.VK_R;
	}

	// Gets the command that moves the player to the left
	public int getMoveLeftCommand() {
		return moveLeftCommand;
	}

	// Gets the command that moves the player to the right
	public int getMoveRightCommand() {
		return moveRightCommand;
	}

	// Gets the command that makes the player jump
	public int getJumpCommand() {
		return jumpCommand;
	}

	// Gets the command that opens the game menu
	public int getOpenCloseMenuCommand() {
		return openCloseMenuCommand;
	}

	// Gets the Command that opens and closes the inventory
	public int getOpenCloseInventoryCommand() {
		return openCloseInventoryCommand;
	}

	// Gets the command that allow to navigate through the inventory
	public int getNavigatThroughInventoryCommand() {
		return navigateThroughInventoryCommand;
	}

	// Gets the command that allows to use the primary action of an item
	public int getUseItemPrimaryCommand() {
		return useItemPrimaryCommand;
	}

	// Gets the command that allows to use the secondary action of an item
	public int getUseItemSecondaryCommand() {
		return useItemSecondaryCommand;
	}

	// Gets the command that allows to drop an item
	public int getDropItemCommand() {
		return dropItemCommand;
	}

	// Sets the command that moves the player to the left
	public void setMoveLeftCommand(InputEvent newMoveLeftCommand) {
		if (newMoveLeftCommand instanceof KeyEvent) {
			this.moveLeftCommand = ((KeyEvent) newMoveLeftCommand).getKeyCode();
		} else {
			this.moveLeftCommand = ((MouseEvent) newMoveLeftCommand).getButton();
		}
	}

	// Sets the command that moves the player to the right
	public void setMoveRightCommand(InputEvent newMoveRightCommand) {
		if (newMoveRightCommand instanceof KeyEvent) {
			this.moveRightCommand = ((KeyEvent) newMoveRightCommand).getKeyCode();
		} else {
			this.moveRightCommand = ((MouseEvent) newMoveRightCommand).getButton();
		}
	}

	// Sets the command that makes the player jump
	public void setJumpCommand(InputEvent newJumpCommand) {
		if (newJumpCommand instanceof KeyEvent) {
			this.jumpCommand = ((KeyEvent) newJumpCommand).getKeyCode();
		} else {
			this.jumpCommand = ((MouseEvent) newJumpCommand).getButton();
		}
	}

	// Sets the command that opens and closes the game menu
	public void setOpenCloseMenuCommand(InputEvent newOpenCloseMenuCommand) {
		if (newOpenCloseMenuCommand instanceof KeyEvent) {
			this.openCloseMenuCommand = ((KeyEvent) newOpenCloseMenuCommand).getKeyCode();
		} else {
			this.jumpCommand = ((MouseEvent) newOpenCloseMenuCommand).getButton();
		}
	}

	// Sets the command that opens and closes the inventory
	public void setOpenCloseInventoryCommand(InputEvent newOpenCloseInventoryCommand) {
		if (newOpenCloseInventoryCommand instanceof KeyEvent) {
			this.openCloseInventoryCommand = ((KeyEvent) newOpenCloseInventoryCommand).getKeyCode();
		} else {
			this.openCloseInventoryCommand = ((MouseEvent) newOpenCloseInventoryCommand).getButton();
		}
	}

	// Sets the command that allow to navigate through the inventory
	public void setNavigateThroughInventoryCommand(InputEvent newNavigateThroughInventoryCommand) {
		if (newNavigateThroughInventoryCommand instanceof KeyEvent) {
			this.navigateThroughInventoryCommand = ((KeyEvent) newNavigateThroughInventoryCommand).getKeyCode();
		} else {
			this.navigateThroughInventoryCommand = ((MouseEvent) newNavigateThroughInventoryCommand).getButton();
		}
	}

	// Sets the command that allows to use the primary action of an item
	public void setUseItemPrimaryCommand(InputEvent newUseItemPrimaryCommand) {
		if (newUseItemPrimaryCommand instanceof KeyEvent) {
			this.useItemPrimaryCommand = ((KeyEvent) newUseItemPrimaryCommand).getKeyCode();
		} else {
			this.useItemPrimaryCommand = ((MouseEvent) newUseItemPrimaryCommand).getButton();
		}
	}

	// Sets the command that allows to use the secondary action of an item
	public void setUseItemSecondaryCommand(InputEvent newUseItemSecondaryCommand) {
		if (newUseItemSecondaryCommand instanceof KeyEvent) {
			this.useItemSecondaryCommand = ((KeyEvent) newUseItemSecondaryCommand).getKeyCode();
		} else {
			this.useItemSecondaryCommand = ((MouseEvent) newUseItemSecondaryCommand).getButton();
		}
	}

	// Sets the command that allows to drop an item
	public void setDropItemCommand(InputEvent newDropItemCommand) {
		if (newDropItemCommand instanceof KeyEvent) {
			this.dropItemCommand = ((KeyEvent) newDropItemCommand).getKeyCode();
		} else {
			this.dropItemCommand = ((MouseEvent) newDropItemCommand).getButton();
		}
	}
}
