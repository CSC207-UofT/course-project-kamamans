package usecases;

import entities.Airport;
import entities.Flight;
import entities.User;

import java.util.Date;
import java.util.List;

/**
 * ViewUserProfile handles editing user profile information and different methods based on user subscription status.
 */

public class ViewUserProfile {
    private User currentUser;

    public ViewUserProfile(User user) {
        currentUser = user;
    }

    public String getUsername() { return currentUser.getUsername(); }

    public String getPassword() {
        return currentUser.getPassword();
    }

    public void setPassword(String password) {
        currentUser.setPassword(password);
    }

    public String getEmail() {
        return currentUser.getEmail();
    }

    public void setEmail(String email) {
        currentUser.setEmail(email);
    }

    public String getPhoneNumber() {
        return currentUser.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        currentUser.setPhoneNumber(phoneNumber);
    }

    public int getAppRating() {
        return currentUser.getAppRating();
    }

    public void setAppRating(int appRating) {
        currentUser.setAppRating(appRating);
    }

    public void addFlightToHistory(Flight flight) { currentUser.addFlightToHistory(flight); }

    public String upgradeUserType() {
        return currentUser.upgradeUserType();
    }

    public String downgradeUserType() {
        return currentUser.downgradeUserType();
    }

    public String getUserType() { return currentUser.getUserType(); }

    // methods past here are premium user functions, so they check user type
    public String isValidRequest(boolean valid) {
        if (!valid) {
            return "Not available for Basic Users. Upgrade to Premium today!";
        }
        else {
            return "Successfully changed."; }
    }

    public String getClassType() {
        return currentUser.user.getClassType();
    }

    public String setClassType(String classType) { return isValidRequest(currentUser.user.setClassType(classType)); }

    public Date getRenewalDate() {
        return currentUser.user.getRenewalDate();
    }

    public String setRenewalDate(Date date) { return isValidRequest(currentUser.user.setRenewalDate(date)); }

    public String getColorScheme() { return currentUser.user.getColorScheme(); }

    public String setColorScheme(String colorScheme) { return isValidRequest(currentUser.user.setColorScheme(colorScheme)); }

    public List<Airport> getFavouriteAirports() {
        return currentUser.user.getFavouriteAirports();
    }

    public String addFavouriteAirport(Airport airport) { return isValidRequest(currentUser.user.addFavouriteAirport(airport)); }

    public String removeFavouriteAirport(Airport airport) { return isValidRequest(currentUser.user.removeFavouriteAirport(airport)); }

    public int getAutoLogoutTimer() { return currentUser.user.getAutoLogoutTimer(); }

    public String setAutoLogoutTimer(int autoLogoutTimer) {
        return isValidRequest(currentUser.user.setAutoLogoutTimer(autoLogoutTimer));
    }

    public Airport getHomeAirport() {
        return currentUser.user.getHomeAirport();
    }

    public String setHomeAirport(Airport airport) { return isValidRequest(currentUser.user.setHomeAirport(airport)); }
}
