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
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import Controllers.InputReader;

public class WordWindow extends JFrame {
	static JFormattedTextField leftTextField;
	static JFormattedTextField rightTextField;
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
		
		//This formatter allows for up to a 10 character word.
		//No spaces allowed.
		MaskFormatter leftMask = null;
		try {
			leftMask = new MaskFormatter(generateFormat('L', 10));
			leftMask.setInvalidCharacters("1234567890 ");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaskFormatter rightMask = null;
		try {
			rightMask = new MaskFormatter(generateFormat('L', 10));
			rightMask.setInvalidCharacters("1234567890 ");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Initialize components
		leftTextField = new JFormattedTextField(leftMask);
		rightTextField = new JFormattedTextField(rightMask);
		submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
		    {
		       InputReader reader = new InputReader();
		       result.setText("These two strings are:");
		       if(reader.readInput(leftTextField.getText(), rightTextField.getText())){
		    	   result.append(" a match");
		       }
		       else{
		    	   result.append(" not a match");
		       }
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

	/**
	 * Allows for a string with specified character amount to be created
	 * @param with character to be put into the string
	 * @param count number of characters to be put in
	 * @return The formatted string with the length provided filled with the characters passed in.
	 */
	public static String generateFormat(char with, int count) {
	    return String.format("%" + count + "s", "").replaceAll(" ", Character.toString(with));
	}

}