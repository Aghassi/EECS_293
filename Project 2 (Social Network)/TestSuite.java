import Testing.LinkUnitTest;
import Testing.SocialNetworkUnitTest;
import Testing.UserUnitTest;

/**
 * A class to run through the Unit Tests
 *
 * Created by David on 9/10/2014.
 */
public class TestSuite {
    public static void main(String [] args) throws Exception {
        UserUnitTest userUnitTest = new UserUnitTest();

        userUnitTest.userCreated();
        userUnitTest.userCreatedIsValid();
        userUnitTest.userGetID();
        userUnitTest.testToString();

        LinkUnitTest linkUnitTest = new LinkUnitTest();

        linkUnitTest.createLink();
        linkUnitTest.establishAndTearDown();

        SocialNetworkUnitTest socialNetworkUnitTest = new SocialNetworkUnitTest();

        socialNetworkUnitTest.createSocialNetwork();
        socialNetworkUnitTest.addUserTestAndIsMember();
        socialNetworkUnitTest.establishAndTeardownLink();

    }
}
