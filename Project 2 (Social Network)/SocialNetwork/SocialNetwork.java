package SocialNetwork;

/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class SocialNetwork{
	public SocialNetwork(){

	}

	public boolean addUser(User user){
		//Returns true if the user added
		//Returns false if the collection changes
	}

	public 	boolean isMember(String id){
		//True if the social network contains the user
		//False otherwise
	}

	public User getUser(String id){
		//returns the user called for
		//or null if the user isn't in the network
	}

	public boolean establishLink(Set<String> ids, Date date){
		//Establish a link between two users at the given date
		/*return false if one or both of the users are null
		or not distinct users. If the date precedes the given date,
		do the same*/
	}

	public boolean tearDownLink(Set<String> id, Date date){
		//Tear down the link at the given date, return true
		/*return false if one or both of the users are null
		or not distinct users. If the date precedes the given date,
		do the same*/
	}

	public boolean isActive(Set<String> ids, Date date){
		//returns whether a link between the users with the given ids exists and is active
		
	}
}