package Testing;

import SocialNetwork.User;
import org.junit.Test;
import org.junit.Assert;

/**
 * A class to test the user class
 * Created by davidaghassi on 9/10/14.
 */
public class UserUnitTest {

    @Test
    public void userCreated(){
        User testUser = new User("David");

        //Test
        Assert.assertNotNull(testUser);
    }

    @Test
    public void userCreatedIsValid(){
        User testUser = new User("David");

        //Test
        testUser.setID(testUser.getID());
        Assert.assertEquals("checking this is a valid user", true, testUser.isValid());
    }

    @Test
    public void userGetID(){
        User testUser = new User("David");

        //Set to valid
        testUser.setID(testUser.getID());
        Assert.assertNotNull(testUser.getID());
    }

    @Test
    public void testToString(){
        User testUser = new User("David");

        //Set to valid
        Assert.assertEquals("SocialNetwork.User: " + testUser.getID(), testUser.toString());
    }

}
