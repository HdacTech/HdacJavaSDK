package com.hdac.sdk.sample;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu extends JMenu {

    /**
     * Create a JMenu from an item list.  Each item is a String array containing
     * the item label and the item action.
     *
     * @param       listener            Menu action listener
     * @param       label               Menu label
     * @param       items               One or more menu items
     */
    public Menu(ActionListener listener, String label, String[]... items) {
        super(label);
        for (String[] item : items) {
            JMenuItem menuItem = new JMenuItem(item[0]);
            menuItem.setActionCommand(item[1]);
            menuItem.addActionListener(listener);
            add(menuItem);
        }
    }

}
