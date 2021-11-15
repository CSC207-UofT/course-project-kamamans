import entities.BasicUser;
import org.junit.Before;
import org.junit.Test;
import usecases.UserManager;

import static org.junit.Assert.*;

public class UserTest {
    UserManager userManager;

    @Before
    public void setUp() throws Exception {
        userManager = new UserManager("001", "user", "pw", "email@example.com", "123456789");
    }

    @Test(timeout = 50)
    public void TestBasicUserClassType() {
        assertEquals(BasicUser.INVALID_REQUEST, userManager.user.setClassType("Economy"));
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
    public void TestPremiumUserFeatures() {
        userManager.upgradeUserType();
        assertEquals("Class Type changed to Economy.", userManager.user.setClassType("Economy"));
    }

}
