/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class Link{

	public Link(){
		//creates an invalid link, throws no exceptions
	}

	public boolean setUsers(Set<User> users){
		//Set the link for the two users, and mark the link as valid
		//Do nothing if the link is already valid, or if the user argument
		//is anything but two distinct users, do nothing and return false
		//Else return true
	}

	public Set<User> getUsers(){
		//returns the two connected users
		//If invalid, throw an UninitializedObjectException
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

	public Date firstDate(){
		//returns the date of the first event recorded in this link
		//returns null if the event has not been established yet
		//If invalid, throw an UninitializedObjectException
	}

	public Date nextDate(Date date){
		//returns the next event after the passed in date.
		//null is returned if no such event exists
		//If invalid, throw an UninitializedObjectException
	}

	public String toString(){
		//returns "link: valid link"
		//or "Invalid link: uninitialized id" if invalid.
	}
}
