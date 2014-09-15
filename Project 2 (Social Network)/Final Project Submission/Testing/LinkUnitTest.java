package Testing;

import SocialNetwork.Link;
import SocialNetwork.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

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
    public void establishAndTearDown(){
        Link testLink = new Link();
        Date testDate = new Date();
        testDate.setMonth(5);
        testDate.setDate(20);
        System.out.print(testDate.toString());

        final User userOne = new User("David");
        final User userTwo = new User("John");
        ArrayList<User> userArrayList = new ArrayList<User>(){
            {
                add(userOne);
                add(userTwo);
            }
        };

        //Test
        testLink.setUsers(userArrayList);
        Assert.assertTrue(testLink.establish(testDate));
        Assert.assertTrue(testLink.tearDown(testDate));
        //Change the month to later than the prior
        testDate.setMonth(6);
        Assert.assertFalse(testLink.tearDown(testDate));
    }
}
