package entities;

import java.util.Date;
import usecases.UserManager;

public class PremiumUser implements BaseUser {
    private String classType;
    private Date renewalDate;
    private UserManager userManager;

    public PremiumUser(UserManager userManager) {
        this.userManager = userManager;
    }

    public String setClassType(String classType) {
        this.classType = classType;
        return "Class Type changed to " + classType + ".";
    }

    public String getClassType() { return this.classType; }

    public Date getRenewalDate() { return this.renewalDate; }

    public void setRenewalDate(Date renewalDate) { this.renewalDate = renewalDate; }

    public String upgradeUserType() {
        return "User Type is already Premium.";
    }

    public String downgradeUserType() {
        this.userManager.changeUserType(new BasicUser(this.userManager));
        return "User Type downgraded to Basic.";
    }
}
