package SocialNetwork;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import SocialNetwork.User;

/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class Link{
	private boolean isValidLink;
	private ArrayList<User> linkedUsers;
    private ArrayList<Date> dates;

	/**
	* Creates and invalid link
	**/
	public Link(){
		isValidLink = false;
		linkedUsers = new ArrayList<User>();
        dates = new ArrayList<Date>();
	}

    /**
     * Sets the users linked
     * @param users The set of users being passed in
     * @return True if they are linked, false if the link isn't valid or
     * one of the users is not valid.
     */
	public boolean setUsers(ArrayList<User> users){
		if(!isUserValid(users) || linkedUsers.size()!=0){
            return false;
		}
        else{
            linkedUsers = users;
            isValidLink = true;
            return true;
        }
	}

	/**
	* Returns the set of users in this link
	* Will throw an exception if the link is invalid
	**/
	public ArrayList<User> getUsers() throws  UninitializedObjectException{
		if(!this.isValidLink){
			throw new UninitializedObjectException();
		}
		else {
            return this.linkedUsers;
        }
    }

    /**
     * Adds the date to the list of dates. If it already exists, or the last date is greater
     * than the date being passed in do nothing and return false;
     * @param date The date being requested
     * @return True if successful, false otherwise.
     */
	public boolean establish(Date date) {
        if (!isInvalidLink()) {
            if (dates.size() > 0 && dates.get(dates.size()).after(date)) {
                return false;
            } else {
                dates.add(date);
                return true;
            }

        } else {
            return false;
        }
    }

    /**
     * Tearsdown the link created if the link is valid and the last date isn't greater
     * than the date being passed in.
     * @param date The date being requested
     * @return True if the date has been added "torndown", false otherwise
     */
	public boolean tearDown(Date date){
      return establish(date);
    }

    /**
     * Returns if the link is active at the given date. Does nothing if the link is invalid
     * @param date The date being requested
     * @return True if the date is acive, false otherwise
     */
	public boolean isActive(Date date){
        if(isInvalidLink()){
            return false;
        }
        else{
            if(dates.contains(date)){
                if(dates.lastIndexOf(date)%2 == 0) {
                    //Even means it is active.
                    return true;
                }
                else{
                    //Inactive
                    return false;
                }
            }
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
        if (!isInvalidLink()) {
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
        if(!isInvalidLink()) {
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
        if(isInvalidLink()){
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
    private boolean isInvalidLink() throws UninitializedObjectException{
        if (!this.isValidLink){
            throw new UninitializedObjectException("Invalid link!");
        }
        return true;
    }


    private boolean isUserValid(ArrayList<User> users){
    	//Checks if the users are valid.
        for (int i = 0; i < users.size() ; i++) {
            if (users.get(i).getID() != null){
                continue;
            }
            else
                return false;
        }
        return true;

    }
}
