package controller;

import entities.Flight;
import usecases.UserManager;

import java.util.Date;

public class UserController {
    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    public void createAccount(String username, String password, String email, String phoneNumber) {
        userManager.createAccount(username, password, email, phoneNumber);
    }

    public boolean login(String username, String password) {
        try {
            return userManager.loginAttempt(username, password);
        }
        catch (NullPointerException e){
            return false;
        }
    }

    public void logout() {
        userManager.logout();
    }

    public void deleteAccount(String username) { userManager.deleteAccount(username); }

    /**
     * Serializes and deserializes userList. Call this at the end of changing UserProfile.
     */
    public void saveSettings() { userManager.saveSettings(); }

    public String getUsername() { return userManager.currentUser.getUsername(); }

    public String getPassword() { return userManager.currentUser.getPassword(); }

    public void setPassword(String pw) { userManager.currentUser.setPassword(pw); }

    public String getEmail() { return userManager.currentUser.getEmail(); }

    public void setEmail(String em) { userManager.currentUser.setEmail(em); }

    public String getPhoneNumber() {
        return userManager.currentUser.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        userManager.currentUser.setPhoneNumber(phoneNumber);
    }

    public int getAppRating() {
        return userManager.currentUser.getAppRating();
    }

    public void setAppRating(int appRating) {
        userManager.currentUser.setAppRating(appRating);
    }

    public void addFlightToHistory(Flight flight) { userManager.currentUser.addFlightToHistory(flight); }

    public String upgradeUserType() {
        return userManager.currentUser.upgradeUserType();
    }

    public String downgradeUserType() {
        return userManager.currentUser.downgradeUserType();
    }

    public String getUserType() { return userManager.currentUser.getUserType(); }

    // All the setters below return whether the user's profile was successfully changed
    public String getClassType() { return userManager.currentUser.getClassType(); }

    public String setClassType(String classType) { return userManager.currentUser.setClassType(classType); }

    public Date getRenewalDate() { return userManager.currentUser.getRenewalDate(); }

    public String setRenewalDate(Date date) { return userManager.currentUser.setRenewalDate(date); }

    public String getColorScheme() { return userManager.currentUser.getColorScheme(); }

    public String setColorScheme(String colorScheme) { return userManager.currentUser.setColorScheme(colorScheme); }

    // TODO: all ___FavouriteAirport and ___HomeAirport methods need to be changed since we can't access Airport entity from controllers

    public int getAutoLogoutTimer() { return userManager.currentUser.getAutoLogoutTimer(); }

    public String setAutoLogoutTimer(int autoLogoutTimer) { return userManager.currentUser.setAutoLogoutTimer(autoLogoutTimer); }
}