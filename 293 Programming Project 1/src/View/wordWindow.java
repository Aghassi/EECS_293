package View;
/**
 * A class for display the window for input.
 */

/**
 * @author davidaghassi
 */

import javax.swing.*;

public class wordWindow {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createWindow();
	}

	
	private static void createWindow(){
		//Submit button
		JFrame mainWindow = new JFrame();
		//Sets the window size
		mainWindow.setSize(500, 500);
		//Title
		mainWindow.setTitle("EECS 293 Programming Project 1");
		//Not resizable
		mainWindow.setResizable(false);
		//Will close when the red button is clicked.
		mainWindow.setDefaultCloseOperation(3);
		//Makes window visible
		mainWindow.setVisible(true);
		
	}

}