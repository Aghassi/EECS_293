package Testing;

import SocialNetwork.SocialNetwork;
import SocialNetwork.User;
import SocialNetwork.Statuses;
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
    public void establishAndTeardownLink() throws Exception {
        SocialNetwork testNetwork = new SocialNetwork();
        final User userOne = new User("David");
        final User userTwo = new User("Sarah");
        User userThree = new User("Jo");

        userOne.setEmailAddress("dsa28@case.edu");
        userOne.setPhoneNumber("1234567890");

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
        testNetwork.establishLink(userIds, testDate, Statuses.SocialNetworkStatus.SUCCESS);
        testNetwork.tearDownLink(userIds, testDate, Statuses.SocialNetworkStatus.SUCCESS);
    }

}
