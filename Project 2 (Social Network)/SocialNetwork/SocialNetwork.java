package SocialNetwork;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class SocialNetwork{
    private HashMap<String, User> userMap;
    private HashMap<Set<String>, Link> links;

	public SocialNetwork(){
        userMap = new HashMap<String, User>();
        links  = new HashMap<Set<String>, Link>();
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
            return null;
        }
        return userMap.get(id);
	}

    /**
     * Establishes the link between two users
     * If the users being passed in aren't valid, or the date is not up to date
     * nothing is done
     * @param ids The pair of users being passed in
     * @param date The date being passed in
     * @return True if the link is established, false otherwise.
     */
	public boolean establishLink(Set<String> ids, Date date){
       if(checkValidity(ids, date)){
           Link linkToAdd = new Link();
           Object[] userIDs = ids.toArray();
           ArrayList<User> usersToAdd = new ArrayList<User>();
           for (int i = 0; i < ids.toArray().length; i++) {
               usersToAdd.add(getUser(userIDs[i].toString()));
           }
           linkToAdd.setUsers(usersToAdd);
           linkToAdd.establish(date);
           links.put(ids, linkToAdd);
           return true;
       }
       return false;
	}

    /**
     * Tears down the link between two userss at the given date and returns true
     * Otherwise, returns false if users aren't valid, or if date is not recent
     * @param id User pair being passed in
     * @param date Date looking to tear down
     * @return True on success, false otherwise.
     */
	public boolean tearDownLink(Set<String> id, Date date){
        if(checkValidity(id, date)){
            links.get(id).tearDown(date);
            return true;
        }
        return false;
	}

    /**
     * Checks to see if the date is less than the date in the link
     * @param ids ids being checked
     * @param date date being compared
     * @return True if it is not less then, false otherwise.
     */
    public boolean checkValidity(Set<String> ids, Date date){
        boolean dateValid = false;
        boolean usersValid = false;
        boolean linksActive = false;

        if(!links.get(ids).nextDate(date).equals(null)){
            dateValid = true;
        }
        Object[] userIDs = ids.toArray();
        for (int i = 0; i < userIDs.length; i++) {
            usersValid = (userMap.containsKey(userIDs[i]) || !userIDs[i].equals(null));
        }

        linksActive = links.get(ids).isActive(date);

        return (dateValid && linksActive && usersValid);
    }
}