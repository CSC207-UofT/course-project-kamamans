package usecases;

import entities.Airport;
import entities.Route;
import entities.SearchResults;
import entities.User;

import java.util.Date;
import java.util.List;

/**
 * ViewProfile handles editing user profile information and different methods based on user subscription status.
 */

public class ViewProfile {
    private final User currentUser;

    public ViewProfile(User user) {
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

    public void addRouteToHistory(Route route) { currentUser.addRouteToHistory(route); }

    /**
     * Return the users route history in a json parseable string using the method in SearchResults.
     * @return StringBuilder of the users route history in json parseable String format
     */
    public StringBuilder getRouteHistory() {
        List<Route> routeHistory = currentUser.getRouteHistory();
        SearchResults routeHistoryResults = new SearchResults(routeHistory);
        return routeHistoryResults.routesToString();
        }

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

    /**
     * Returns airports as a JSON parseable string.
     * @param airport airport to be converted
     * @return airport as a JSON parseable string
     */
    public StringBuilder airportToString(Airport airport) {
        StringBuilder returnString = new StringBuilder("{");
        returnString.append("\"iataCode\": " + "\""+airport.getIataCode()+"\"" + ",");
        returnString.append("\"city\":" + "\""+airport.getCity()+"\"");
        returnString.append("}");
        return returnString;
    }
    /**
     * Return a StringBuilder representing this users favourite airports
     * @return
     */
    public StringBuilder getFavouriteAirports() {
        StringBuilder returnString = new StringBuilder("[");
        List<Airport> favAirports = currentUser.user.getFavouriteAirports();

        for (Airport airport : favAirports) {
            returnString.append(airportToString(airport));
        };

        returnString.append("]");
        return returnString;
    }

    public String addFavouriteAirport(Airport airport) { return isValidRequest(currentUser.user.addFavouriteAirport(airport)); }

    public String removeFavouriteAirport(Airport airport) { return isValidRequest(currentUser.user.removeFavouriteAirport(airport)); }

    public int getAutoLogoutTimer() { return currentUser.user.getAutoLogoutTimer(); }

    public String setAutoLogoutTimer(int autoLogoutTimer) {
        return isValidRequest(currentUser.user.setAutoLogoutTimer(autoLogoutTimer));
    }

    public StringBuilder getHomeAirport() {
        return airportToString(currentUser.user.getHomeAirport());
    }

    public String setHomeAirport(Airport airport) { return isValidRequest(currentUser.user.setHomeAirport(airport)); }
}
