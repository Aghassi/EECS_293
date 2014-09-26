package SocialNetwork;


import java.util.*;

/**
* A class that represents a social network
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class SocialNetwork{
    private HashMap<String, User> userMap;
    private HashMap<HashSet<User>, Link> links;

	public SocialNetwork(){
        userMap = new HashMap<String, User>();
        links  = new HashMap<HashSet<User>, Link>();
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
           HashSet<User> usersToAdd = convertIDtoUser(ids);
           linkToAdd.setUsers(usersToAdd, status);
           linkToAdd.establish(date, status);
           links.put(usersToAdd, linkToAdd);

           User[] arrayOfUsers = new User[usersToAdd.size()];
           usersToAdd.toArray(arrayOfUsers);
           for (User user: arrayOfUsers){
               usersToAdd.remove(user);
               arrayOfUsers = new User[usersToAdd.size()];
               usersToAdd.toArray(arrayOfUsers);
               user.addFriend(arrayOfUsers);
               usersToAdd.add(user);
               usersToAdd.toArray(arrayOfUsers);
           }

       }
	}

    /**
     * Tears down the link between two users at the given date and returns true
     * Otherwise, returns false if users aren't valid, or if date is not recent
     * @param ids User pair being passed in
     * @param date Date looking to tear down
     * @return True on success, false otherwise.
     */
	public void tearDownLink(HashSet<String> ids, Date date, Statuses.SocialNetworkStatus status) throws Exception {
        checkSizeOfUsers(ids);
        if(checkValidity(ids, date)){
            HashSet<User> usersToLookUp = convertIDtoUser(ids);
            System.out.print(links.containsKey(usersToLookUp));
            links.get(usersToLookUp).tearDown(date, status);
        }
	}

    /**
     * Called to find all the users friends in the social network
     * @param id The user being requested
     * @param date The date being passed in
     * @param status status to be set
     * @return All the friends of the user
     * @throws Exception
     */
    public HashMap<String, Friends> neighborhood(String id, Date date, Statuses.SocialNetworkStatus status) throws Exception {
        if(id.equals(null) || !isMember(id)){
            status = Statuses.SocialNetworkStatus.INVALID_USER;
            ErrorChecker.checkNotNull(id);
        }
        ErrorChecker.checkNotNull(date, status);
        User currentUser = getUser(id);
        HashMap<String, Friends> friends = new HashMap<String, Friends>();
        return findFriends(currentUser, currentUser, 0, null, friends, status);
    }

    /**
     * Returns all the user's friends that are withing a certain distance of the user
     * @param id The user being requested
     * @param date The date being passed in
     * @param maxDistance The max distance the friend can be from the user
     * @param status the status to be set
     * @return
     * @throws Exception
     */
    public HashMap<String,Friends> neighborhood(String id, Date date, int maxDistance, Statuses.SocialNetworkStatus status) throws Exception {
        if(maxDistance > 0){
            status = Statuses.SocialNetworkStatus.INVALID_DISTANCE;
            return null;
        }
        if(id.equals(null) || !isMember(id)){
            status = Statuses.SocialNetworkStatus.INVALID_USER;
            ErrorChecker.checkNotNull(id);
        }
        ErrorChecker.checkNotNull(date, status);
        User currentUser = getUser(id);
        HashMap<String, Friends> friends = new HashMap<String, Friends>();
        return findFriends(currentUser, currentUser, 0, maxDistance, friends, status);
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

    //Takes hashset of id strings, finds the users, and returns a hashset of users from those strings
    private HashSet<User> convertIDtoUser(HashSet<String> ids){
        HashSet<User> usersToAdd = new HashSet<User>();
        for (String id : ids) {
            usersToAdd.add(getUser(id.toString()));
        }
        return usersToAdd;
    }

    //Called recursively to find all the users in the social network
    private HashMap<String, Friends> findFriends(User user, User originalUser, int distance, Integer maxDistance, HashMap<String, Friends> friendsset, Statuses.SocialNetworkStatus status) throws Exception {
        if(user.getFriendsSize() == 0){
            return friendsset;
        }
        for (User friend: user.getFriends()){
            Friends newFriend = new Friends();
            newFriend.set(friend, distance);
            if(friendsset.containsKey(friend.getID()) || friend.equals(originalUser) ){
                continue;
            }
            else{
                friendsset.put(friend.getID(), newFriend);
            }
            if(maxDistance == null){
                friendsset = findFriends(friend, originalUser,  distance++, null, friendsset, status);
            }
            else if(maxDistance >= 0 && distance != maxDistance){
                friendsset = findFriends(friend, originalUser, distance++, maxDistance, friendsset, status);
            }

        }
        status = Statuses.SocialNetworkStatus.SUCCESS;
        return  friendsset;
    }

}