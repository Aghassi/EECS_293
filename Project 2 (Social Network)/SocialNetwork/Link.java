package SocialNetwork;


import java.util.*;

/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class Link{
	private boolean isValidLink;
	private Set<User> linkedUsers;
    private ArrayList<Date> dates;

	/**
	* Creates and invalid link
	**/
	public Link(){
		isValidLink = false;
		linkedUsers = new HashSet<User>();
        dates = new ArrayList<Date>();
	}

    /**
     * Sets the users linked
     * @param users The set of users being passed in
     * @return True if they are linked, false if the link isn't valid or
     * one of the users is not valid.
     */
	public void setUsers(HashSet<User> users, Statuses.SocialNetworkStatus status){
		if(!isUserValid(users)){
            status = Statuses.SocialNetworkStatus.INVALID_USER;
        }
        if(linkedUsers.size()!=0){
            status = Statuses.SocialNetworkStatus.ALREADY_ACTIVE;
		}
        else{
            linkedUsers = users;
            isValidLink = true;
        }
	}

	/**
	* Returns the set of users in this link
	* Will throw an exception if the link is invalid
	**/
	public Set<User> getUsers() throws  UninitializedObjectException{
		if(!this.isValidLink){
			throw new UninitializedObjectException();
		}
		else {
            return linkedUsers;
        }
    }

    /**
     * Adds the date to the list of dates. If it already exists, or the last date is greater
     * than the date being passed in do nothing and return false;
     * @param date The date being requested
     * @return True if successful, false otherwise.
     */
	public void establish(Date date, Statuses.SocialNetworkStatus status) throws Exception {
        ErrorChecker.checkNotNull(date, status);
        if(isActive(date)){
            status = Statuses.SocialNetworkStatus.ALREADY_ACTIVE;
        }

        addDateToLink(date, status);
    }

    /**
     * Tearsdown the link created if the link is valid and the last date isn't greater
     * than the date being passed in.
     * @param date The date being requested
     * @return True if the date has been added "torndown", false otherwise
     */
	public void tearDown(Date date, Statuses.SocialNetworkStatus status) throws Exception {
        ErrorChecker.checkNotNull(date, status);
        if(!isActive(date)){
            status = Statuses.SocialNetworkStatus.ALREADY_INACTIVE;
        }

        addDateToLink(date, status);

    }

    /**
     * Returns if the link is active at the given date. Does nothing if the link is invalid
     * @param date The date being requested
     * @return True if the date is acive, false otherwise
     */
	public boolean isActive(Date date){
        if(isValidLink()){
            return false;
        }

        if(dates.size() != 0) {
            int index = binarySearchForDate(date, 0, dates.size());
            return (index%2 == 0);
        }
        return false;



    }

    /**
     * A method to get the first date of the link
     * If the object is invalid, it will throw an exception
     * @return Null if there is no object at the first date in the array
     * The date otherwise.
     */
	public Date firstDate(){
        if (!isValidLink()) {
            if (dates.get(0) == null) {
                return null;
            } else {
                return dates.get(0);
            }
        }
        return null;
	}

    /**
     * Returns the next date after the one being requested
     * @param date The date being requested
     * @return The next date, or null if the date set doesn't contain the passed in date
     */
	public Date nextDate(Date date){
        if(!isValidLink()) {
            if (dates.contains(date)) {
                dates.get(dates.indexOf(date) + 1);
            }
            else {
                return null;
            }
        }
        return null;
	}

    /**
     * Returns a legible version if the link is valid.
     * @return A string if the link is valid or not
     */
	public String toString(){
        if(isValidLink()){
            return "Invalid link: uninitialized id";
        }
        else{
            return "Link: valid link";
        }
	}

    /**
     * Checks if the link is invalid
     * @return False if it isn't invalid, true if it is
     */
    private boolean isValidLink() throws UninitializedObjectException{
        if (!this.isValidLink){
            throw new UninitializedObjectException("Invalid link!");
        }
        return false;
    }


    private boolean isUserValid(HashSet<User> users){
    	//Checks if the users are valid.
        for (User user : users) {
            if( user != null){
                continue;
            }
            else
                return false;
        }
        return true;

    }

    //Adds the given date to links
    private void addDateToLink(Date date, Statuses.SocialNetworkStatus status){
        if (dates.size() > 0 && dates.get(dates.size()-1).after(date)) {
            status = Statuses.SocialNetworkStatus.INVALID_DATE;
        }
        else {
            dates.add(date);
            status = Statuses.SocialNetworkStatus.SUCCESS;
        }
    }

    //Finds the date within a range of dates to see if the date is active
    private int binarySearchForDate(Date date, int left, int right){
        if(left>right){
            return -1;
        }
        int middle = (left+right)/2;

        if (dates.get(middle) == date || (dates.get(middle - 1).before(date) && dates.get(middle + 1).after(date))) {
            return middle;
        }
        else if (dates.get(middle).after(date)) {
            return binarySearchForDate(date, left, middle - 1);
        }
        else {
            return binarySearchForDate(date, middle + 1, right);
        }
    }


}
