package SocialNetwork;

import java.util.HashMap;

/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class SocialNetwork{
    private HashMap<String, User> userMap;

	public SocialNetwork(){
        userMap = new HashMap<String, User>();

	}

    /**
     * Adds the user to the social network
     * @param user The user being added
     * @return True if added, false otherwise
     */
	public boolean addUser(User user){
        if (!userMap.containsValue(user)){
            userMap.put(user.getID(), user);
            return true;
        }
        else{
            return false;
        }
	}

    /**
     * States whether the social network has the user or not
     * @param id The id of the user to be looked up
     * @return True if the user is in the social network, false otherwise
     */
	public 	boolean isMember(String id){
        return userMap.containsKey(id);
	}

    /**
     * Gets the user from the social network
     * @param id The id of the user to be looked up
     * @return The user, or null if the user isn't in the network
     */
	public User getUser(String id){
        if(!userMap.containsKey(id)){
            return null
        }
        else{
            return userMap.get(id);
        }
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