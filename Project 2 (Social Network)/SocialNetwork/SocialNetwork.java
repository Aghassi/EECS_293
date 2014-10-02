package SocialNetwork;


import java.lang.reflect.Array;
import java.util.*;

/**
* A class that represents a social network
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class SocialNetwork{
    private HashMap<String, User> userMap;
    private HashMap<HashSet<User>, Link> links;
    private HashMap<User,  HashMap<String, Link>> usersToLinks;
    private HashMap<User, HashMap<Date, Integer>> trendMap;

	public SocialNetwork(){
        userMap = new HashMap<String, User>();
        links  = new HashMap<HashSet<User>, Link>();
        usersToLinks = new HashMap<User, HashMap<String, Link>>();
        trendMap = new HashMap<User, HashMap<Date, Integer>>();
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
        if (!usersToLinks.containsValue(user)){
            usersToLinks.put(user, new HashMap<String, Link>());
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
        return usersToLinks.containsKey(getUser(id));
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

           //Adds the link to each user is social network
           for(String id: ids){
               HashMap map = usersToLinks.get(getUser(id));
               if(!map.containsKey(ids)){
                   map.put(ids, linkToAdd);

               }
               updateTrendMap(id, date);
               usersToLinks.put(getUser(id), map);
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
            //Removes the link to each user is social network
            for(String id: ids){
                HashMap map = usersToLinks.get(getUser(id));
                if(map.containsKey(ids)){
                   Link linkToTeardown = (Link) map.get(ids);
                   linkToTeardown.tearDown(date, status);
                   map.remove(ids);
                   map.put(ids, linkToTeardown);
                }
                updateTrendMap(id, date);
                usersToLinks.put(getUser(id), map);
            }
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
        return neighborhood(id, date, Integer.MAX_VALUE, status);
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
        return findFriends(currentUser, maxDistance,  status);
    }

    public Map<Date, Integer> neighborhoodTrend(String id, Statuses.SocialNetworkStatus status){
        if(id.equals(null)){
            status = Statuses.SocialNetworkStatus.INVALID_USER;
            //Need to return or throw?
            return null;
        }
        else{
            return trendMap.get(getUser(id));
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

    //Takes hashset of id strings, finds the users, and returns a hashset of users from those strings
    private HashSet<User> convertIDtoUser(HashSet<String> ids){
        HashSet<User> usersToAdd = new HashSet<User>();
        for (String id : ids) {
            usersToAdd.add(getUser(id.toString()));
        }
        return usersToAdd;
    }

    //Called recursively to find all the users in the social network
    private HashMap<String, Friends> findFriends(User user,  Integer maxDistance, Statuses.SocialNetworkStatus status) throws Exception {
        //Breadth first traversal
        Queue friendsToBeAdded = new LinkedList<Friends>();
        HashMap<String, Friends> friendsToBeReturned = new HashMap<String, Friends>();
        friendsToBeAdded.addAll(usersToLinks.get(user).values());
        User lastUser = user;
        int distance = 0;



        while (!friendsToBeAdded.isEmpty()){
            Link node = (Link) friendsToBeAdded.remove();
            //Name of user
            ArrayList<User> currentFriend = new ArrayList<User>();
            currentFriend.addAll(node.getUsers());
            //Remove the last user from the array
            currentFriend.remove(lastUser);

            if(friendsToBeReturned.containsKey(currentFriend.get(0).getID()) || currentFriend.contains(user)){
                continue;
            }
            lastUser = currentFriend.get(0);

            Friends friend = new Friends();
            friend.set(currentFriend.get(0), distance);

            //Put the name of the user with the friend
            friendsToBeReturned.put(currentFriend.get(0).getID(), friend);
            //Add the friend's friends
            friendsToBeAdded.addAll(usersToLinks.get(currentFriend.get(0)).values());

            distance++;
            if(distance >= maxDistance){
                break;
            }

        }

        status = Statuses.SocialNetworkStatus.SUCCESS;
        return  friendsToBeReturned;
    }

    private void updateTrendMap (String id, Date date){
        HashMap updatedTrendMap = trendMap.get((getUser(id)));
        ArrayList trendsList = new ArrayList(updatedTrendMap.keySet());

        Integer trendsCount = 1;
        if (trendsList.size() > 0) {
           trendsCount = (Integer) updatedTrendMap.get(trendsList.get(trendsList.size() - 1));
        }

        updatedTrendMap.put(date, trendsCount);
        trendMap.put(getUser(id), updatedTrendMap);
    }

}