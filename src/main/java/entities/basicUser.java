package entities;

import usecases.EditUser;

import java.util.Date;

public class basicUser extends loginInformation {
    private String ID;
    private String email;
    private String phoneNumber;
    private String classType;
    private boolean upgraded;
    private Date renewalDate;
    private int appRating;

    public basicUser(){

    }

    public basicUser(String id, String email, String phoneNumber, String c){
        this.ID = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classType = c;
        this.upgraded = false;
    }

    public basicUser(String id, String email, String phoneNumber, String c, Boolean upgradeAccount){
        this.ID = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.classType = c;
        this.upgraded = upgradeAccount;
        this.renewalDate = renewalDate;
        this.appRating = appRating;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

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
