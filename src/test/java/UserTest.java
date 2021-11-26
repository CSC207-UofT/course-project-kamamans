import entities.BasicUserSettings;
import org.junit.Before;
import org.junit.Test;
import entities.User;

import static org.junit.Assert.*;

public class UserTest {
    User userManager;

    @Before
    public void setUp() throws Exception {
        userManager = new User("user", "pw", "email@example.com", "123456789");
    }

    @Test(timeout = 50)
    public void TestBasicUserDowngrade() {
        assertEquals("User Type is already Basic.", userManager.downgradeUserType());
    }

    @Test(timeout = 50)
    public void TestBasicUserUpgrade() {
        assertEquals("User Type upgraded to Premium.", userManager.upgradeUserType());
        System.out.println(userManager.user.toString());
    }

    @Test(timeout = 50)
    public void TestPremiumUserDowngrade() {
        assertEquals("User Type upgraded to Premium.", userManager.upgradeUserType());
        assertEquals("User Type downgraded to Basic.", userManager.downgradeUserType());
    }


    @Test(timeout = 50)
    public void TestLogin() {

    }
}
