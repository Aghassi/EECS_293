package SocialNetwork;

import java.security.InvalidParameterException;

/**
* A class that represents a user object
* David Aghassi (dsa28@case.edu)
**/

public class User{
	private String id;
    private String firstName, lastName, middleName;
    private String email;
    private String phoneNumber;
	private boolean isValidUser;

	/**
	* Creates a new user
	* @param id - the name of the user being passed in
	**/
	public User(String id){
        if(id.equals(null)){
            this.id = id;
            isValidUser = false;
        }
		this.id = id;
		isValidUser = true;
        firstName = null;
        lastName = null;
        middleName = null;
        email = null;
        phoneNumber = null;
	}


    /**
     * Sets the first name of the user. Throws exceptions if the user is invalid
     * or if the input is null
     * @param name The name to be set to the first name
     * @return The user
     * @throws Exception
     */
    public User setFirstName(String name) throws Exception{
        checkForExceptions(name);
        firstName = name;
        return this;
    }

    /**
     * Gets the first name of the user
     * @return The user's first name
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Sets the last name of the user. Throws exceptions if the user is invalid
     * or if the input is null
     * @param name The name to be set to the last name
     * @return The user
     * @throws Exception
     */
    public User setLastName(String name) throws Exception{
        checkForExceptions(name);
        lastName = name;
        return this;
    }

    /**
     * Gets the last name of the user
     * @return The user's last name
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Sets the middle name of the user. Throws exceptions if the user is invalid
     * or if the input is null
     * @param name The name to be set to the middle name
     * @return The user
     * @throws Exception
     */
    public User setMiddleName(String name) throws Exception{
        checkForExceptions(name);
        middleName = name;
        return this;
    }

    /**
     * Gets the middle name of the user
     * @return The user's middle name
     */
    public String getMiddleName(){
        return middleName;
    }

    /**
     * Sets the email for the current user. Will throw exceptions if the email doesn't contain @,
     * if the input is null, or if the user is not valid.
     * @param emailAddress The email being passed in
     * @return The current user
     * @throws Exception
     */
    public User setEmailAddress(String emailAddress) throws Exception{
        checkForExceptions(emailAddress);
        if(!emailAddress.contains("@")){
            throw new InvalidParameterException("Email did not containt @ symbold!");
        }
        email = emailAddress;
        return this;
    }

    /**
     * Gets the current email of the user
     * @return The current email of the user.
     */
    public String getEmailAddress(){
        return email;
    }

    public User setPhoneNumber(String number){
        //How many numbers is a phone number, are we accounting for world wide?
        checkForExceptions(number);
        phoneNumber = number;
        return this;
    }

    /**
     * Gets the phone number of the user
     * @return The user's phone number
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

	/**
	* Sets the id of the user
	* @param id - the id of the user to be set
	* @return True if the id is marked as valid, false if otherwise
	**/
	public boolean setID(String id){
        if(id == null){
            throw new UninitializedObjectException("Please create a valid user id");
        }
        if (!this.isValidUser) {
            this.isValidUser = true;
            return true;
        }

        return false;
	}

	/**
	* Returns the id of the user as long as it is valid
	* @return The id, null if it is valid
	**/
	public String getID(){
		if(!isValid()){
			return null;
		}

        return this.id;
	}

	/**
	* @return If the user is valid or not
	**/
	public boolean isValid(){
		return this.isValidUser;
	}

	/**
	* Prints a legible string for the user if of the current user
	* @return A string with the id of the current user, or "Invalid SocialNetwork.User" if the user is
	* uninitialized
	**/
	public String toString(){
		if(!isValid()){
			return "Invalid SocialNetwork.User: Uninitialized ID";
		}
		else{
			return "SocialNetwork.User: " + this.id;
		}
	}

    private void checkForExceptions(String string){
        if (string.equals(null)){
            throw new NullPointerException();
        }
        if (!isValid()){
            throw new UninitializedObjectException();
        }
    }
}