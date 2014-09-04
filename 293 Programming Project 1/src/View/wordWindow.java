package View;
/**
 * A class for display the window for input.
 */

/**
 * @author davidaghassi
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controllers.InputReader;

public class WordWindow extends JFrame {
	static JTextField leftTextField;
	static JTextField rightTextField;
	static JTextArea result = new JTextArea("Type in two strings to be compared");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createWindow();
	}

	
	private static void createWindow(){
		//Text fields
		JButton submitButton;
		
		//Submit button
		JFrame mainWindow = new JFrame();
		//Sets the window size
		mainWindow.setSize(500, 200);
		//Title
		mainWindow.setTitle("EECS 293 Programming Project 1");
		//Not resizable
		mainWindow.setResizable(false);
		//Will close when the red button is clicked.
		mainWindow.setDefaultCloseOperation(3);
		
		//Set the layout as a 2x3 grid
		mainWindow.setLayout(new GridLayout(2, 3));
		
		
		//Initialize components
		leftTextField = new JTextField();
		rightTextField = new JTextField();
		submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
		    {
				String left = cleanseString(leftTextField.getText());
				String right = cleanseString(rightTextField.getText());
		       InputReader reader = new InputReader();
		       result.setText("These two strings are:");
		       if(reader.readInput(left, right)){
		    	   result.append(" a match");
		       }
		       else{
		    	   result.append(" not a match");
		       }
		    }
			
			public String cleanseString(String input){
				return input.toLowerCase().trim();
			}
		});
		
		//Add the components to the view
		mainWindow.add(leftTextField);
		mainWindow.add(rightTextField);
		mainWindow.add(submitButton);
		mainWindow.add(result);
		
		
		//Makes window visible
		mainWindow.setVisible(true);
		
	}

}