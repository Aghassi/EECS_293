package Testing;

import SocialNetwork.Link;
import SocialNetwork.User;
import SocialNetwork.Statuses;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

/**
 * Unit test for testing the Link class
 * Created by davidaghassi on 9/10/14.
 */
public class LinkUnitTest {

    @Test
    public void createLink(){
        Link testLink = new Link();

        //Test
        Assert.assertNotNull(testLink);
    }


    @Test
    public void establishAndTearDown() throws Exception {
        Link testLink = new Link();
        Date testDate = new Date();
        testDate.setMonth(5);
        testDate.setDate(20);
        System.out.print(testDate.toString());

        final User userOne = new User("David");
        final User userTwo = new User("John");
        HashSet<User> userArrayList = new HashSet<User>(){
            {
                add(userOne);
                add(userTwo);
            }
        };

        //Test
        testLink.setUsers(userArrayList, Statuses.SocialNetworkStatus.SUCCESS);
        testLink.establish(testDate, Statuses.SocialNetworkStatus.SUCCESS);
        testLink.tearDown(testDate, Statuses.SocialNetworkStatus.SUCCESS);
        //Change the month to later than the prior
        testDate.setMonth(6);
        testLink.tearDown(testDate, Statuses.SocialNetworkStatus.SUCCESS);
    }
}
