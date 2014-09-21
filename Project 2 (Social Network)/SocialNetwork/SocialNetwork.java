package SocialNetwork;

import java.util.*;

/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class SocialNetwork{
    private HashMap<String, User> userMap;
    private HashMap<HashSet<String>, Link> links;

	public SocialNetwork(){
        userMap = new HashMap<String, User>();
        links  = new HashMap<HashSet<String>, Link>();
	}

    /**
     * Adds the user to the social network
     * @param user The user being added
     * @return True if added, false otherwise
     */
	public boolean addUser(User user){
        if(!user.isValid()){
            return false;
        }
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
	public void establishLink(HashSet<String> ids, Date date, Statuses.SocialNetworkStatus status) throws Exception {
       checkSizeOfUsers(ids);
       if(checkValidity(ids, date)){
           Link linkToAdd = new Link();
           HashSet<User> usersToAdd = new HashSet<User>();
           for (String id : ids) {
               usersToAdd.add(getUser(id.toString()));
           }
           linkToAdd.setUsers(usersToAdd, status);
           linkToAdd.establish(date, status);
           links.put(ids, linkToAdd);
       }
	}

    /**
     * Tears down the link between two userss at the given date and returns true
     * Otherwise, returns false if users aren't valid, or if date is not recent
     * @param ids User pair being passed in
     * @param date Date looking to tear down
     * @return True on success, false otherwise.
     */
	public void tearDownLink(HashSet<String> ids, Date date, Statuses.SocialNetworkStatus status) throws Exception {
        checkSizeOfUsers(ids);
        if(checkValidity(ids, date)){
            links.get(ids).tearDown(date, status);
        }
	}

    /**
     * Checks to see if the date is less than the date in the link
     * @param ids ids being checked
     * @param date date being compared
     * @return True if it is not less then, false otherwise.
     */
    public boolean checkValidity(HashSet<String> ids, Date date){
        for (String id : ids) {
            return (userMap.containsKey(id) && !id.equals(null));
        }

        if(links.size() == 0) {
           return true;
        }
        else{
            return links.get(ids).isActive(date);
        }
    }

    private void checkSizeOfUsers(HashSet<String> ids) throws Exception{
        if (ids.size() > 2){
            throw new UninitializedObjectException("Too many users!");
        }
    }
}