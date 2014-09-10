package SocialNetwork;

/**
* A class that represents a user object
* David Aghassi (dsa28@case.edu)
**/

public class User{
	private String id;
	private boolean isValidUser;

	/**
	* Creates a new user
	* @param id - the name of the user being passed in
	**/
	public User(String id){
		id = id;
		isValidUser = false;
	}

	/**
	* Sets the id of the user
	* @param id - the id of the user to be set
	* @return True if the id is marked as valid, false if otherwise
	**/
	public boolean setID(String id){
        if(id == null){
            throw new UninitializedObjectException("Please pass in a valid user id");
        }
        else {
            if (!this.isValidUser) {
                this.isValidUser = true;
                return true;
            } else {
                return false;
            }
        }
	}

	/**
	* Returns the id of the user as long as it is valid
	* @return The id, null if it is valid
	**/
	public String getID(){
		if(!this.isValidUser){
			return null;
		}
		else{
			return this.id;
		}
	}

	/**
	* @return If the user is valid or not
	**/
	public boolean isInvalid(){
		return this.isValidUser;
	}

	/**
	* Prints a legible string for the user if of the current user
	* @return A string with the id of the current user, or "Invalid SocialNetwork.User" if the user is
	* uninitialized
	**/
	public String toString(){
		if(!this.isValidUser){
			return "Invalid SocialNetwork.User: Uninitialized ID";
		}
		else{
			return "SocialNetwork.User: " + this.id;
		}
	}
}