package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * BasicUserSettings is responsible for implementing basic user actions which are defined in BaseUserSettings
 */

public class BasicUserSettings implements BaseUserSettings, Serializable {
    public User user;

    public BasicUserSettings(User user) {
        this.user = user;
    }

    public boolean setClassType(String classType) { return false; }

    public String getClassType() {
        return null;
    }

    public Date getRenewalDate() {
        return null;
    }

    public boolean setRenewalDate(Date renewalDate) { return false; }

    public String getColorScheme() {
        return null;
    }

    public boolean setColorScheme(String colorScheme) { return false; }

    public List<Airport> getFavouriteAirports() {
        return null;
    }

    public boolean addFavouriteAirport(Airport favAirport) { return false; }

    public boolean removeFavouriteAirport(Airport favAirport) { return false; }

    public int getAutoLogoutTimer() {
        return 0;
    }

    public boolean setAutoLogoutTimer(int autoLogoutTimer) { return false; }

    public Airport getHomeAirport() {
        return null;
    }

    public boolean setHomeAirport(Airport homeAirport) { return false; }

    public String downgradeUserType() {
        return "User Type is already Basic.";
    }

    /**
     * Upgrade BasicUser to PremiumUser by creating a new PremiumUser and passing it to userManager
     * @return String
     */
    public String upgradeUserType() {
        this.user.changeUserType(new PremiumUserSettings(this.user));
        return "User Type upgraded to Premium.";
    }

}