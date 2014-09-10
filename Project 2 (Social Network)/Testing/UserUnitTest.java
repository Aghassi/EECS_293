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
    public void userCreatedIsNotValid(){
        User testUser = new User("David");

        //Test
        Assert.assertFalse(testUser.isInvalid());
    }

    @Test
    public void userCreatedIsValid(){
        User testUser = new User("David");

        //Test
        testUser.setID(testUser.getID());
        Assert.assertTrue(testUser.isInvalid());
    }

    @Test
    public void userGetID(){
        User testUser = new User("David");

        //Test
        Assert.assertNull(testUser.getID());
        //Set to valid
        testUser.setID(testUser.getID());
        Assert.assertNotNull(testUser.getID());
    }

    @Test
    public void testToString(){
        User testUser = new User("David");

        //Test
        Assert.assertSame("Invalid SocialNetwork.User: Uninitialized ID", testUser.toString());
        //Set to valid
        testUser.setID(testUser.getID());
        Assert.assertSame("SocialNetwork.User: " + testUser.getID(), testUser.toString());
    }

}
