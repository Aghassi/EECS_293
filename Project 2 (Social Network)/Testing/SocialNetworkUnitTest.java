package Testing;

import SocialNetwork.SocialNetwork;
import SocialNetwork.User;
import SocialNetwork.Friends;
import SocialNetwork.Statuses;
import org.junit.Assert;
import org.junit.Test;


import java.util.Date;
import java.util.HashSet;

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
        final User userTwo = new User("Alison");
        User userThree = new User("Jo");

        userOne.setEmailAddress("dsa28@case.edu");
        userOne.setPhoneNumber("1234567890");

        Date testDate = new Date();
        testDate.setMonth(5);
        testDate.setDate(20);


        HashSet<String> userIds = new HashSet<String>(){
            {
                add(userOne.getID());
                add(userTwo.getID());
            }
        };

        testNetwork.addUser(userOne);
        testNetwork.addUser(userTwo);
        testNetwork.addUser(userThree);

        //Test
        Statuses.SocialNetworkStatus status = Statuses.SocialNetworkStatus.SUCCESS;

        testNetwork.establishLink(userIds, testDate, status);
        Assert.assertEquals(0, status.ordinal());

        testNetwork.tearDownLink(userIds, testDate, status);
        Assert.assertEquals(0, status.ordinal());
    }

    @Test
    public void findUsers() throws Exception {
        SocialNetwork testNetwork = new SocialNetwork();
        final User userOne = new User("David");
        final User userTwo = new User("Alison");
        User userThree = new User("Jo");

        userOne.setEmailAddress("dsa28@case.edu");
        userOne.setPhoneNumber("1234567890");

        Date testDate = new Date();
        testDate.setMonth(5);
        testDate.setDate(20);


        HashSet<String> userIds = new HashSet<String>(){
            {
                add(userOne.getID());
                add(userTwo.getID());
            }
        };

        testNetwork.addUser(userOne);
        testNetwork.addUser(userTwo);
        testNetwork.addUser(userThree);

        //Test
        Statuses.SocialNetworkStatus status = Statuses.SocialNetworkStatus.SUCCESS;

        testNetwork.establishLink(userIds, testDate, status);
        Assert.assertEquals(0, status.ordinal());

        HashSet<Friends> testFriends = testNetwork.neighborhood(userOne.getID(), testDate, status);
        System.out.print(testFriends.toString());
        Assert.assertEquals("Should be success", 0, status.ordinal());

        testNetwork.tearDownLink(userIds, testDate, status);
        Assert.assertEquals(0, status.ordinal());
    }
}
