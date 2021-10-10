package entities;

import java.util.Date;

public class premiumUser extends basicUser{
    private Date renewalDate;
    private int appRating;

    public premiumUser(Date renewalDate, int appRating) {
        this.renewalDate = renewalDate;
        this.appRating = appRating;
    }

    public premiumUser(String id, String email, int phoneNumber, String c, Date renewalDate, int appRating) {
        super(id, email, phoneNumber, c);
        this.renewalDate = renewalDate;
        this.appRating = appRating;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public int getAppRating() {
        return appRating;
    }

    public void setAppRating(int appRating) {
        this.appRating = appRating;
    }
}
