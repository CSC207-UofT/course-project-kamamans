package entities;

import java.util.Calendar;

public class BasicUser extends LoginInformation {
    private String ID;
    private String password;
    private String email;
    private String phoneNumber;
    private String classType;

    private boolean upgraded;
    private PremiumSettings premiumSettings = new PremiumSettings();
    private Calendar renewalDate;
    private int appRating;

    public BasicUser(){

    }

    public BasicUser(String id, String password, String email, String phoneNumber, String c){
        this.ID = id;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classType = c;
        this.upgraded = false;
    }

    public BasicUser(String id, String password, String email, String phoneNumber, String c, Boolean upgradeAccount){
        this.ID = id;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classType = c;
        if(upgradeAccount) {
            Calendar expiryDate = Calendar.getInstance();
            expiryDate.add(Calendar.MONTH, 1);
            this.renewalDate = expiryDate;
        }
        this.upgraded = upgradeAccount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public boolean isUpgraded() {
        return upgraded;
    }

    public void setUpgraded(boolean upgraded) {
        if(upgraded) {
            this.upgraded = upgraded;
            Calendar expiryDate = Calendar.getInstance();
            expiryDate.add(Calendar.MONTH, 1);
            this.renewalDate = expiryDate;
        } else{
            this.upgraded = upgraded;
        }
    }

    public Calendar getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Calendar renewalDate) {
        this.renewalDate = renewalDate;
    }

    public int getAppRating() {
        return appRating;
    }

    public void setAppRating(int appRating) {
        this.appRating = appRating;
    }

}
