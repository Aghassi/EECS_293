package SocialNetwork;

/**
 * A class to represent the friends of a user and how far they are from each other
 * Created by davidaghassi on 9/20/14.
 */
public class Friends {
    private boolean valid;
    private User user;
    private int distance;

    public Friends(){
        valid = false;
        user = null;
        distance = 0;
    }

    /**
     * Sets the friend link. Will do nothing if friend is already valid.
     * @param userToSet user to be set
     * @param distanceFromUser distance from current user
     * @return true if successful, false if otherwise
     */
    public boolean set(User userToSet, int distanceFromUser){
        ErrorChecker.checkNotNull(userToSet, distanceFromUser);
        if(!valid()){
            user = userToSet;
            distance = distanceFromUser;
            return true;
        }
        return false;
    }

    /**
     * Returns the user linked as a friend if the friend is valid
     * @return The user
     * @throws Exception new UninitializedObjectException if not valid
     */
    public User getUser() throws  Exception{
        if(valid()){
            return user;
        }
        else{
            throw new UninitializedObjectException();
        }
    }

    /**
     * The distance the current user friend is from the current friend if valid
     * @return The distance
     * @throws Exception new UninitializedObjectException if not valid
     */
    public int getDistance() throws Exception{
        if(valid()){
            return distance;
        }
        else{
            throw new UninitializedObjectException();
        }
    }

    /**
     * Returns a human legible string of the friend
     * @return User: Distance, or Invalid Friend if not valid
     */
    public String toString(){
        if(!valid()){
            return "Invalid Friend";
        }
        else{
            return user.toString() + ": " + String.valueOf(distance);
        }
    }

    private boolean valid(){
        return valid;
    }

}
