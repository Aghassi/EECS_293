import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;

import java.util.ArrayList;

/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class Link{
	private boolean isValidLink;
	private Set<User> linkedUsers;
    private ArrayList<E> dates;

	/**
	* Creates and invalid link
	**/
	public Link(){
		this.isValidLink = false;
		linkedUsers = new Set<User>();
        dates = new ArrayList<E>();
	}

	public boolean setUsers(Set<User> users){
		//Set the link for the two users, and mark the link as valid
		//Do nothing if the link is already valid, or if the user argument
		//is anything but two distinct users, do nothing and return false
		//Else return true
	}

	/**
	* Returns the set of users in this link
	* Will throw an exception if the link is invalid
	**/
	public Set<User> getUsers(){
		if(!this.isValidLink){
			//If invalid, throw an UninitializedObjectException 
		}
		else{
			return this.linkedUsers;
		}
	}

	public boolean establish(Date date){
		//Establish the link at the given date
		//If invalid, throw an UninitializedObjectException
		//If link already exists, or precedes the date already there, do nothing
		//and return false
		//Otherwise return true
	}

	public boolean tearDown(Date date){
		//Teardown the link at the given date
		//If invalid, throw an UninitializedObjectException
		/*If the link is already inactive, or the given date precedes
		the date already there, do nothing and return false*/
	}

	public boolean isActive(Date date){
		//returns whether the link is active at the given date
		/*If there are multiple links to one day, the last link there will decide if it is active or not
		*/
		//If link invalid, return false
	}

    /**
     * A method to get the first date of the link
     * If the object is invalid, it will throw an exception
     * @return Null if there is no object at the first date in the array
     * The date otherwise.
     */
	public Date firstDate(){
        if (!isInvalidLink()) {
            if (dates.get(0) == null) {
                return null;
            } else {
                return dates.get(0);
            }
        }
	}

    /**
     * Returns the next date after the one being requested
     * @param date The date being requested
     * @return The next date, or null if the date set doesn't contain the passed in date
     */
	public Date nextDate(Date date){
        if(!isInvalidLink()) {
            if (dates.contains(date)) {
                dates.get(dates.indexOf(date) + 1);
            } else {
                return null;
            }
        }
	}

	public String toString(){
		//returns "link: valid link"
		//or "Invalid link: uninitialized id" if invalid.
	}

    /**
     * Checks if the link is invalid
     * @return False if it isn't invalid, true if it is
     */
    private boolean isInvalidLink(){
        if (!this.isValidLink){
            //If invalid, throw an UninitializedObjectException
        }
    }
}
