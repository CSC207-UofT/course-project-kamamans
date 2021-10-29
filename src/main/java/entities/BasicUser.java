package entities;

import usecases.UserManager;

public class BasicUser implements BaseUser {
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

    public String upgradeUserType() {
        BaseUser new_user = new PremiumUser(this.userManager);
        this.userManager.changeUserType(new_user);
        return "User Type upgraded to Premium.";
    }

}
