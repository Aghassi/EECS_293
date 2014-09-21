package SocialNetwork;

/**
 * Checks for common errors
 * Created by davidaghassi on 9/20/14.
 */
public class ErrorChecker {

    public static void checkNotNull(Object... objectToCheck) throws Exception{
        for (Object check : objectToCheck){
            if(check.equals(null)){
                throw new NullPointerException();
            }
        }
    }
}
