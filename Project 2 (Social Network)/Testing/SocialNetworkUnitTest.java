package Testing;

import SocialNetwork.SocialNetwork;
import SocialNetwork.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * A class to test the social network class
 * Created by David on 9/10/2014.
 */
public class SocialNetworkUnitTest {

    @Test
    public void createSocialNetwork(){
        SocialNetwork testNetwork = new SocialNetwork();

        //Test
        Assert.assertNotNull(testNetwork);
    }

    @Test
    public void addUserTestAndIsMember(){
        SocialNetwork testNetwork = new SocialNetwork();
        User testUser = new User("David");

        testNetwork.addUser(testUser);

        //Test
        Assert.assertEquals(testUser, testNetwork.getUser(testUser.getID()));

        //Test is member
        Assert.assertTrue(testNetwork.isMember(testUser.getID()));
    }

    @Test
    public void establishAndTeardownLink(){
        SocialNetwork testNetwork = new SocialNetwork();
        final User userOne = new User("David");
        final User userTwo = new User("Sarah");
        User userThree = new User("Jo");

        Date testDate = new Date();
        testDate.setMonth(5);
        testDate.setDate(20);


        ArrayList<String> userIds = new ArrayList<String>(){
            {
                add(userOne.getID());
                add(userTwo.getID());
            }
        };

        testNetwork.addUser(userOne);
        testNetwork.addUser(userTwo);
        testNetwork.addUser(userThree);

        //Test
        Assert.assertTrue(testNetwork.establishLink((Set<String>) userIds, testDate));
        Assert.assertTrue(testNetwork.tearDownLink((Set<String>) userIds, testDate));
    }

}
