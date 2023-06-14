package gameengine.application.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** Allows to create a CommandsPanel object
 * @author Cédric ABDELBAKI
 * @version 0.1
 */
public class CommandsPanel extends ScrollPanePanel {
	
	// The panel containing every commands
	private JPanel commandsPanel;
	
	// A boolean to know if a click in a text field is the first
	private boolean firstClick = true;
	
	/** Constructor : creates a CommandsPanel object
	 * @param pm a PanelMediator object
	 */
	public CommandsPanel(PanelMediator pm) {
		super(pm);
		
        // Creates the panel containing every commands
        commandsPanel = new JPanel();
        commandsPanel.setOpaque(false);
        commandsPanel.setLayout(new BoxLayout(commandsPanel, BoxLayout.Y_AXIS));
        commandsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));   

        // Creates each commands
		createCommand("Move left:", super.gameCommands.getMoveLeftCommand());
		createCommand("Move right:", super.gameCommands.getMoveRightCommand());
		createCommand("Jump:", super.gameCommands.getJumpCommand());
		createCommand("Open menu:", super.gameCommands.getOpenCloseMenuCommand());
		createCommand("Open inventory:", super.gameCommands.getOpenCloseInventoryCommand());
		createCommand("Select item:", super.gameCommands.getNavigatThroughInventoryCommand());
		createCommand("Primary action:", super.gameCommands.getUseItemPrimaryCommand());
		createCommand("Secondary action:", super.gameCommands.getUseItemSecondaryCommand());
		createCommand("Drop item:", super.gameCommands.getDropItemCommand());

		// Creates the commands menu JScrollPane
		super.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		super.scrollPane.setPreferredSize(new Dimension(350, 300));
		super.scrollPane.setViewportView(commandsPanel);
		add(super.scrollPane, gbc);
		
		// Creates the return button
		JButton returnButton = new JButton("RETURN");
		returnButton.addMouseListener(this);
		gbc.weighty = 2;
		defineButtonStyle(returnButton);
		add(returnButton, gbc);
	}
	
	/** Creates a new command added to the commands containing every commands
	 * @param commandAction What the command does
	 * @param commandKey The default command to assign
	 */
	public void createCommand(String commandAction, int commandKey) {

		// Creates a panel containing the label and the text field
		JPanel commandPanel = new JPanel(new GridBagLayout());
		GridBagConstraints cPGBC = new GridBagConstraints();
		commandPanel.setOpaque(false);
		commandsPanel.add(commandPanel);
		
		// Creates a label representing the action of the command
		JLabel commandLabel = new JLabel(commandAction);
		Font commandFont = new Font("Atari", Font.PLAIN, 24);
		commandLabel.setFont(commandFont);
		commandLabel.setForeground(Color.WHITE);
		cPGBC.anchor = GridBagConstraints.WEST;
		cPGBC.weightx = 0;
		commandPanel.add(commandLabel, cPGBC);
		
		// Creates a text field representing the command
		String commandString = KeyEvent.getKeyText(commandKey);
		JTextField commandTextField = new JTextField(commandString);
		commandTextField.setPreferredSize(new Dimension(80, 80));
		commandTextField.setOpaque(false);
		commandTextField.setForeground(Color.WHITE);
		commandTextField.setFont(commandFont);
		commandTextField.setHorizontalAlignment(SwingConstants.CENTER);
		cPGBC.anchor = GridBagConstraints.EAST;
		cPGBC.weightx = 1;
		commandPanel.add(commandTextField, cPGBC);
		
		/** Allows to call KeyAdapter methods on the text field
		 */
		commandTextField.addKeyListener(new KeyAdapter() {
			
			// Avoids the display of multiple letters when pressing the key
			@Override
			public void keyPressed(KeyEvent e) {
				commandTextField.setText(null);	
				commandTextField.setForeground(Color.decode("#FFDE59"));	
			}
			
			// Modifies the command in the CommandsMap object and changes the command in the text field
			@Override
			public void keyReleased(KeyEvent e) {
			    InputEvent commandKey = e;
			    JPanel parentPanel = (JPanel) commandTextField.getParent();
			    JLabel associatedLabel = null;
			    for (Component component : parentPanel.getComponents()) {
			        if (component instanceof JLabel && component != commandTextField) {
			            associatedLabel = (JLabel) component;
			            break;
			        }
			    }
			    assignCommand(associatedLabel, commandKey);
			    String keyText = KeyEvent.getKeyText(e.getKeyCode()).toLowerCase();
			    commandTextField.setText(keyText);
			    commandTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			    commandTextField.setForeground(Color.WHITE);
			    commandTextField.getParent().requestFocus();
			    firstClick = true;
			}
		});
		
		/** Allows to call MouseAdapter methods on the text field
		 */
			commandTextField.addMouseListener(new MouseAdapter() {
			
			// Modifies the command in the CommandsMap object and changes the command in the text field
			@Override
			public void mousePressed(MouseEvent e) {
				if (firstClick) {
					firstClick = false;
					commandTextField.setText(null);
					commandTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDE59")));
				} else {
					InputEvent mouseButton = e;
				    JPanel parentPanel = (JPanel) commandTextField.getParent();
				    JLabel associatedLabel = null;
				    for (Component component : parentPanel.getComponents()) {
				        if (component instanceof JLabel && component != commandTextField) {
				            associatedLabel = (JLabel) component;
				            break;
				        }
				    }
				    assignCommand(associatedLabel, mouseButton);
					String mouseText;
				    int button = e.getButton();
					switch (button) {
					case MouseEvent.BUTTON1:
						mouseText = "MOUSE1";
						break;
					case MouseEvent.BUTTON2:
						mouseText = "MOUSE2";
						break;
					case MouseEvent.BUTTON3:
						mouseText = "MOUSE3";
						break;
					default:
						mouseText = "UnknowMouseButton";
						break;
					}
					commandTextField.setText(mouseText);
					commandTextField.setForeground(Color.decode("#FFDE59"));	
					commandTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#FFDE59")));
					firstClick = true;
				}
			}
			
			// Allows to change back the borders and the text color to white
			@Override
			public void mouseReleased(MouseEvent e) {
				if (firstClick) {
					commandTextField.setForeground(Color.WHITE);	
					commandTextField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}
			}
		});
	}
	
	/** Modifies the CommandsMap map object commands
	 * @param commandLabel The label to know what command to change
	 * @param command The new command to apply
	 */
	public void assignCommand(JLabel commandLabel, InputEvent command) {
		String commandLabelText = commandLabel.getText();
		switch(commandLabelText) {
			case "Move left:":
				super.gameCommands.setMoveLeftCommand(command);
			case "Move right:":
				super.gameCommands.setMoveRightCommand(command);
			case "Jump:":
				super.gameCommands.setJumpCommand(command);
			case "Open menu:":
				super.gameCommands.setOpenCloseMenuCommand(command);
			case "Open inventory:":
				super.gameCommands.setOpenCloseInventoryCommand(command);
			case "Select item:":
				super.gameCommands.setNavigateThroughInventoryCommand(command);
			case "Primary action:":
				super.gameCommands.setUseItemPrimaryCommand(command);
			case "Secondary action:":
				super.gameCommands.setUseItemSecondaryCommand(command);
			case "Drop item:":
				super.gameCommands.setDropItemCommand(command);
			default:
				break;
		}
	}
}
