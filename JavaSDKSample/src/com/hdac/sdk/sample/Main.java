package com.hdac.sdk.sample;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
			
	/** File separator */
    public static String fileSeparator;

    /** Line separator */
    public static String lineSeparator;

    /** User home */
    public static String userHome;

    /** Operating system */
    public static String osName;

    /** Application identifier */
    public static String applicationID;

    /** Application name */
    public static String applicationName;

    /** Application version */
    public static String applicationVersion;

    /** Application lock file */
    private static RandomAccessFile lockFile;

    /** Application lock */
    private static FileLock fileLock;

    /** Application properties */
    public static Properties properties;

    /** Data directory */
    public static String dataPath;

    /** Application properties file */
    private static File propFile;

    
    private static String folderName = "Hdac";
    
    private static String hdacLibPath;
    
    private static MainWindow mainWindow;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			javax.swing.SwingUtilities.invokeLater(() -> startup());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void startup() {
		createAndShowGUI();
	}
	
	private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        mainWindow = new MainWindow();   
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = screen.width;
        int frameHeight = screen.height;
        HdacSamplePane hdacSamplePane = new HdacSamplePane();
        //mainWindow.add(hdacSamplePane);
        JPanel hdac_pane = new JPanel();
        hdac_pane.add(hdacSamplePane);
        hdac_pane.setBounds(new Rectangle(0, 0, frameWidth, frameHeight));
        mainWindow.add(hdac_pane);
        mainWindow.setVisible(true);
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice vc = env.getDefaultScreenDevice();
        vc.setFullScreenWindow(mainWindow);
        mainWindow.pack();
    }

}



