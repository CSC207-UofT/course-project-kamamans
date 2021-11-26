package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PremiumUserSettings is responsible for implementing premium user features and actions which are defined in BaseUserSettings
 */

public class PremiumUserSettings implements BaseUserSettings, Serializable {
    private String classType;
    private Date renewalDate;
    private User user;
    private String colorScheme;
    private List<Airport> favouriteAirports = new ArrayList<>();
    private int autoLogoutTimer = 60;
    private Airport homeAirport;

    public PremiumUserSettings(User user) {
        this.user = user;
    }

    public boolean setClassType(String classType) {
        this.classType = classType;
        return true;
    }

    public String getClassType() { return classType; }

    public Date getRenewalDate() { return renewalDate; }

    public boolean setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
        return true;
    }

    public String getColorScheme() { return colorScheme; }

    public boolean setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
        return true;
    }

    public List<Airport> getFavouriteAirports() { return favouriteAirports; }

    public boolean addFavouriteAirport(Airport favouriteAirport) {
        this.favouriteAirports.add(favouriteAirport);
        return true;
    }

    public boolean removeFavouriteAirport(Airport removedAirport) {
        this.favouriteAirports.remove(removedAirport);
        return true;
    }

    public int getAutoLogoutTimer() { return autoLogoutTimer; }

    public boolean setAutoLogoutTimer(int autoLogoutTimer) {
        this.autoLogoutTimer = autoLogoutTimer;
        return true;
    }

    public Airport getHomeAirport() { return homeAirport; }

    public boolean setHomeAirport(Airport homeAirport) {
        this.homeAirport = homeAirport;
        return true;
    }

    public String upgradeUserType() {
        return "User Type is already Premium.";
    }

    /**
     * Downgrade PremiumUser to BasicUser by creating a new BasicUser and passing it to userManager
     * @return None
     */
    public String downgradeUserType() {
        this.user.changeUserType(new BasicUserSettings(this.user));
        return "User Type downgraded to Basic.";
    }
}
