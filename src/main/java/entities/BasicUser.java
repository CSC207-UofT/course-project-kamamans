package entities;

import java.io.Serializable;

/**
 * BasicUser is responsible for implementing basic user actions which are defined in BaseUser
 */

public class BasicUser implements BaseUser, Serializable {
    public static final String INVALID_REQUEST = "Not available for Basic Users. Upgrade to Premium today!";
    public UserManager userManager;

    public BasicUser(UserManager userManager) {
        this.userManager = userManager;
    }

    public String setClassType(String classType) {
        return INVALID_REQUEST;
    }

    public String downgradeUserType() {
        return "User Type is already Basic.";
    }

    /**
     * Upgrade BasicUser to PremiumUser by creating a new PremiumUser and passing it to userManager
     * @return String
     */
    public String upgradeUserType() {
        this.userManager.changeUserType(new PremiumUser(this.userManager));
        return "User Type upgraded to Premium.";
    }

}
