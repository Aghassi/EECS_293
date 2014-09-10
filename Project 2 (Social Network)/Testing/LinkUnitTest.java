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
    public void canCreateLink(){
        Link testLink = new Link();

        //Test
        Assert.assertNotNull(testLink);
    }

    @Test
    public void testLinkValidity(){
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
        Assert.assertFalse(testLink.isActive(testDate));
        //Set link to active
        testLink.setUsers(userArrayList);
        Assert.assertTrue(testLink.isActive(testDate));
    }

    @Test
    public void
}