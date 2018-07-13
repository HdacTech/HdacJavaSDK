package com.hdac.sdk.sample;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.hdac.sdk.sample.HdacSamplePane;
import com.hdac.sdk.sample.Menu;

public class MainWindow extends JFrame implements ActionListener{
	/** Main window is minimized */
    private boolean windowMinimized = true;

    /** Synchronizing title set */
    private boolean synchronizingTitle = false;

    /** Rebroadcast pending transactions */
    private boolean txBroadcastDone = false;

    /** Rescanning block chain */
    private boolean rescanChain = false;
    
    private HdacSamplePane hdacSamplePane;
    
    /** Transaction panel */

    /**
     * Create the application window
     */
    public MainWindow() {
        //
        // Create the frame
        //
        super("Hdac Wallet");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage("./res/imgs/ic_hdac.png"));      
        //
        // Position the window using the saved position from the last time
        // the program was run
        //
        int frameX = 320;
        int frameY = 10;

        setLocation(frameX, frameY);
        //
        // Size the window using the saved size from the last time
        // the program was run
        //
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = screen.width;
        int frameHeight = screen.height;
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        //
        // Create the application menu bar
        //
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(new Color(230,230,230));
        //
        // Add the "File" menu to the menu bar
        //
        // The "File" menu contains "Exit"
        //
        menuBar.add(new Menu(this, "Main", new String[] {"Exit", "exit"}));
        
        //
        // Add the "Help" menu to the menu bar
        //
        // The "Help" menu contains "About"
        //
        menuBar.add(new Menu(this, "Help", new String[] {"About", "about"}));
        
        //
        // Add the menu bar to the window frame
        //
        setJMenuBar(menuBar);   
        
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem source = (JMenuItem) (e.getSource());		
		String name = source.getActionCommand();
		System.out.println(name);
		
		switch(name) {			
			case "exit":
				System.exit(0);
				break;
			case "about":
				JOptionPane.showMessageDialog(null,
        			    "©2018 Hdac Technology AG. All Rights Reserved.",
        			    "Sample Hdac SDK Application for Java",
        			    JOptionPane.INFORMATION_MESSAGE);
				break;
		}		
		
	}
}
