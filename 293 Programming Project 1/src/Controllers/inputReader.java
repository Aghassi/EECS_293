package Controllers;

import java.util.HashMap;
import java.util.Map;

/**
 * A class for comparing the strings being passed in
 * @author davidaghassi
 *
 */


public class InputReader {
	//Used to compare the strings to each other
	private HashMap<Character, Integer> base = new HashMap<Character, Integer>();
	private HashMap<Character, Integer> compareToBase = new HashMap<Character, Integer>();
	
	
	/**
	 * Called to compare the two text boxes
	 * @param inputBoxLeft
	 * @param inputBoxRight
	 * @return True if the two values match
	 */
	public boolean readInput(String inputBoxLeft, String inputBoxRight){
		//Put the data in tables and clean it
		readInData(inputBoxLeft, inputBoxRight);
		
		//Start by assuming the data is the same
		boolean isTheSame = true;
		
		//If they aren't the same length, they can't be the same at all
		if(base.size() != compareToBase.size()){
			isTheSame = false;
			return isTheSame;
		}
		else{
			/**
			 * For each entry in the hash map, see if the other map contains that entry
			 * If it does, we then check to see if the key values are the same
			 * 
			 */
			for(Map.Entry<Character, Integer> entry : base.entrySet()){
				if(!compareToBase.containsKey(entry.getKey())){
					isTheSame = false;
					break;
				}
			}
		}
		return isTheSame;
	}
	
	/**
	 * Wrapper method that puts the data in hashmaps and cleans the strings
	 * @param baseString The data coming from the left input text box
	 * @param compareToBaseString The data coming from the right input text box
	 */
	private void readInData(String baseString, String compareToBaseString){
		
		//Abstracted the population method for future maintence
		populateHashMaps(baseString, base);
		populateHashMaps(compareToBaseString, compareToBase);
		
	}
	
	/**
	 * Creates the hash map where the data will be stored
	 * The hashmap will add new data, and increment the value count of old data.
	 * @param string The string to be broken into characters and put in the hashmap
	 * @param table The hashmap to store it to
	 */
	private void populateHashMaps(String string, HashMap<Character, Integer> table){
		for(int i = 0; i < string.length(); i++){
			//If the character already exists
			if(table.containsKey(string.charAt(i))){
				int count = (Integer) table.get(string.charAt(i)) + 1;
				table.put(string.charAt(i), count);
			}
			else{
				table.put(string.charAt(i), 1);
			}
		}
	}

}
