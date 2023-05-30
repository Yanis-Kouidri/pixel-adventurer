package gameengine.inventory.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryViewTest {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Inventory frame testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 100);

        // Create the inventory panel
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(1, 9));
        inventoryPanel.setBackground(Color.BLACK);

        // Create an array of item labels
        JLabel[] itemLabels = new JLabel[9];

        // Add the item labels to the inventory panel
        for (int i = 0; i < itemLabels.length; i++) {
            itemLabels[i] = new JLabel();
            itemLabels[i].setOpaque(true);
            itemLabels[i].setBackground(Color.GRAY);
            itemLabels[i].setPreferredSize(new Dimension(40, 40));
            inventoryPanel.add(itemLabels[i]);
        }

        // Update the first item label to simulate a change
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemLabels[0].setBackground(Color.BLUE);
            }
        });
        timer.start();

        frame.add(inventoryPanel);


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryViewTest::createAndShowGUI);
    }
}
